package org.nott.service.pay.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.annotation.PayApi;
import org.nott.common.exception.HutuBizException;
import org.nott.common.redis.RedisUtils;
import org.nott.common.thread.pool.HutuThreadPoolExecutor;
import org.nott.common.utils.HutuUtils;
import org.nott.common.utils.SpringContextUtil;
import org.nott.dto.PayDTO;
import org.nott.dto.RefundDTO;
import org.nott.enums.OrderStatusEnum;
import org.nott.feign.BizPayOrderWsClient;
import org.nott.model.BizPayOrder;
import org.nott.model.BizUser;
import org.nott.model.inter.BalancePayInfo;
import org.nott.model.inter.BalanceRefundInfo;
import org.nott.service.api.IBizPayOrderService;
import org.nott.service.api.IBizUserService;
import org.nott.service.pay.PayService;
import org.nott.vo.pay.PayResultVo;
import org.nott.vo.pay.QueryResultVo;
import org.nott.vo.pay.RefundResultVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Nott
 * @date 2025-3-28
 */

@Slf4j
@Service
@PayApi("P001")
@Transactional(rollbackFor = Exception.class)
public class BalancePayService implements PayService {

    @Resource
    private IBizUserService bizUserService;

    @Override
    public PayResultVo doPay(PayDTO payDTO) {
        Long payOrderId = payDTO.getPayOrderId();
        String payNo = payDTO.getPayNo();
        long userId = StpUtil.getLoginIdAsLong();
        IBizPayOrderService payOrderService = SpringContextUtil.getBean(IBizPayOrderService.class);
        BizPayOrder payOrder = payOrderService.getPayOrderById(payOrderId, payNo);
        if (!Objects.equals(payOrder.getOrderStatus(), OrderStatusEnum.INIT.getVal())) {
            throw new HutuBizException("订单已支付或过期，无法支付");
        }
        payOrderId = payOrder.getId();
        BizUser user = bizUserService.getById(userId);
        BigDecimal actualBalance = user.getActualBalance();
        BigDecimal giftBalance = user.getGiftBalance();
        BigDecimal totalUserBal = actualBalance.add(giftBalance);
        BigDecimal totalAmount = payOrder.getTotalAmount();
        if (totalUserBal.compareTo(totalAmount) < 0) {
            throw new HutuBizException("余额不足");
        }
        boolean hasGift = giftBalance.compareTo(BigDecimal.ZERO) > 0;
        boolean giftEnough = giftBalance.compareTo(totalAmount) >= 0;
        LambdaUpdateWrapper<BizUser> wrapper = new LambdaUpdateWrapper<BizUser>()
                .eq(BizUser::getId, userId)
                .eq(BizUser::getActualBalance, actualBalance)
                .eq(BizUser::getGiftBalance, giftBalance)
                .set(!hasGift, BizUser::getActualBalance, actualBalance.subtract(totalAmount))
                .set(hasGift && giftEnough,
                        BizUser::getGiftBalance, giftBalance.subtract(totalAmount))
                .set(hasGift && !giftEnough,
                        BizUser::getGiftBalance, BigDecimal.ZERO)
                .set(hasGift && !giftEnough,
                        BizUser::getActualBalance, actualBalance.subtract(totalAmount).add(giftBalance));
        boolean updated = bizUserService.update(wrapper);

        BalancePayInfo info = new BalancePayInfo();
        if (giftEnough) {
            info.setActualDispose(BigDecimal.ZERO);
            info.setGiftDispose(totalAmount);
        } else {
            info.setActualDispose(totalAmount.subtract(giftBalance));
            info.setGiftDispose(hasGift ? giftBalance : BigDecimal.ZERO);
        }
        if (!updated) {
            throw new HutuBizException("支付失败，更新余额失败");
        }

        boolean update = payOrderService.updateOrderPayStatus(payOrderId, JSONObject.toJSONString(info));
        if (!update) {
            throw new HutuBizException("支付失败，更新订单状态失败");
        }

        PayResultVo resultVo = new PayResultVo();
        resultVo.setOrderNo(payNo);
        resultVo.setPayOrderId(String.valueOf(payOrderId));
        resultVo.setOutTradeNo(null);
        resultVo.setPayCode(this.getClass().getAnnotation(PayApi.class).value());
        resultVo.setPayAmount(totalAmount);
        HutuThreadPoolExecutor.threadPool.submit(() -> payOrderService.doOrderPayedBusiness(payOrder));
        return resultVo;
    }

