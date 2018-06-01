package com.example.ddd.mybatis.service;

import com.example.ddd.mybatis.model.LiveStreamBindModel;
import com.example.ddd.mybatis.model.MatchModel;

import java.util.List;

public interface MatchService {
    List<MatchModel> selectMatchByKickoffTime(String kickoffTime);
    List<MatchModel> selectMatchByMatchId(String matchId);
    MatchModel selectMatchById(Integer id);
    Integer updateMatchPointByMatchId(String matchId,Integer homePoint,Integer awayPoint);
    Integer updateOddsUrlById(Integer Id,String oddsUrl);
    Integer insertLiveStreamBind(LiveStreamBindModel model);

}
