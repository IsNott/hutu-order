package org.nott.service.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.config.BusinessConfig;
import org.nott.common.exception.HutuBizException;
import org.nott.common.redis.RedisUtils;
import org.nott.common.thread.pool.HutuThreadPoolExecutor;
import org.nott.common.utils.HutuUtils;
import org.nott.common.utils.SequenceUtils;
import org.nott.dto.OrderItemDTO;
import org.nott.dto.UserSettleOrderDTO;
import org.nott.enums.OrderStatusEnum;
import org.nott.enums.PickTypeEnum;
import org.nott.model.BizItem;
import org.nott.model.BizPayOrder;
import org.nott.service.delayed.handler.UserPayOrderQueueHandler;
import org.nott.service.mapper.BizPayOrderMapper;
import org.nott.service.service.IBizItemService;
import org.nott.service.service.IBizPayOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.SettleOrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Service
public class BizPayOrderServiceImpl extends ServiceImpl<BizPayOrderMapper, BizPayOrder> implements IBizPayOrderService {

    @Resource
    private UserPayOrderQueueHandler userPayOrderQueueHandler;
    @Resource
    private BizPayOrderMapper bizPayOrderMapper;
    @Resource
    private IBizItemService bizItemService;
    @Resource
    private BusinessConfig businessConfig;
    @Resource
    private SequenceUtils sequenceUtils;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public String countPurchases() {
        BigDecimal decimal = new BigDecimal("0.0");
        List<String> money = bizPayOrderMapper.selectOrderMoney();
        if (!money.isEmpty()) {
            for (String singlePay : money) {
                BigDecimal single = new BigDecimal(singlePay);
                decimal = decimal.add(single);
            }
        }
        return decimal.toString();
    }

    @Override
    public SettleOrderVo doUserSettle(UserSettleOrderDTO dto) {
        BigDecimal pointDiscount = new BigDecimal("0.00");
        BigDecimal couponDisCount = new BigDecimal("0.00");
        // TODO 检验积分
        if (dto.isUsePoint()) {

        }
        // TODO 检验优惠券
        if (dto.isUseCoupon()) {

        }
        // 确认需支付金额
        BigDecimal originalAmount = checkAndReturnTotalAmount(dto.getItems());
        BigDecimal totalAmount = originalAmount.subtract(pointDiscount).subtract(couponDisCount);
        if (totalAmount.compareTo(new BigDecimal("0.00")) <= 0) {
            throw new HutuBizException("优惠券+积分减免的总金额不能大于原价");
        }
        dto.setOriginAmount(originalAmount);
        dto.setTotalAmount(totalAmount);
        // 创建内部订单
        return this.createOrder(dto);
    }

    private BigDecimal checkAndReturnTotalAmount(List<OrderItemDTO> itemsByOrder) {
        List<Long> ids = itemsByOrder.stream().map(OrderItemDTO::getItemId).collect(Collectors.toList());
        LambdaQueryWrapper<BizItem> wp = new LambdaQueryWrapper<BizItem>()
                .in(BizItem::getId, ids);
        BigDecimal originalAmount = new BigDecimal("0.00");
        List<BizItem> itemInDb = bizItemService.list(wp);
        for (OrderItemDTO orderItemDTO : itemsByOrder) {
            Integer dtoItemPiece = orderItemDTO.getItemPiece();
            BizItem bizItem = itemInDb.stream().filter(item -> item.getId().equals(orderItemDTO.getItemId())).findAny().orElse(null);
            HutuUtils.requireNotNull(bizItem, "没有找到对应的商品");
            BigDecimal itemAmount = new BigDecimal(bizItem.getActuallyAmount());
            BigDecimal totalAmount4Item = itemAmount.multiply(BigDecimal.valueOf(dtoItemPiece));
            originalAmount.add(totalAmount4Item);
        }

        return originalAmount;
    }

    private SettleOrderVo createOrder(UserSettleOrderDTO dto) {
        Integer pickType = dto.getPickType();
        BizPayOrder order = new BizPayOrder();
        HutuUtils.copyProperties(dto, order);
        PickTypeEnum pickTypeEnum = PickTypeEnum.getByVal(pickType);
        String prefix = "";
        switch (pickTypeEnum) {
            default:
                throw new HutuBizException("未知的就餐类型");
            case EAT_IN: {
                prefix = businessConfig.getPrefixEatIn();
                break;
            }
            case TACK_OUT: {
                prefix = businessConfig.getPrefixTakeOut();
                break;
            }
        }
        order.setItemInfo(JSONObject.toJSONString(dto.getItems()));

        String nextOrderNo = sequenceUtils.nextSeq(prefix);
        order.setOrderNo(nextOrderNo);
        order.setOrderStatus(OrderStatusEnum.INIT.getVal());
        this.save(order);

        Date currentDate = new Date();
        long id = StpUtil.getLoginIdAsLong();
        SettleOrderVo vo = HutuUtils.transToObject(dto, SettleOrderVo.class);
        vo.setOrderNo(nextOrderNo);
        vo.setOrderId(order.getId());
        vo.setCreateTime(currentDate);
        vo.setExpireTime(new Date(currentDate.getTime() + businessConfig.getOrderExpire()));
        vo.setUserId(id);

        HutuThreadPoolExecutor.threadPool.submit(() -> {
            userPayOrderQueueHandler.pushData2Queue(vo, businessConfig.getOrderExpire());
            redisUtils.set(UserPayOrderQueueHandler.NON_PAYMENT_ORDER_KEY_PREFIX + id, JSONObject.toJSONString(vo));
        });

        return vo;
    }
}
