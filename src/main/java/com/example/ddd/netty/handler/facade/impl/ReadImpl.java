package com.example.ddd.netty.handler.facade.impl;

import com.example.ddd.library.RedisDao;
import com.example.ddd.netty.handler.facade.PackFacade;
import com.example.ddd.netty.handler.facade.ReadFacade;
import com.example.ddd.netty.handler.model.DataModel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private String matchLiveListKey = "LiveSocketMatchRoomList";
    private Integer matchLiveListTime = 7200;
    private String matchRoomSocketKey = "MatchRoomSocket_";
    private Integer matchRoomSocketTime = 7200;

    @Autowired
    RedisDao redisDao;
    @Autowired
    PackFacade packFacade;

    @Override
    public void protocolHandler(ChannelHandlerContext ctx,String ctxId, DataModel model) {
        DataModel writeModel = new DataModel();
        try{
            String loginUser = redisDao.getStringKeyValue(redisUserKey+ctxId);
            writeModel.setProtocol(model.getProtocol());
            if(loginUser == null && !model.getProtocol().equals("01")){
                writeModel.setBody("10");
                packFacade.write(ctx,writeModel);
                return;
            }
            switch (model.getProtocol()){
            /*
             * 登录
             */
                case "01":
                    if(loginUser == null){
                        writeModel.setBody("10");
                        packFacade.write(ctx,writeModel);
                        return;
                    }
                    redisDao.setStringKeyValueTime(redisUserKey+ctxId,"Tourist",redisUserTime);
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
                        if(matchLive.equals(model.getBody())){
                            List<String> userSocketList = redisDao.getObjectKeyValue(matchRoomSocketKey);
                            boolean hasUser = false;
                            for(String userSocket : userSocketList){
                                if(userSocket.equals(ctxId)){
                                    hasUser = true;
                                }
                            }
                            if(!hasUser){
                                userSocketList.add(ctxId);
                            }
                            redisDao.setObjectKeyValueTime(matchLiveListKey,userSocketList,matchRoomSocketTime);
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
