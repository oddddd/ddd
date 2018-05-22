package com.example.ddd;
import com.example.ddd.netty.TCPServer;
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
import org.springframework.context.ConfigurableApplicationContext;

/**
 * DddNettyTcpApplication
 *
 * @author wjp
 * @desc
 * @date Created in 下午5:45 2018/5/21
 */
@SpringBootApplication
public class DddNettyTcpApplication{
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(DddNettyTcpApplication.class, args);
        TCPServer tcpServer = context.getBean(TCPServer.class);
        tcpServer.start(9903);
    }
}
