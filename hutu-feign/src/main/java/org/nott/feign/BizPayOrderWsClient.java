package org.nott.feign;

import com.alibaba.fastjson.JSONObject;
import org.nott.common.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "payOrder-ws", url = "http://localhost:8888", path = "/ws/payOrder/")
public interface BizPayOrderWsClient {

    @PostMapping("sendMessage2Shop/{shopId}")
    @ResponseBody
    ResponseEntity<?> sendMessage2Shop(@PathVariable("shopId") Long shopId,@RequestBody JSONObject message);
}
