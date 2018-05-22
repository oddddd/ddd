package com.example.ddd.netty;

import com.example.ddd.netty.initializer.HeartbeatHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * TCPServer
 *
 * @author wjp
 * @desc
 * @date Created in 下午4:50 2018/5/22
 */
@Service
public class TCPServer {

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    @Autowired
    HeartbeatHandlerInitializer heartbeatHandlerInitializer;

    public void start(int port) throws Exception {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //看起来想管理连接的进程
            bossGroup = new NioEventLoopGroup(2); // (1)
            //看起来像处理请求的进程
            workerGroup = new NioEventLoopGroup(4);
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(heartbeatHandlerInitializer)
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5) // 设置tcp协议的请求等待队列
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO)); // (6) //保持连接
            ChannelFuture f = serverBootstrap.bind(port).sync(); // (7)
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
