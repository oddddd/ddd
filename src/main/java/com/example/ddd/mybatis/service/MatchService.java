package com.example.ddd.mybatis.service;

import com.example.ddd.mybatis.model.LiveStreamBindModel;
import com.example.ddd.mybatis.model.MatchModel;

import java.util.List;

public interface MatchService {
    List<MatchModel> selectMatchByKickoffTime(String kickoffTime);
    List<MatchModel> selectMatchByMatchId(String matchId);
    Integer updateMatchPointByMatchId(String matchId,Integer homePoint,Integer awayPoint);
    Integer insertLiveStreamBind(LiveStreamBindModel model);
}
