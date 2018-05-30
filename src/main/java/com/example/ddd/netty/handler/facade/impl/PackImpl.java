package com.example.ddd.netty.handler.facade.impl;

import com.example.ddd.library.MD5Utils;
import com.example.ddd.netty.handler.facade.PackFacade;
import com.example.ddd.netty.handler.model.DataModel;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * PackImpl
 *
 * @author wjp
 * @desc
 * @date Created in 下午2:57 2018/5/22
 */
@Service
public class PackImpl implements PackFacade{

    /**
     * 数据头
     */
    private String head = "start";
    /**
     * token
     */
    private String token = "key";
    /**
     * 数据尾
     */
    private String fail = "end";
    /**
     * 分隔符
     */
    private String separator = ",0o0,";

    @Override
    public String sign(DataModel model) {
        return MD5Utils.getMd5(model.getProtocol()+model.getBody()+model.getTime()+token);
    }

    @Override
    public boolean verifySign(DataModel model) {
        String sign = MD5Utils.getMd5(model.getProtocol()+model.getBody()+model.getTime()+token);
        return model.getToken().equals(sign);
    }

    @Override
    public List<DataModel> parseData(String str) {
        String[] strList = str.split(fail);
        List<DataModel> listModel = new ArrayList<>();
        for (String strDetail : strList){
            try {
                if(strDetail!=null){
                    String[] data = strDetail.split(separator);
                    DataModel model = new DataModel();
                    model.setHead(data[0]);
                    model.setProtocol(data[1]);
                    model.setBody(data[2]);
                    model.setTime(data[3]);
                    model.setToken(data[4]);
                    listModel.add(model);
            }
            }catch (Exception e){
                continue;
            }
        }
        return listModel;
    }

    @Override
    public ChannelFuture write(ChannelHandlerContext ctx, DataModel model) {
        String sign = sign(model);
        Long time = System.currentTimeMillis() / 1000;
        String response = head+separator+model.getProtocol()+separator+model.getBody()+separator+time.toString()+separator+sign+separator+fail;
        return ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
    }
}
