package com.example.ddd.mybatis.service.impl;

import com.example.ddd.library.DateUtil;
import com.example.ddd.mybatis.mapper.LiveStreamBindMapper;
import com.example.ddd.mybatis.mapper.MatchMapper;
import com.example.ddd.mybatis.model.LiveStreamBindModel;
import com.example.ddd.mybatis.model.MatchModel;
import com.example.ddd.mybatis.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * MatchImpl
 *
 * @author wjp
 * @desc
 * @date Created in 下午3:37 2018/5/18
 */
@Repository
public class MatchImpl implements MatchService{
    @Autowired
    MatchMapper matchMapper;
    @Autowired
    LiveStreamBindMapper liveStreamBindMapper;
    @Override
    public List<MatchModel> selectMatchByKickoffTime(String kickoffTime) {
        Timestamp k = DateUtil.str2TimeStamp(kickoffTime);
//        MatchModel select = new MatchModel();
//        select.setKickoffTime(kickoffTime);
        return matchMapper.findMatchByKickoffTime(k);
    }

    @Override
    public List<MatchModel> selectMatchByMatchId(String matchId) {
        return matchMapper.findMatchByMatchId(matchId);
    }

    @Override
    public MatchModel selectMatchById(Integer id) {
        return matchMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateMatchPointByMatchId(String matchId, Integer homePoint, Integer awayPoint) {
        return matchMapper.updateMatchPointByMatchId(matchId,homePoint,awayPoint);
    }

    @Override
    public Integer updateOddsUrlById(Integer id, String oddsUrl) {
        return matchMapper.updateOddsUrlById(id,oddsUrl);
    }

    @Override
    public Integer insertLiveStreamBind(LiveStreamBindModel model) {
        try{
            if(model.getFid() == null || model.getLiveUrl() == null || model.getLiveName() == null){
                return null;
            }
            MatchModel hasMatch = matchMapper.selectByPrimaryKey(model.getFid());
            if(hasMatch == null){
                return null;
            }
            LiveStreamBindModel select = new LiveStreamBindModel();
            select.setFid(model.getFid());
            select.setLiveUrl(model.getLiveUrl());
            List<LiveStreamBindModel> hasLiveStreamBind = liveStreamBindMapper.select(select);
            if(hasLiveStreamBind.size()>0){
                return null;
            }
            liveStreamBindMapper.insertSelective(model);
            return model.getId();
        }catch (Exception e){
            return null;
        }
    }
}
