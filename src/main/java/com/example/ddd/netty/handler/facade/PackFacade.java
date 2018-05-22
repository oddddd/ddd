package com.example.ddd.netty.handler.facade;

import com.example.ddd.netty.handler.model.DataModel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public interface PackFacade {
    /**
     * 签名
     * @param model
     * @return
     */
    String sign(DataModel model);

    /**
     * 验签
     * @param model
     * @return
     */
    boolean verifySign(DataModel model);

    /**
     * 解析接收的数据
     * @param str
     * @return
     */
    List<DataModel> parseData(String str);

    /**
     * 发数据封装
     * @param ctx
     * @param model
     * @return
     */
    ChannelFuture write(ChannelHandlerContext ctx, DataModel model);
}
