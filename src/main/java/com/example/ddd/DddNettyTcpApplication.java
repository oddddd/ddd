package com.example.ddd;
import com.example.ddd.netty.TCPServer;
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
