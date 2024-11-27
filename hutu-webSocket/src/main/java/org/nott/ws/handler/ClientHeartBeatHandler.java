package org.nott.ws.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.config.WebSocketConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-11-27
 */
@Slf4j
@Component
public class ClientHeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Resource
    private WebSocketConfig webSocketConfig;

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if(!(obj instanceof TextWebSocketFrame)){
            super.channelRead(channelHandlerContext,obj);
            return;
        }
        TextWebSocketFrame tf = (TextWebSocketFrame) obj;
        String text = tf.text();
        if(webSocketConfig.getHeartBeatMsg().equals(text)){
            channelHandlerContext.writeAndFlush(new TextWebSocketFrame("pong"));
            log.info("客户端[{}]心跳包",channelHandlerContext.channel().id().asShortText());
            return;
        }
        super.channelRead(channelHandlerContext,tf);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 读写空闲状态事件
        if (evt instanceof IdleStateEvent && webSocketConfig.isShowIdleMsg()) {
            IdleStateEvent event = (IdleStateEvent) evt;
            IdleState state = event.state();
            log.info("Client: [{}], Idle state: [{}]", ctx.channel().remoteAddress(), state);
        }
        super.userEventTriggered(ctx, evt);
    }
}
