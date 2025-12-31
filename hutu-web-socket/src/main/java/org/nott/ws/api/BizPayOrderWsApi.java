package org.nott.ws.api;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.ResponseEntity;
import org.nott.feign.BizPayOrderWsClient;
import org.nott.ws.handler.OrderServerHandler;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nott
 * @date 2024-11-27
 */
@Slf4j
@RestController
@RequestMapping("/ws/payOrder/")
public class BizPayOrderWsApi implements BizPayOrderWsClient {

    @PostMapping("sendMessage2Shop/{shopId}")
    public ResponseEntity<?> sendMessage2Shop(@PathVariable("shopId") Long shopId, @RequestBody JSONObject message) {
        log.info("WsApi -> sendMessage2Shop -> shopId:[{}], message:[{}]", shopId, message);
        // 客户端下单完成 -> 发送到叫号大屏
        checkAndSend(shopId, message);
        return ResponseEntity.success();
    }

    private static void checkAndSend(Long shopId, JSONObject message) {
        String shopIdStr = shopId + "";
        if (!OrderServerHandler.SHOP_CHANNEL_MAP.containsKey(shopIdStr)) {
            log.error("未知的WebSocket分组:[{}]", shopId);
            return;
        }
        ChannelGroup channels = OrderServerHandler.SHOP_CHANNEL_MAP.get(shopIdStr);
        if (channels.isEmpty()) {
            log.error("WebSocket分组:[{}]，无连接客户端", shopId);
            return;
        }
        for (Channel channel : channels) {
            log.info("发送信息到门店[{}]下的客户端", shopIdStr);
            channel.writeAndFlush(new TextWebSocketFrame(message.toJSONString()));
        }
    }

    @GetMapping("finishOrder/{shopId}/{shopOrderNO}")
    public ResponseEntity<?> finishOrder(@PathVariable("shopId") Long shopId, @PathVariable("shopOrderNO") String shopOrderNO) {
        log.info("WsApi -> finishOrder -> shopId:[{}], shopOrderNo:[{}]", shopId, shopOrderNO);
        JSONObject message = new JSONObject();
        message.put("action", "finish_order");
        message.put("shopOrderNo", shopOrderNO);
        checkAndSend(shopId, message);
        return ResponseEntity.success();
    }

    @GetMapping("takeOrder/{shopId}/{shopOrderNO}")
    public ResponseEntity<?> takeOrder(@PathVariable("shopId") Long shopId, @PathVariable("shopOrderNO") String shopOrderNO) {
        log.info("WsApi -> takeOrder -> shopId:[{}], shopOrderNo:[{}]", shopId, shopOrderNO);
        JSONObject message = new JSONObject();
        message.put("action", "take_order");
        message.put("shopOrderNo", shopOrderNO);
        checkAndSend(shopId, message);
        return ResponseEntity.success();
    }


}
