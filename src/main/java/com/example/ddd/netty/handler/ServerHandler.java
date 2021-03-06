package com.example.ddd.netty.handler;

import com.example.ddd.netty.handler.facade.PackFacade;
import com.example.ddd.netty.handler.facade.ReadFacade;
import com.example.ddd.netty.handler.model.ChannelListModel;
import com.example.ddd.netty.handler.model.DataModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * ServerHandler
 *
 * @author wjp
 * @desc
 * @date Created in 下午5:53 2018/5/21
 */
@Component
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PackFacade packFacade;

    @Autowired
    ReadFacade readFacade;

    /** {@inheritDoc} */ // 这个好像是捕获收到消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException { // (2)
        try{
            String ctxId = ctx.channel().id().asLongText();
            ByteBuf buf = (ByteBuf)msg;
            String str = buf.toString(CharsetUtil.UTF_8);
            List<DataModel> modelList = packFacade.parseData(str);
            for(DataModel model : modelList){
                if(packFacade.verifySign(model)){
                    readFacade.protocolHandler(ctx,ctxId,model);
                }else{
                    String response = "Sign Error";
                    ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
                }
            }
        }catch (Exception e){
            String response = "Read Error";
            ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
        }
    }

    /** {@inheritDoc} */ // 好像是捕获断开连接的
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        cause.printStackTrace();
        ctx.close();
    }

    /** {@inheritDoc} */ // 这个好像是捕获连上服务器
    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        System.out.println( ctx.channel().remoteAddress()+"连接上了：");
    }

    /** {@inheritDoc} */
    @Override
    public void channelUnregistered(final ChannelHandlerContext ctx) { // (1)
        System.out.println( ctx.channel().remoteAddress()+"断开连接：");
    }


    /** {@inheritDoc} */ // 这个好像是捕获心跳触发的事件
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) { // 2
//            IdleStateEvent event = (IdleStateEvent) evt;
//            String type = "";
//            if (event.state() == IdleState.READER_IDLE) {
//                type = "read idle";
//            } else if (event.state() == IdleState.WRITER_IDLE) {
//                type = "write idle";
//            } else if (event.state() == IdleState.ALL_IDLE) {
//                type = "all idle";
//            }
//            ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(
//                    ChannelFutureListener.CLOSE_ON_FAILURE); // 3
//            System.out.println( ctx.channel().remoteAddress()+"超时类型：" + type);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }


}
