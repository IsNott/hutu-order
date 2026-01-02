package org.nott.service.api;

//import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.nott.common.config.BusinessConfig;
import org.nott.common.exception.HutuBizException;
import org.nott.common.idWorker.SnowflakeIdWorker;
import org.nott.common.redis.RedisUtils;
import org.nott.common.thread.pool.HutuThreadPoolExecutor;
import org.nott.common.utils.HutuUtils;
import org.nott.common.utils.SequenceUtils;
import org.nott.dto.MyOrderQueryDTO;
import org.nott.dto.OrderItemDTO;
import org.nott.dto.RefundDTO;
import org.nott.dto.UserSettleOrderDTO;
import org.nott.enums.*;
import org.nott.feign.BizPayOrderWsClient;
import org.nott.model.BizItem;
import org.nott.model.BizPayOrder;
import org.nott.model.BizShopInfo;
import org.nott.model.BizUser;
import org.nott.model.inter.OrderWsMessageInfo;
import org.nott.service.api.delayed.handler.UserPayOrderQueueHandler;
import org.nott.service.mapper.api.BizPayOrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
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
@Slf4j
public class BizPayOrderService extends ServiceImpl<BizPayOrderMapper, BizPayOrder> {

    public static final SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1, 1);

    @Resource
    private UserPayOrderQueueHandler userPayOrderQueueHandler;
    @Resource
    private BizPayOrderMapper bizPayOrderMapper;
    @Resource
    private BizItemService bizItemService;
    @Resource
    private BusinessConfig businessConfig;
    @Resource
    private SequenceUtils sequenceUtils;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private BizShopInfoService bizShopInfoService;
    @Resource
    private BizUserPackageService bizUserPackageService;
    @Resource
    private BizPayOrderWsClient bizPayOrderWsClient;
    @Resource
    private BizUserService bizUserService;

    private final Object lock = new Object();

    @PostConstruct
    public void pushUnFinishOrder2Queue() {
        if (HandleOrderExpireType.DELAYED_QUEUE.getName().equals(businessConfig.getCheckType())) {
            redisUtils.delByKey(RedisUtils.Keys.NON_PAYMENT_ORDER_KEY_PREFIX);
            LambdaQueryWrapper<BizPayOrder> wrapper = new LambdaQueryWrapper<>();
            wrapper
                    .isNotNull(BizPayOrder::getUserId)
                    .notIn(BizPayOrder::getOrderStatus, Arrays.asList(OrderStatusEnum.EXPIRE.getVal(),
                            OrderStatusEnum.FAILED.getVal(), OrderStatusEnum.PAYED.getVal(), OrderStatusEnum.REFUND.getVal()));
//                    .like(BizPayOrder::getCreateTime, HutuUtils.FORMAT.DATE.format(new Date()));

            List<BizPayOrder> unPayOrders = this.list(wrapper);
            if (HutuUtils.isNotEmpty(unPayOrders)) {
                unPayOrders.forEach(order -> {
                    userPayOrderQueueHandler.pushData2Queue(order, businessConfig.getOrderExpire());
                    redisUtils.hset(RedisUtils.Keys.NON_PAYMENT_ORDER_KEY_PREFIX , HutuUtils.joinByColon(order.getUserId(), order.getId()), JSONObject.toJSONString(order),
                            TimeEnum.DAY.getSeconds());
                });
                log.info("投入了{}个未完成订单到监听队列", unPayOrders.size());
            }
        }
    }


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



    public SettleOrderVo doUserSettle(UserSettleOrderDTO dto) {
        BigDecimal pointDiscount = new BigDecimal("0.00");
        BigDecimal couponDisCount = new BigDecimal("0.00");
        long id = 1L;
//        long id = StpUtil.getLoginIdAsLong();
        BizUser user;
        SettleOrderVo vo;
        List<OrderItemDTO> items = dto.getItems();
        synchronized (lock) {
            boolean couponDisable = false;
            BigDecimal originalAmount = checkAndReturnTotalAmount(items);
            user = bizUserService.getById(id);
            HutuUtils.requireNotNull(user, "没有找到对应用户");
            // 检验 移除积分
            if (dto.isUsePoint()) {
                Long point = user.getPoint();
                Long pointCount = dto.getPointCount();
                if (point < pointCount) {
                    throw new HutuBizException("积分不足");
                }
                BigDecimal disCountPrice = new BigDecimal(pointCount / 10L);
                if(disCountPrice.compareTo(originalAmount) >= 0){
                    disCountPrice = originalAmount.subtract(new BigDecimal("0.1"));
                    couponDisable = true;
                }
                pointDiscount = disCountPrice;
                Long leftPoint = point - pointCount;
                LambdaUpdateWrapper<BizUser> wrapper = new LambdaUpdateWrapper<>();
                wrapper.eq(BizUser::getId, id)
                        .eq(BizUser::getPoint, point)
                        .set(BizUser::getPoint, leftPoint);
                boolean update = bizUserService.update(wrapper);
                if (!update) {
                    throw new HutuBizException("积分更新失败");
                }
            }
            // TODO 检验 移除优惠券
            if (dto.isUseCoupon() && !couponDisable) {

            }

            // 确认需支付金额
            BigDecimal totalAmount = originalAmount.subtract(pointDiscount).subtract(couponDisCount);
            if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new HutuBizException("优惠券+积分减免的总金额不能大于原价");
            }
            dto.setOriginAmount(originalAmount);
            dto.setTotalAmount(totalAmount);
            // 创建内部订单
            vo = this.createOrder(dto);
        }
        // 移除购物车内容（将结算参数放入redis，方便后续回滚）
        HutuThreadPoolExecutor.threadPool.submit(() -> {
            List<Long> ids = items.stream().map(OrderItemDTO::getId).collect(Collectors.toList());
            bizUserPackageService.removeBatchByIds(ids);
            redisUtils.hset(RedisUtils.Keys.SETTLE_INFO_PREFIX,
                    HutuUtils.joinByColon(id, vo.getOrderId()),
                    JSONObject.toJSONString(vo));
        });
        return vo;
    }


    public PayOrderVo queryPayOrderById(Long id) {
        //TODO 实际上这里应该加上支付成功状态的查询条件
        BizPayOrder payOrder = null;
        if(HutuUtils.isNotEmpty(id)){
            payOrder = this.getById(id);
        }
        HutuUtils.requireNotNull(payOrder, "根据id没有找到对应订单");
        String itemInfo = payOrder.getItemInfo();
        PayOrderVo vo = HutuUtils.transToObject(payOrder, PayOrderVo.class);
        BizShopInfo shopInfo = bizShopInfoService.getById(vo.getShopId());
        vo.setPayOrderId(id);
        vo.setShopAddress(shopInfo.getAddress());
        vo.setShopName(shopInfo.getShopName());
        if (StringUtils.isNotEmpty(itemInfo)) {
            List<OrderItemVo> orderItemVos = JSONArray.parseArray(itemInfo, OrderItemVo.class);
            vo.setItemInfo(orderItemVos);
        }
        return vo;
    }


    public BizPayOrder getPayOrderById(Long id, String orderNo) {
        BizPayOrder payOrder;
        if (HutuUtils.isNotEmpty(id)) {
            payOrder = this.getById(id);
        } else {
            payOrder = this.getOne(new LambdaQueryWrapper<BizPayOrder>().eq(BizPayOrder::getOrderNo, orderNo));
        }
        HutuUtils.requireNotNull(payOrder, "没有找到对应订单");
        return payOrder;
    }


    public PayOrderVo orderQuery(Long id) {
        BizPayOrder payOrder = this.getById(id);
        HutuUtils.requireNotNull(payOrder, "没有找到对应订单");
        Long orderId = payOrder.getId();
        PayOrderVo vo = HutuUtils.transToObject(payOrder, PayOrderVo.class);
        vo.setPayOrderId(orderId);
        return vo;
    }


    public FrontOrderVo orderFront(Long orderId) {
        BizPayOrder payOrder = this.getById(orderId);
        HutuUtils.requireNotNull(payOrder, "没有找到订单");
        Date settleTime = payOrder.getSettleTime();
        HutuUtils.requireNotNull(settleTime, "没有找到订单结算时间");
        return bizPayOrderMapper.orderFrontQueryByOrderId(HutuUtils.FORMAT.DATETIME.format(settleTime), orderId);
    }


    public boolean updateOrderPayStatus(Long orderId, String extra) {
        BizPayOrder payOrder = this.getById(orderId);
        payOrder.setOrderStatus(OrderStatusEnum.PAYED.getVal());
        payOrder.setSettleTime(new Date());
        LambdaUpdateWrapper<BizPayOrder> wrapper = new LambdaUpdateWrapper<BizPayOrder>().eq(BizPayOrder::getId, orderId)
                .eq(BizPayOrder::getOrderStatus, OrderStatusEnum.INIT.getVal())
                .set(BizPayOrder::getOrderStatus, OrderStatusEnum.PAYED.getVal())
                .set(HutuUtils.isNotEmpty(extra), BizPayOrder::getRemark, OrderStatusEnum.PAYED.getVal())
                .set(BizPayOrder::getSettleTime, new Date());
        return this.update(wrapper);
    }

    public void doOrderPayedBusiness(BizPayOrder payOrder){
        redisUtils.removeUnPayOrderCache(payOrder.getUserId(), payOrder.getId());
        OrderWsMessageInfo messageInfo = HutuUtils.transToObject(payOrder, OrderWsMessageInfo.class);
        messageInfo.setMessageType(OrderMessageType.IN.getVal());
        this.sendMessageAsync(payOrder.getShopId(), messageInfo);
    }

