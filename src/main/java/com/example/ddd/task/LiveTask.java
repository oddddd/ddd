package com.example.ddd.task;

import com.alibaba.fastjson.JSON;
import com.example.ddd.crawler.controller.CrawlerController;
import com.example.ddd.crawler.model.LiveModel;
import com.example.ddd.library.RedisDao;
import com.example.ddd.netty.handler.ServerHandler;
import com.example.ddd.netty.handler.facade.PackFacade;
import com.example.ddd.netty.handler.model.ChannelListModel;
import com.example.ddd.netty.handler.model.DataModel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * LiveTask
 *
 * @author wjp
 * @desc
 * @date Created in 下午5:07 2018/5/28
 */
@Configuration
@EnableScheduling
public class LiveTask {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    ServerHandler serverHandler;
//    @Autowired
//    CrawlerController crawlerController;
//    @Autowired
//    PackFacade packFacade;
//    @Autowired
//    RedisDao redisDao;
//    @Autowired
//    Environment env;
//
//    private boolean liveOneBotten = true;
//    @Scheduled(cron = "0/10 * * * * ?") // 每10秒执行一次
//    public void liveOne() {
//        if (liveOneBotten) {
//            liveOneBotten = false;
//            logger.info("liveOne - start");
//            try{
//                String matchRoomSocketKey = env.getProperty("redis.matchRoomSocketKey");
//                String matchRoomSocketDetailKey = env.getProperty("redis.matchRoomSocketDetail");
//                String matchLiveListKey = env.getProperty("redis.matchLiveListKey");
//                String matchLiveSocketPushNum = env.getProperty("redis.matchLiveSocketPushNum");
//                ChannelListModel channelListModel = ChannelListModel.getInstance();
//                Map<String, ChannelHandlerContext> channelList = channelListModel.getChannelList();
//                List<String> roomList = redisDao.getObjectKeyValue(matchLiveListKey);
//                for(String room : roomList){
//                    try{
//                        List<String> userSocketList = redisDao.getObjectKeyValue(matchRoomSocketKey+room);
//                        List<String> newUserSocketList = new ArrayList<>();
//                        if(userSocketList.size()>0){
//                            matchRoomSocketDetailKey = matchRoomSocketDetailKey+room;
//                            List<LiveModel> liveModelsList = redisDao.getObjectKeyValue(matchRoomSocketDetailKey);
//                            Integer num = redisDao.getObjectKeyValue(matchLiveSocketPushNum+room);
//                            num = num == null ? 0 : num;
//                            if(liveModelsList.size() > num){
//                                for(String userSocket : userSocketList){
//                                    try {
//                                        ChannelHandlerContext channelHandlerContext = channelList.get(userSocket);
//                                        if(channelHandlerContext == null){
//                                            continue;
//                                        }
//                                        Channel channel = channelHandlerContext.channel();
//                                        if (channel == null || !channel.isActive()) {
//                                            channelListModel.delChannelList(userSocket);
//                                            continue;
//                                        }
//                                        LiveModel liveModel = liveModelsList.get(num + 1);
//                                        if (liveModel == null) {
//                                            break;
//                                        }
//                                        String objJson = JSON.toJSONString(liveModel);
//                                        DataModel dataModel = new DataModel();
//                                        dataModel.setProtocol("03");
//                                        dataModel.setBody(objJson);
//                                        packFacade.write(channelHandlerContext, dataModel);
//                                        newUserSocketList.add(userSocket);
//                                    }catch (Exception e){
//                                        logger.info("live For - log : minor error : " + e);
//                                    }
//                                }
//                                num++;
//                                redisDao.setObjectKeyValueTime(matchLiveSocketPushNum+room,num,7200);
//                            }
//                        }
//                        redisDao.setObjectKeyValueTime(matchRoomSocketKey+room,newUserSocketList,7200);
//                    }catch (Exception e){
//                        logger.info("live For - log : minor error : " + e);
//                    }
//                }
//                logger.info("liveOne - log : ok");
//            }catch (Exception e){
//                logger.info("liveOne - log : ERROR : " + e.getMessage());
//            }
//
//            logger.info("liveOne - end");
//            liveOneBotten = true;
//        }else{
//            logger.info("liveOne - before not end");
//        }
//    }
//
//    private boolean liveTwoBotten = true;
//    @Scheduled(cron = "0/40 * * * * ?") // 每40秒执行一次
//    public void liveTwo() throws IOException, SAXException {
//        if (liveTwoBotten){
//            liveTwoBotten = false;
//            logger.info("liveTwo - start");
//            try{
//                String ret = crawlerController.crawlerOkoooSoccerList("http://www.okooo.com/livecenter/football/");
//                logger.info("liveTwo - log : "+ret);
//            }catch (Exception e){
//                logger.info("liveOne - log : ERROR : " + e.getMessage());
//            }
//            logger.info("liveTwo - end");
//            liveTwoBotten = true;
//        }else{
//            logger.info("liveTwo - before not end");
//        }
//    }
//
//    private boolean liveThreeBotten = true;
//    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
//    public void liveThree() {
//        if (liveThreeBotten){
//            liveThreeBotten = false;
//            logger.info("liveThree - start");
//            try{
//                String ret = crawlerController.crawlerOkoooSoccerDetail();
//                logger.info("liveThree - log : "+ ret);
//            }catch (Exception e){
//                logger.info("liveOne - log : ERROR : " + e.getMessage());
//            }
//            logger.info("liveThree - end");
//            liveThreeBotten = true;
//        }else{
//            logger.info("liveThree - before not end");
//        }
//    }
}
