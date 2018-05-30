package com.example.ddd.netty.handler.facade;

import com.example.ddd.netty.handler.model.DataModel;
import io.netty.channel.ChannelHandlerContext;

public interface ReadFacade {
    /**
     * 收到数据处理
     * @param ctx
     * @param ctxId
     * @param model
     */
    void protocolHandler(ChannelHandlerContext ctx,String ctxId,DataModel model);
}
