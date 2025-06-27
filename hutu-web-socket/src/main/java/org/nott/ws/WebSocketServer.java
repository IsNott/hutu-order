package org.nott.ws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.config.WebSocketConfig;
import org.nott.common.exception.WebSocketInitException;
import org.nott.ws.initializer.ChannelInitializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-11-26
 */
@Slf4j
@Component
public class WebSocketServer {

    @Resource
    private WebSocketConfig webSocketConfig;

    @Resource
    private ChannelInitializer channelInitializer;

    @PostConstruct
    public void run() {
        new Thread(this::doRun).start();
    }

    public void doRun() {
        log.info("Starting to run websocket server...");
        Integer port = webSocketConfig.getPort();
        Integer bossGroupThread = webSocketConfig.getBossGroup();
        Integer workerGroupThread = webSocketConfig.getWorkerGroup();
        NioEventLoopGroup boss = new NioEventLoopGroup(bossGroupThread > 0 ? bossGroupThread : 1);
        NioEventLoopGroup worker = new NioEventLoopGroup(workerGroupThread > 0 ? workerGroupThread : 1);

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.AUTO_READ,true)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(channelInitializer);
            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("WebSocket server running at port: [{}]", port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            throw new WebSocketInitException();
        } finally {
            boss.shutdownGracefully();
            log.info("WebSocket server already shut down...");
        }
    }
}
