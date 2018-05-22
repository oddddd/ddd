package com.example.ddd;
import com.example.ddd.netty.initializer.HeartbeatHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DddNettyTcpApplication
 *
 * @author wjp
 * @desc
 * @date Created in 下午5:45 2018/5/21
 */
@SpringBootApplication
public class DddNettyTcpApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DddNettyTcpApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        //看起来想管理连接的进程
        EventLoopGroup bossGroup = new NioEventLoopGroup(2); // (1)
        //看起来像处理请求的进程
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new HeartbeatHandlerInitializer() { // (4)
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5) // 设置tcp协议的请求等待队列
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO)); // (6) //保持连接
            ChannelFuture f = b.bind(9903).sync(); // (7)
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