    @Override
    public RefundResultVo doRefund(RefundDTO refundDTO) {
        String payNo = refundDTO.getPayNo();
        boolean full = refundDTO.isFull();
        IBizPayOrderService payOrderService = SpringContextUtil.getBean(IBizPayOrderService.class);
        BizPayOrder payOrder = payOrderService.getPayOrderById(null, payNo);
        Integer orderStatus = payOrder.getOrderStatus();
        if (!orderStatus.equals(OrderStatusEnum.PAYED.getVal())) {
            throw new HutuBizException("订单状态异常，无法退款");
        }
        Long userId = payOrder.getUserId();
        BizUser user = bizUserService.getById(userId);
        BigDecimal giftBalance = user.getGiftBalance();
        BigDecimal actualBalance = user.getActualBalance();
        String remark = payOrder.getRemark();
        BalancePayInfo balancePayInfo = JSONObject.parseObject(remark, BalancePayInfo.class);
        if (HutuUtils.isEmpty(remark)) {
            throw new HutuBizException("支付信息异常，无法退款");
        }
        BigDecimal refundAmount = full ? payOrder.getTotalAmount() : refundDTO.getRefundAmount();
        if (refundAmount.compareTo(payOrder.getTotalAmount()) > 0) {
            throw new HutuBizException("退款金额超出支付金额");
        }
        BigDecimal actualDispose = balancePayInfo.getActualDispose();
        BigDecimal giftDispose = balancePayInfo.getGiftDispose();

        BizPayOrder refundOrder = payOrderService.getOne(new LambdaQueryWrapper<BizPayOrder>()
                .eq(BizPayOrder::getPayOrderId, payOrder.getId()));

        BigDecimal totalDispose = actualDispose.add(giftDispose);
        boolean equal = totalDispose.compareTo(refundAmount) == 0;
        LambdaUpdateWrapper<BizUser> wrapper;
        BalanceRefundInfo balanceRefundInfo = new BalanceRefundInfo();
        if(equal) {
            wrapper = new LambdaUpdateWrapper<BizUser>()
                    .eq(BizUser::getId, userId)
                    .eq(BizUser::getActualBalance, actualBalance)
                    .eq(BizUser::getGiftBalance, giftBalance)
                    .set(BizUser::getActualBalance, actualBalance.add(actualDispose))
                    .set(BizUser::getGiftBalance, giftBalance.add(giftDispose));

            balanceRefundInfo.setActualRefund(actualDispose);
            balanceRefundInfo.setGiftRefund(giftDispose);
        }else {
            boolean disposeGreater = actualDispose.compareTo(refundAmount) >= 0;
            wrapper = new LambdaUpdateWrapper<BizUser>()
                    .eq(BizUser::getId, userId)
                    .eq(BizUser::getActualBalance, actualBalance)
                    .eq(BizUser::getGiftBalance, giftBalance)
                    .set(disposeGreater,BizUser::getActualBalance, actualBalance.add(refundAmount))
                    .set(!disposeGreater ,BizUser::getActualBalance, actualBalance.add(actualDispose))
                    .set(!disposeGreater ,BizUser::getGiftBalance, giftBalance.add(refundAmount.subtract(actualDispose)));
            if (disposeGreater) {
                balanceRefundInfo.setActualRefund(refundAmount);
                balanceRefundInfo.setGiftRefund(BigDecimal.ZERO);
            }else {
                balanceRefundInfo.setActualRefund(actualDispose);
                balanceRefundInfo.setGiftRefund(refundAmount.subtract(actualDispose));
            }
        }
        boolean update = bizUserService.update(wrapper);
        if (!update) {
            throw new HutuBizException("退款失败，更新余额失败");
        }
        refundOrder.setOrderStatus(OrderStatusEnum.PAYED.getVal());
        refundOrder.setRemark(JSONObject.toJSONString(balanceRefundInfo));
        payOrderService.updateById(refundOrder);

        payOrder.setOrderStatus(OrderStatusEnum.REFUND.getVal());
        payOrder.setRefundOrderNo(refundOrder.getOrderNo());
        payOrderService.updateById(payOrder);

        RefundResultVo resultVo = new RefundResultVo();
        resultVo.setRefundNo(refundOrder.getOrderNo());
        resultVo.setOrderNo(payOrder.getOrderNo());
        resultVo.setRefundAmount(refundAmount);
        resultVo.setRefundStatus(OrderStatusEnum.REFUND.getVal());
        return resultVo;
    }

    @Override
    public void notify(JSONObject obj) {
        throw new HutuBizException("余额支付不支持异步通知");
    }

    @Override
    public QueryResultVo query(String orderNo) {
        return null;
    }
}
