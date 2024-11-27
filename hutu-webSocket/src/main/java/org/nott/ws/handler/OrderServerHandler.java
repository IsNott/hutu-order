package org.nott.ws.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.utils.RequestUriUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nott
 * @date 2024-11-26
 */
@Slf4j
public class OrderServerHandler extends ChannelInboundHandlerAdapter{

    public final static ConcurrentHashMap<String, ChannelGroup> SHOP_CHANNEL_MAP = new ConcurrentHashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // TODO 按照门店分组管理客户端
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            Map<String, String> params = RequestUriUtils.getParams(request.uri());
            log.info("参数:" + JSON.toJSONString(params));
        }

        if(msg instanceof TextWebSocketFrame){
            log.info("接收到信息: " + ((TextWebSocketFrame) msg).text());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接：[{}]",ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端断开连接：[{}]",ctx.channel().remoteAddress());
        super.channelInactive(ctx);
    }
}
