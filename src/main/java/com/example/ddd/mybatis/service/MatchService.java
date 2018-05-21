package com.example.ddd.mybatis.service;

import com.example.ddd.mybatis.model.LiveStreamBindModel;
import com.example.ddd.mybatis.model.MatchModel;

import java.util.List;

public interface MatchService {
    List<MatchModel> selectMatchByKickoffTime(String kickoffTime);
    Integer insertLiveStreamBind(LiveStreamBindModel model);
}
