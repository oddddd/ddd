package com.example.ddd.netty.initializer;

import com.example.ddd.netty.handler.ServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * HeartbeatHandlerInitializer
 *
 * @author wjp
 * @desc
 * @date Created in 下午5:51 2018/5/21
 */
public class HeartbeatHandlerInitializer extends ChannelInitializer {
    private static final int READ_IDEL_TIME_OUT = 10; // 读超时
    private static final int WRITE_IDEL_TIME_OUT = 10;// 写超时
    private static final int ALL_IDEL_TIME_OUT = 20; // 所有超时

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(READ_IDEL_TIME_OUT,
                WRITE_IDEL_TIME_OUT, ALL_IDEL_TIME_OUT, TimeUnit.SECONDS)); // 1
        pipeline.addLast(new ServerHandler());
    }
}
