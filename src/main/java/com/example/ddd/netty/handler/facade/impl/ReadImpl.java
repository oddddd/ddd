package com.example.ddd.netty.handler.facade.impl;

import com.example.ddd.library.RedisDao;
import com.example.ddd.netty.handler.facade.ReadFacade;
import com.example.ddd.netty.handler.model.DataModel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ReadImpl
 *
 * @author wjp
 * @desc
 * @date Created in 上午11:39 2018/5/22
 */
@Service
public class ReadImpl implements ReadFacade {

    private String redisUserKey = "socketUser_";
    private Integer redisUserTime = 7200;

    @Autowired
    RedisDao redisDao;

    @Override
    public void protocolHandler(ChannelHandlerContext ctx, DataModel model) {
        switch (model.getProtocol()){
            /*
             * 登录
             */
            case "01":

                break;
            /*
             * 进入房间
             */
            case "02":
                break;
            /*
             * 下发直播响应
             */
            case "03":
                break;
        }
    }
}
