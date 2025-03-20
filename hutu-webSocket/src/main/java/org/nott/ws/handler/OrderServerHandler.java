package org.nott.ws.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.config.WebSocketConfig;
import org.nott.common.utils.RequestUriUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Nott
 * @date 2024-11-26
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class OrderServerHandler extends ChannelInboundHandlerAdapter {

    private WebSocketServerHandshaker handshaker;

    @Resource
    private WebSocketConfig webSocketConfig;

    public final static ConcurrentHashMap<String, ChannelGroup> SHOP_CHANNEL_MAP = new ConcurrentHashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            // 按照门店分组管理客户端
            FullHttpRequest request = (FullHttpRequest) msg;
            Map<String, String> params = RequestUriUtils.getParams(request.uri());
            if (!params.containsKey("shopId")) {
                super.channelRead(ctx, msg);
            }
            String shopId = params.get("shopId");
            boolean inMap = SHOP_CHANNEL_MAP.containsKey(shopId);
            ChannelGroup group = inMap ? SHOP_CHANNEL_MAP.get(shopId) : new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            group.add(ctx.channel());
            if (!inMap) {
                SHOP_CHANNEL_MAP.put(shopId, group);
            }
            log.info("添加到门店[{}]下客户端组", shopId);
            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                    String.format("ws://localhost:%s/%s", webSocketConfig.getPort(), webSocketConfig.getPath()), null, true);
            handshaker = wsFactory.newHandshaker(request);
            if (handshaker == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else {
                handshaker.handshake(ctx.channel(), request);
            }
        }

        if (msg instanceof TextWebSocketFrame) {
            log.info("接收到信息: " + ((TextWebSocketFrame) msg).text());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接：[{}]", ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端断开连接：[{}]", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
