package org.nott.ws.initializer;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.nott.common.config.WebSocketConfig;
import org.nott.ws.handler.ClientHeartBeatHandler;
import org.nott.ws.handler.OrderServerHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-11-26
 */
@Component
public class ChannelInitializer extends io.netty.channel.ChannelInitializer<SocketChannel> {

    @Resource
    private WebSocketConfig webSocketConfig;

    @Resource
    private ClientHeartBeatHandler clientHeartBeatHandler;

    @Resource
    private OrderServerHandler orderServerHandler;

    @Override
    protected void initChannel(SocketChannel sc) {
        ChannelPipeline pipeline = sc.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(clientHeartBeatHandler);
        pipeline.addLast(orderServerHandler);
        pipeline.addLast(new IdleStateHandler(10,10,20));
        pipeline.addLast(new WebSocketServerProtocolHandler(webSocketConfig.getPath(),null,true,65536,false,true));

    }
}
