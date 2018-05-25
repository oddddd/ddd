package com.example.ddd.netty.handler.facade;

import com.example.ddd.netty.handler.model.DataModel;
import io.netty.channel.ChannelHandlerContext;

public interface ReadFacade {
    void protocolHandler(ChannelHandlerContext ctx,String ctxId,DataModel model);
}
