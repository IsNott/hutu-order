package org.nott.ws.api;

import lombok.extern.slf4j.Slf4j;
import org.nott.common.ResponseEntity;
import org.nott.feign.BizPayOrderWsClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nott
 * @date 2024-11-27
 */
@Slf4j
@RestController
@RequestMapping("/payOrder-ws/")
public class BizPayOrderWsApi implements BizPayOrderWsClient {

    @PostMapping("sendMessage2Shop/{shopId}")
    public ResponseEntity<?> sendMessage2Shop(@PathVariable("shopId") Long shopId, @RequestBody Object message) {
        //TODO 客户端下单完成 -> 发送到叫号大屏
        log.info("收到feignClient调用");
        return ResponseEntity.success();
    }
}
