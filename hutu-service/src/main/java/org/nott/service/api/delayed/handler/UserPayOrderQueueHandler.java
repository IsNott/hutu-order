package org.nott.service.api.delayed.handler;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.config.BusinessConfig;
import org.nott.common.delayed.DelayedTask;
import org.nott.common.redis.RedisUtils;
import org.nott.common.thread.pool.HutuThreadPoolExecutor;
import org.nott.common.utils.HutuUtils;
import org.nott.common.utils.SpringContextUtil;
import org.nott.enums.HandleOrderExpireType;
import org.nott.enums.OrderStatusEnum;
import org.nott.model.BizPayOrder;
import org.nott.service.api.IBizPayOrderService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Nott
 * @date 2024-11-19
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UserPayOrderQueueHandler {

    private final RedisUtils redisUtils;

    private final DelayQueue<DelayedTask<BizPayOrder>> payOrderQueue = new DelayQueue<>();

    public static final String NON_PAYMENT_ORDER_KEY_PREFIX = "NON-PAYMENT-ORDER:";

    private final BusinessConfig businessConfig;

    @PostConstruct
    public void handleOrderExpire() {
        if (HandleOrderExpireType.DELAYED_QUEUE.getName().equals(businessConfig.getCheckType())) {
            HutuThreadPoolExecutor.threadPool.submit(this::doOrderExpire);
            log.info("向线程池提交检查订单过期线程");
        }
    }

    public void doOrderExpire() {
        IBizPayOrderService payOrderService = SpringContextUtil.getBean(IBizPayOrderService.class);
        while (true) {
            try {
                DelayedTask<BizPayOrder> delayedTask = payOrderQueue.take();
                BizPayOrder data = delayedTask.getData();
                log.info("开始处理过期订单信息，订单id：[{}]", data.getId());
                Long userId = data.getUserId();
                Object object = redisUtils.hget(NON_PAYMENT_ORDER_KEY_PREFIX + userId, data.getId() + "");
                if (HutuUtils.isEmpty(object)) {
                    log.info("订单已完成，无需处理");
                    return;
                }
                BizPayOrder payOrder = payOrderService.getById(data.getId());
                if(!payOrder.getOrderStatus().equals(OrderStatusEnum.INIT.getVal())){
                    log.info("订单已更新状态，无需处理");
                    return;
                }
                LambdaUpdateWrapper<BizPayOrder> wrapper = new LambdaUpdateWrapper<>();
                wrapper.eq(BizPayOrder::getId, data.getId())
                        .set(BizPayOrder::getOrderStatus, OrderStatusEnum.EXPIRE.getVal());
                payOrderService.update(wrapper);
                redisUtils.hdel(NON_PAYMENT_ORDER_KEY_PREFIX + userId, data.getId());
                log.info("订单已超时，设置为过期状态...");
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
    }

    public void pushData2Queue(BizPayOrder data, long expireTime) {
        DelayedTask<BizPayOrder> settleOrderVoDelayedTask = new DelayedTask<>();
        settleOrderVoDelayedTask.setData(data);
        settleOrderVoDelayedTask.setStartTime(data.getCreateTime().getTime());
        settleOrderVoDelayedTask.setDelayTime(TimeUnit.MILLISECONDS.convert(expireTime, TimeUnit.SECONDS));
        payOrderQueue.offer(settleOrderVoDelayedTask);
    }

}
