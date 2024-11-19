package org.nott.service.delayed.handler;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.delayed.DelayedTask;
import org.nott.common.redis.RedisUtils;
import org.nott.common.thread.pool.HutuThreadPoolExecutor;
import org.nott.common.utils.HutuUtils;
import org.nott.enums.OrderStatusEnum;
import org.nott.model.BizPayOrder;
import org.nott.service.service.IBizPayOrderService;
import org.nott.vo.SettleOrderVo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.DelayQueue;

/**
 * @author Nott
 * @date 2024-11-19
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UserPayOrderQueueHandler {

    private final RedisUtils redisUtils;

    private DelayQueue<DelayedTask<SettleOrderVo>> payOrderQueue = new DelayQueue<>();

    private final IBizPayOrderService payOrderService;

    public static final String NON_PAYMENT_ORDER_KEY_PREFIX = "NON-PAYMENT-ORDER:";

    @PostConstruct
    public void handleOrderExpire() {
        HutuThreadPoolExecutor.threadPool.submit(this::doOrderExpire);
        log.info("向线程池提交检查订单过期线程");
    }

    public void doOrderExpire() {
        while (true) {
            try {
                DelayedTask<SettleOrderVo> delayedTask = payOrderQueue.take();
                SettleOrderVo data = delayedTask.getData();
                log.info("开始处理过期订单信息，订单id：[{}]", data.getOrderId());
                Long userId = data.getUserId();
                Object object = redisUtils.get(NON_PAYMENT_ORDER_KEY_PREFIX + userId);
                if (HutuUtils.isEmpty(object)) {
                    log.info("订单已完成，无需处理");
                    return;
                }
                LambdaUpdateWrapper<BizPayOrder> wrapper = new LambdaUpdateWrapper<>();
                wrapper.eq(BizPayOrder::getId, data.getOrderId())
                        .set(BizPayOrder::getOrderStatus, OrderStatusEnum.EXPIRE.getVal());
                payOrderService.update(wrapper);
                redisUtils.delByKey(NON_PAYMENT_ORDER_KEY_PREFIX + userId);
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
    }

    public void pushData2Queue(SettleOrderVo data, long expireTime) {
        DelayedTask<SettleOrderVo> settleOrderVoDelayedTask = new DelayedTask<>();
        settleOrderVoDelayedTask.setData(data);
        settleOrderVoDelayedTask.setAvailableTime(expireTime);
        payOrderQueue.offer(settleOrderVoDelayedTask);
    }

}
