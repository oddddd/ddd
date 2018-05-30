package com.example.ddd.netty.handler.facade.impl;

import com.example.ddd.library.RedisDao;
import com.example.ddd.netty.handler.facade.PackFacade;
import com.example.ddd.netty.handler.facade.ReadFacade;
import com.example.ddd.netty.handler.model.ChannelListModel;
import com.example.ddd.netty.handler.model.DataModel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ReadImpl
 *
 * @author wjp
 * @desc
 * @date Created in 上午11:39 2018/5/22
 */
@Service
public class ReadImpl implements ReadFacade {
    @Autowired
    RedisDao redisDao;
    @Autowired
    PackFacade packFacade;
    @Autowired
    Environment env;

    @Override
    public void protocolHandler(ChannelHandlerContext ctx,String ctxId, DataModel model) {
        Integer redisTime = 7200;
        String redisUserKey = env.getProperty("redis.redisUserKey");
        String matchLiveListKey = env.getProperty("redis.matchLiveListKey");
        String matchRoomSocketKey = env.getProperty("redis.matchRoomSocketKey");
        DataModel writeModel = new DataModel();
        writeModel.setProtocol(model.getProtocol());
        try{
            String loginUser = redisDao.getStringKeyValue(redisUserKey+ctxId);
            if(loginUser == null && !model.getProtocol().equals("01")){
                writeModel.setBody("10");
                packFacade.write(ctx,writeModel);
            }
            switch (model.getProtocol()){
            /*
             * 登录
             */
                case "01":
                    ChannelListModel channelListModel = ChannelListModel.getInstance();
                    channelListModel.addChannelList(ctxId,ctx);
                    channelListModel.getChannelList();
                    redisDao.setStringKeyValueTime(redisUserKey+ctxId,"Tourist",redisTime);
                    break;
            /*
             * 进入房间
             */
                case "02":
                    List<String> matchLiveList;
                    try{
                        matchLiveList = redisDao.getObjectKeyValue(matchLiveListKey);
                    }catch (Exception e){
                        matchLiveList = new ArrayList<>();
                    }
                    if(matchLiveList == null){
                        break;
                    }
                    for(String matchLive : matchLiveList){
                        try{
                            if(matchLive.equals(model.getBody())){
                                List<String> userSocketList = redisDao.getObjectKeyValue(matchRoomSocketKey+matchLive);
                                if(userSocketList == null){
                                    userSocketList = new ArrayList<>();
                                    userSocketList.add(ctxId);
                                }else{
                                    boolean hasUser = false;
                                    for(String userSocket : userSocketList){
                                        if(userSocket.equals(ctxId)){
                                            hasUser = true;
                                        }
                                    }
                                    if(!hasUser){
                                        userSocketList.add(ctxId);
                                    }
                                }
                                redisDao.setObjectKeyValueTime(matchRoomSocketKey+matchLive,userSocketList,redisTime);
                            }
                        }catch (Exception e){
                            writeModel.setBody("11");
                            packFacade.write(ctx,writeModel);
                        }
                    }
                    break;
            /*
             * 下发直播响应
             */
                case "03":
                    break;
            }
            writeModel.setBody("1");
            packFacade.write(ctx,writeModel);
        }catch (Exception e){
            writeModel.setBody("0");
            packFacade.write(ctx,writeModel);
        }
    }
}
