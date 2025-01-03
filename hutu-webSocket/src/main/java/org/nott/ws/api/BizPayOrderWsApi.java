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
        String shopIdStr = shopId + "";
        if (!OrderServerHandler.SHOP_CHANNEL_MAP.containsKey(shopIdStr)) {
            log.error("未知的WebSocket分组:[{}]", shopId);
            return ResponseEntity.failure();
        }
        ChannelGroup channels = OrderServerHandler.SHOP_CHANNEL_MAP.get(shopIdStr);
        if (channels.isEmpty()) {
            log.error("WebSocket分组:[{}]，无连接客户端", shopId);
            return ResponseEntity.failure();
        }
        for (Channel channel : channels) {
            log.info("发送信息到门店[{}]下的客户端", shopIdStr);
            channel.writeAndFlush(new TextWebSocketFrame(message.toJSONString()));
        }
        return ResponseEntity.success();
    }
}
