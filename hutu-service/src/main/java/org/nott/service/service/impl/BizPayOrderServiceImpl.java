package org.nott.service.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.nott.common.config.BusinessConfig;
import org.nott.common.exception.HutuBizException;
import org.nott.common.redis.RedisUtils;
import org.nott.common.thread.pool.HutuThreadPoolExecutor;
import org.nott.common.utils.HutuUtils;
import org.nott.common.utils.SequenceUtils;
import org.nott.dto.MyOrderQueryDTO;
import org.nott.dto.OrderItemDTO;
import org.nott.dto.UserSettleOrderDTO;
import org.nott.enums.OrderMessageType;
import org.nott.enums.OrderStatusEnum;
import org.nott.enums.PickTypeEnum;
import org.nott.enums.YesOrNoEnum;
import org.nott.feign.BizPayOrderWsClient;
import org.nott.model.BizItem;
import org.nott.model.BizPayOrder;
import org.nott.model.BizShopInfo;
import org.nott.model.inter.OrderWsMessageInfo;
import org.nott.service.delayed.handler.UserPayOrderQueueHandler;
import org.nott.service.mapper.BizPayOrderMapper;
import org.nott.service.service.IBizItemService;
import org.nott.service.service.IBizPayOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.service.service.IBizShopInfoService;
import org.nott.service.service.IBizUserPackageService;
import org.nott.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
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
@Slf4j
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
    @Resource
    private IBizShopInfoService bizShopInfoService;
    @Resource
    private IBizUserPackageService bizUserPackageService;
    @Resource
    private BizPayOrderWsClient bizPayOrderWsClient;

    @PostConstruct
    public void pushUnFinishOrder2Queue() {
        LambdaQueryWrapper<BizPayOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .isNotNull(BizPayOrder::getUserId)
                .notIn(BizPayOrder::getOrderStatus, Arrays.asList(OrderStatusEnum.EXPIRE.getVal(),
                OrderStatusEnum.FAILED.getVal(), OrderStatusEnum.PAYED.getVal(), OrderStatusEnum.REFUND.getVal()))
                .like(BizPayOrder::getCreateTime, HutuUtils.FORMAT.DATE.format(new Date()));

        List<BizPayOrder> unPayOrders = this.list(wrapper);
        if (HutuUtils.isNotEmpty(unPayOrders)) {
            unPayOrders.forEach(order -> {
                userPayOrderQueueHandler.pushData2Queue(order, businessConfig.getOrderExpire());
                redisUtils.hset(UserPayOrderQueueHandler.NON_PAYMENT_ORDER_KEY_PREFIX + order.getUserId(), order.getId() + "", JSONObject.toJSONString(order));
            });
            log.info("投入了{}个当日未完成订单到监听队列", unPayOrders.size());
        }
    }

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
    @Transactional
    public SettleOrderVo doUserSettle(UserSettleOrderDTO dto) {
        BigDecimal pointDiscount = new BigDecimal("0.00");
        BigDecimal couponDisCount = new BigDecimal("0.00");
        Future<Boolean> removePointTaskResult = null;
        Future<Boolean> removeCouponTaskResult = null;
        // TODO 检验 移除积分
        if (dto.isUsePoint()) {

        }
        // TODO 检验 移除优惠券
        if (dto.isUseCoupon()) {

        }

        // 确认需支付金额
        List<OrderItemDTO> items = dto.getItems();
        BigDecimal originalAmount = checkAndReturnTotalAmount(items);
        BigDecimal totalAmount = originalAmount.subtract(pointDiscount).subtract(couponDisCount);
        if (totalAmount.compareTo(new BigDecimal("0.00")) <= 0) {
            throw new HutuBizException("优惠券+积分减免的总金额不能大于原价");
        }
        dto.setOriginAmount(originalAmount);
        dto.setTotalAmount(totalAmount);
        // 创建内部订单
        SettleOrderVo vo = this.createOrder(dto);
//        try {
//            if(!removePointTaskResult.get() || removeCouponTaskResult.get()){
//
//            }
//        } catch (Exception e) {
//            log.error(e.getLocalizedMessage(),e);
//            throw new HutuBizException("积分/优惠券使用失败");
//        }
        // 移除购物车内容
        HutuThreadPoolExecutor.threadPool.submit(()->{
            List<Long> ids = items.stream().map(OrderItemDTO::getId).collect(Collectors.toList());
            bizUserPackageService.removeBatchByIds(ids);
        });
        return vo;
    }

    @Override
    public PayOrderVo queryPayOrderById(Long id) {
        //TODO 实际上这里应该加上支付成功状态的查询条件
        BizPayOrder payOrder = this.getById(id);
        HutuUtils.requireNotNull(payOrder, "根据id没有找到对应订单");
        String itemInfo = payOrder.getItemInfo();
        PayOrderVo vo = HutuUtils.transToObject(payOrder, PayOrderVo.class);
        BizShopInfo shopInfo = bizShopInfoService.getById(vo.getShopId());
        vo.setPayOrderId(id);
        vo.setShopAddress(shopInfo.getAddress());
        vo.setShopName(shopInfo.getShopName());
        if(StringUtils.isNotEmpty(itemInfo)){
            List<OrderItemVo> orderItemVos = JSONArray.parseArray(itemInfo, OrderItemVo.class);
            vo.setItemInfo(orderItemVos);
        }
        return vo;
    }

    @Override
    public PayOrderVo orderQuery(Long id) {
        BizPayOrder payOrder = this.getById(id);
        HutuUtils.requireNotNull(payOrder,"没有找到对应订单");
        Long orderId = payOrder.getId();
        PayOrderVo vo = HutuUtils.transToObject(payOrder, PayOrderVo.class);
        vo.setPayOrderId(orderId);
        return vo;
    }

    @Override
    public FrontOrderVo orderFront(Long orderId) {
        BizPayOrder payOrder = this.getById(orderId);
        HutuUtils.requireNotNull(payOrder,"没有找到订单");
        Date settleTime = payOrder.getSettleTime();
        HutuUtils.requireNotNull(settleTime,"没有找到订单结算时间");
        return bizPayOrderMapper.orderFrontQueryByOrderId(HutuUtils.FORMAT.DATETIME.format(settleTime), orderId);
    }

    @Override
    public void simulateNotify(Long orderId) {
        BizPayOrder payOrder = this.getById(orderId);
        payOrder.setOrderStatus(OrderStatusEnum.PAYED.getVal());
        payOrder.setSettleTime(new Date());
        this.updateById(payOrder);
        redisUtils.hdel(UserPayOrderQueueHandler.NON_PAYMENT_ORDER_KEY_PREFIX + payOrder.getUserId(), payOrder.getId() + "");
        OrderWsMessageInfo messageInfo = HutuUtils.transToObject(payOrder, OrderWsMessageInfo.class);
        messageInfo.setMessageType(OrderMessageType.IN.getVal());
        this.sendMessageAsync(payOrder.getShopId(), messageInfo);
    }

    @Async
    public void sendMessageAsync(Long shopId, OrderWsMessageInfo messageInfo) {
        bizPayOrderWsClient.sendMessage2Shop(shopId, JSONObject.parseObject(messageInfo.toJSONString()));
    }

    @Override
    public Page<MyPayOrderVo> queryMyOrder(MyOrderQueryDTO dto, Integer page, Integer size) {
        long id = StpUtil.getLoginIdAsLong();
        Integer status = dto.getStatus();
        String keyWord = dto.getKeyWord();
        Page<MyPayOrderVo> queryOrderPageByUserId = bizPayOrderMapper.queryOrderPageByUserId(new Page<>(page, size), id, status, keyWord);
        if (queryOrderPageByUserId != null && HutuUtils.isNotEmpty(queryOrderPageByUserId.getRecords())) {
            for (MyPayOrderVo vo : queryOrderPageByUserId.getRecords()) {
                if (HutuUtils.isEmpty(vo.getItemInfo())) continue;
                vo.setItems(JSONArray.parseArray(vo.getItemInfo(), OrderItemVo.class));
            }
        }
        return queryOrderPageByUserId;
    }

    @Override
    public void deleteOrder(Long orderId) {
        BizPayOrder payOrder = this.getById(orderId);
        HutuUtils.requireNotNull(payOrder,"没有找到对应订单");
        payOrder.setUserDelFlag(YesOrNoEnum.YES.getValue());
        this.updateById(payOrder);
    }

    @Override
    public void finishOrder(Long orderId) {
        BizPayOrder payOrder = this.getById(orderId);
        HutuUtils.requireNotNull(payOrder,"没有找到对应订单");
        payOrder.setOrderStatus(OrderStatusEnum.FINISH.getVal());
        this.updateById(payOrder);
        OrderWsMessageInfo messageInfo = HutuUtils.transToObject(payOrder, OrderWsMessageInfo.class);
        messageInfo.setMessageType(OrderMessageType.FINISH.getVal());
        this.sendMessageAsync(payOrder.getShopId(), messageInfo);
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
            originalAmount = originalAmount.add(totalAmount4Item);
        }

        return originalAmount;
    }

    private SettleOrderVo createOrder(UserSettleOrderDTO dto) {
        Integer pickType = dto.getPickType();
        BizPayOrder order = new BizPayOrder();
        long id = StpUtil.getLoginIdAsLong();
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
        List<OrderItemDTO> items = dto.getItems();
        order.setItemInfo(JSONObject.toJSONString(items));

        String nextOrderNo = sequenceUtils.nextSeq(prefix);
        order.setOrderNo(nextOrderNo);
        order.setOrderStatus(OrderStatusEnum.INIT.getVal());
        order.setUserId(id);
        order.setItemPiece(items.size());
        order.setWaitTime(items.stream().mapToInt(OrderItemDTO::getExpectMakeTime).sum());
        this.save(order);

        Date currentDate = new Date();

        SettleOrderVo vo = HutuUtils.transToObject(dto, SettleOrderVo.class);
        vo.setOrderNo(nextOrderNo);
        vo.setOrderId(order.getId());
        vo.setCreateTime(currentDate);
        vo.setExpireTime(new Date(currentDate.getTime() + businessConfig.getOrderExpire()));
        vo.setUserId(id);

        HutuThreadPoolExecutor.threadPool.submit(() -> {
            userPayOrderQueueHandler.pushData2Queue(order, businessConfig.getOrderExpire());
            redisUtils.hset(UserPayOrderQueueHandler.NON_PAYMENT_ORDER_KEY_PREFIX + id, order.getId() + "", JSONObject.toJSONString(order));
            log.info("订单[{}]进入监听队列",order.getId());
        });
        return vo;
    }
}
