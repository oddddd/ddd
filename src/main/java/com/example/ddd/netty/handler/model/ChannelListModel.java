package com.example.ddd.netty.handler.model;

import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ChannelListModel
 *
 * @author wjp
 * @desc
 * @date Created in 下午3:56 2018/5/30
 */
public class ChannelListModel {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static ChannelListModel channelListModel;

    public static ChannelListModel getInstance() {
        synchronized (ChannelListModel.class) {
            if(channelListModel == null){
                channelListModel = new ChannelListModel();
            }
        }
        return channelListModel;
    }

    public void addChannelList(String ctxId,ChannelHandlerContext ctx){
        channelList.put(ctxId,ctx);
    }

    public void delChannelList(String ctxId){
        channelList.remove(ctxId);
    }

    private Map<String,ChannelHandlerContext> channelList  = new ConcurrentHashMap<String,ChannelHandlerContext>();
    /**
     * Getter for property 'channelList'.
     *
     * @return Value for property 'channelList'.
     */
    public Map<String, ChannelHandlerContext> getChannelList() {
        logger.info("getChannelList - size : "+channelList.size());
        return channelList;
    }

    /**
     * Setter for property 'channelList'.
     *
     * @param channelList Value to set for property 'channelList'.
     */
    public void setChannelList(Map<String, ChannelHandlerContext> channelList) {
        this.channelList = channelList;
    }
}
