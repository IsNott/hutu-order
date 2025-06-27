package org.nott.service.pay;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.PayDTO;
import org.nott.dto.PayGatewayDTO;
import org.nott.dto.RefundDTO;
import org.nott.model.BizPayOrder;
import org.nott.service.api.IBizPayOrderService;
import org.nott.vo.pay.BaseResultVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nott
 * @date 2025-3-31
 */

@Slf4j
@RestController
@RequestMapping("/pay")
public class PayAPI implements ApplicationListener<ContextRefreshedEvent> {

    public static final Map<String, PayService> payServiceMap = new ConcurrentHashMap<>(16);

    @Resource
    private IBizPayOrderService bizPayOrderService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        String[] beans = applicationContext.getBeanNamesForAnnotation(org.nott.common.annotation.PayApi.class);
        for (String beanName : beans) {
            PayService bean = (PayService) applicationContext.getBean(beanName);
            Class<? extends PayService> beanClass = bean.getClass();
            Class<?> superclass = beanClass.getSuperclass();
            org.nott.common.annotation.PayApi annotation = superclass.getAnnotation(org.nott.common.annotation.PayApi.class);
            log.info("Add PayService {} to map, PayCode: {}", beanName, annotation.value());
            payServiceMap.put(annotation.value(), bean);

        }
    }

    @PostMapping("/gateway")
    @SuppressWarnings("all")
    public ResponseEntity<?> gateway(@RequestBody PayGatewayDTO dto) {
        String orderNo = dto.getOrderNo();
        BizPayOrder payOrder = bizPayOrderService.getPayOrderById(null, orderNo);
        String payCode = payOrder.getPayCode();
        BaseResultVo resultVo = null;
        PayService payService = null;
        if(HutuUtils.isNotEmpty(payCode)){
            payService = payServiceMap.get(payCode);
        }
        switch (dto.getBusinessCode()) {
            default:
            case "query": {
                HutuUtils.requireNotNull(payCode, "支付code不能为空");
                HutuUtils.requireNotNull(orderNo, "订单号不能为空");
                resultVo = payService.query(orderNo);
                break;
            }
            case "refund": {
                HutuUtils.requireNotNull(payCode, "支付code不能为空");
                RefundDTO refundDTO = HutuUtils.transToObject(dto, RefundDTO.class);
                bizPayOrderService.saveRefundOrder(refundDTO);
                resultVo = payService.doRefund(refundDTO);
                break;
            }
            case "pay": {
//                PayDTO payDTO = dto.getPayDTO();
//                HutuUtils.requireNotNull(payDTO, "支付参数不能为空");
                PayDTO payDTO = HutuUtils.transToObject(dto, PayDTO.class);
                payDTO.setPayNo(dto.getOrderNo());
                payDTO.setPayOrderId(payOrder.getId());
                HutuUtils.requireAndNotNull("支付订单号和id不能同时为空", payDTO.getPayNo(), payDTO.getPayOrderId());
                String dtoPayCode = payDTO.getPayCode();
                HutuUtils.requireAndNotNull("没有选择支付方式", payCode, dtoPayCode);
                if(HutuUtils.isEmpty(payOrder.getPayCode())){
                    payOrder.setPayCode(dtoPayCode);
                    bizPayOrderService.update(new LambdaUpdateWrapper<BizPayOrder>()
                            .set(BizPayOrder::getPayCode, dtoPayCode)
                            .eq(BizPayOrder::getId, payOrder.getId()));
                    payService = payServiceMap.get(dtoPayCode);
                }
                resultVo = payService.doPay(payDTO);
                break;
            }
        }
        return ResponseEntity.successData(resultVo);
    }
}