//    @Async
    public void sendMessageAsync(Long shopId, OrderWsMessageInfo messageInfo) {
        bizPayOrderWsClient.sendMessage2Shop(shopId, JSONObject.parseObject(messageInfo.toJSONString()));
    }


    public Page<MyPayOrderVo> queryMyOrder(MyOrderQueryDTO dto, Integer page, Integer size) {
//        long id = StpUtil.getLoginIdAsLong();
        long id = 1L;
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


    public void deleteOrder(Long orderId) {
        BizPayOrder payOrder = this.getById(orderId);
        HutuUtils.requireNotNull(payOrder, "没有找到对应订单");
        payOrder.setUserDelFlag(YesOrNoEnum.YES.getValue());
        this.updateById(payOrder);
    }


    public void finishOrder(Long orderId) {
        BizPayOrder payOrder = this.getById(orderId);
        HutuUtils.requireNotNull(payOrder, "没有找到对应订单");
        payOrder.setOrderStatus(OrderStatusEnum.FINISH.getVal());
        this.updateById(payOrder);
        OrderWsMessageInfo messageInfo = HutuUtils.transToObject(payOrder, OrderWsMessageInfo.class);
        messageInfo.setMessageType(OrderMessageType.FINISH.getVal());
        this.sendMessageAsync(payOrder.getShopId(), messageInfo);
    }


    public void cancelOrder(Long orderId) {
        BizPayOrder payOrder = this.getById(orderId);
        HutuUtils.requireNotNull(payOrder, "没有找到对应订单");
        payOrder.setOrderStatus(OrderStatusEnum.EXPIRE.getVal());
        LambdaUpdateWrapper<BizPayOrder> wp = new LambdaUpdateWrapper<BizPayOrder>()
                .eq(BizPayOrder::getId, orderId)
                .eq(BizPayOrder::getOrderStatus, OrderStatusEnum.INIT.getVal())
                .set(BizPayOrder::getOrderStatus, OrderStatusEnum.EXPIRE.getVal());
        boolean b = this.update(wp);
        if (!b) {
            throw new HutuBizException("订单状态已经发生变化");
        }
        redisUtils.removeUnPayOrderCache(payOrder.getUserId(), payOrder.getId());
    }


    public void saveRefundOrder(RefundDTO refundDTO) {
        BizPayOrder payOrder = this.getPayOrderById(null, refundDTO.getPayNo());
        if(payOrder.getTotalAmount().compareTo(refundDTO.getRefundAmount()) < 0){
            throw new HutuBizException("退款金额大于支付金额");
        }
        Long userId = payOrder.getUserId();
        String orderNo = snowflakeIdWorker.nextId() + "";
        BizPayOrder refundOrder = new BizPayOrder();
        refundOrder.setPayOrderId(payOrder.getId());
        refundOrder.setOrderNo(orderNo);
        refundOrder.setType(PayTypeEnum.REFUND.getVal());
        refundOrder.setSettleTime(new Date());
        refundOrder.setOrderStatus(OrderStatusEnum.INIT.getVal());
        refundOrder.setTotalAmount(refundDTO.getRefundAmount());
        refundOrder.setUserId(userId);
        refundOrder.setPayCode(payOrder.getPayCode());
        refundOrder.setShopId(payOrder.getShopId());
        this.save(refundOrder);
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
//        long id = StpUtil.getLoginIdAsLong();
        long id = 1L;
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

        String nextShopOrderNo = sequenceUtils.nextSeq(prefix);
        String orderNo = snowflakeIdWorker.nextId() + "";
        order.setShopOrderNo(nextShopOrderNo);
        order.setOrderNo(orderNo);
        order.setOrderStatus(OrderStatusEnum.INIT.getVal());
        order.setUserId(id);
        order.setItemPiece(items.size());
        order.setWaitTime(items.stream().mapToInt(OrderItemDTO::getExpectMakeTime).sum());
        order.setType(PayTypeEnum.PAY.getVal());
//        order.setPayCode(dto.getPayCode());
        this.save(order);

        Date currentDate = new Date();

        SettleOrderVo vo = HutuUtils.transToObject(dto, SettleOrderVo.class);
        vo.setOrderNo(orderNo);
        vo.setShopOrderNo(nextShopOrderNo);
        vo.setOrderId(order.getId());
        vo.setCreateTime(currentDate);
        vo.setExpireTime(businessConfig.getOrderExpire());
        vo.setUserId(id);

        HutuThreadPoolExecutor.threadPool.submit(() -> {
            userPayOrderQueueHandler.pushData2Queue(order, businessConfig.getOrderExpire());
            redisUtils.hset(RedisUtils.Keys.NON_PAYMENT_ORDER_KEY_PREFIX, HutuUtils.joinByColon(order.getId() + id), JSONObject.toJSONString(order),
                    TimeEnum.DAY.getSeconds());
            log.info("订单[{}]进入监听队列", order.getId());
        });
        return vo;
    }
}
