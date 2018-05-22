package com.example.ddd.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ServerHandler
 *
 * @author wjp
 * @desc
 * @date Created in 下午5:53 2018/5/21
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private Map<String,ChannelHandlerContext> channelList  = new ConcurrentHashMap<String,ChannelHandlerContext>();

    //心跳要用的东西不知道干啥的
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat",CharsetUtil.UTF_8));// 1

    // 这个好像是捕获收到消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException { // (2)
        String ctxId = ctx.channel().id().asLongText();
        channelList.put(ctxId,ctx);
        ByteBuf buf = (ByteBuf)msg;
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        System.out.print(buf.toString(CharsetUtil.UTF_8));
        String request = new String(data, "utf-8");
        System.out.println("Server: " + request);
        //写给客户端
        String response = "我是反馈的信息";
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }

    // 好像是捕获断开连接的
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        cause.printStackTrace();
        ctx.close();
    }

    // 这个好像是捕获连上服务器
    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        System.out.println( ctx.channel().remoteAddress()+"连接上了：");
    }

    // 这个好像是捕获心跳触发的事件
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) { // 2
            IdleStateEvent event = (IdleStateEvent) evt;
            String type = "";
            if (event.state() == IdleState.READER_IDLE) {
                type = "read idle";
            } else if (event.state() == IdleState.WRITER_IDLE) {
                type = "write idle";
            } else if (event.state() == IdleState.ALL_IDLE) {
                type = "all idle";
            }
            ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(
                    ChannelFutureListener.CLOSE_ON_FAILURE); // 3
            System.out.println( ctx.channel().remoteAddress()+"超时类型：" + type);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
