package org.nott.feign;

import com.alibaba.fastjson.JSONObject;
import org.nott.common.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

// 没有使用注册中心，使用url方式调用
@FeignClient(value = "payOrder-ws",url = "localhost:8888/payOrder-ws")
public interface BizPayOrderWsClient {

    @PostMapping("sendMessage2Shop/{shopId}")
    @ResponseBody
    ResponseEntity<?> sendMessage2Shop(@PathVariable("shopId") Long shopId,@RequestBody JSONObject message);
}
