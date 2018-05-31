package com.example.ddd.crawler.facade.impl;

import com.example.ddd.crawler.facade.CrawlerFacade;
import com.example.ddd.crawler.model.AllLiveModel;
import com.example.ddd.crawler.model.AllMatchModel;
import com.example.ddd.mybatis.mapper.ConsultAuthorMapper;
import com.example.ddd.mybatis.mapper.ConsultClassMapper;
import com.example.ddd.mybatis.mapper.LiveMatchBindMapper;
import com.example.ddd.mybatis.mapper.LiveMatchMapper;
import com.example.ddd.mybatis.model.*;
import com.example.ddd.mybatis.service.ConsultService;
import com.example.ddd.mybatis.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * CrawlerImpl
 *
 * @author wjp
 * @desc
 * @date Created in 下午6:15 2018/5/3
 */
@Service
public class CrawlerImpl implements CrawlerFacade{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ConsultAuthorMapper consultAuthorMapper;
    @Autowired
    ConsultClassMapper consultClassMapper;
    @Autowired
    LiveMatchMapper liveMatchMapper;
    @Autowired
    LiveMatchBindMapper liveMatchBindMapper;
    @Autowired
    ConsultService consultService;
    @Autowired
    MatchService matchService;
    @Override
    public Integer addAuthorByAuthorName(String name) {
        try{
            List<ConsultAuthorModel> hasAuthor = consultService.selectAuthorByName(name);
            if(hasAuthor.size() > 0 && hasAuthor.get(0).getId() != null)
                return hasAuthor.get(0).getId();
            ConsultAuthorModel insert = new ConsultAuthorModel();
            insert.setConsultAuthorName(name);
            consultAuthorMapper.insertSelective(insert);
            return insert.getId();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    @Override
    public Integer addClassByClassName(String name) {
        try{
            List<ConsultClassModel> hasClass = consultService.selectClassByName(name);
            if(hasClass.size() > 0 && hasClass.get(0).getId() != null)
                return hasClass.get(0).getId();
//            ConsultClassModel insert = new ConsultClassModel();
//            insert.setConsultClassName(name);
//            consultClassMapper.insertSelective(insert);
//            return insert.getId();
            return null;
        }catch (Exception e){
            System.out.println(name);
            return null;
        }

    }

    @Override
    public Integer selectMatchIdByHomeNameAwayNameKickoffTime(String homeName, String awayName, String kickoffTime) {
        if(kickoffTime.length()==16){
            kickoffTime = kickoffTime+":00";
        }
        List<MatchModel> matchList = matchService.selectMatchByKickoffTime(kickoffTime);
        Integer matchId = null;
        for(MatchModel match : matchList){
            try{
                if(match.getAwayName().equals(homeName)||match.getAwayName().equals(awayName)){
                    matchId = match.getId();
                    break;
                }
                if(match.getHomeName().equals(homeName)||match.getHomeName().equals(awayName)){
                    matchId = match.getId();
                    break;
                }
                if(match.getHomeName().contains(homeName)||match.getHomeName().contains(awayName)){
                    matchId = match.getId();
                    break;
                }
                if(match.getAwayName().contains(homeName)||match.getAwayName().contains(awayName)){
                    matchId = match.getId();
                    break;
                }
                if(homeName.contains(match.getHomeName())||homeName.contains(match.getAwayName())){
                    matchId = match.getId();
                    break;
                }
                if(awayName.contains(match.getHomeName())||awayName.contains(match.getAwayName())){
                    matchId = match.getId();
                    break;
                }
            }catch (Exception e){
                logger.info("selectMatchIdByHomeNameAwayNameKickoffTime For - log : minor error : " + e);
            }
        }
        return matchId;
    }

    @Override
    public List<Integer> matchBindLiveStreamList(List<AllMatchModel> list) {
        List<Integer> idList = new ArrayList<>();
        for(AllMatchModel model : list){
            try {
                if (model.getHomeName() != null && model.getAwayName() != null) {
                    Integer matchId = selectMatchIdByHomeNameAwayNameKickoffTime(model.getHomeName(), model.getAwayName(), model.getKickoffTime());
                    if (matchId == null) {
                        continue;
                    }
                    for (AllLiveModel liveModel : model.getLiveList()) {
                        try {
                            LiveStreamBindModel liveStreamBindModel = new LiveStreamBindModel();
                            liveStreamBindModel.setLiveUrl(liveModel.getLiveUrl());
                            liveStreamBindModel.setFid(matchId.toString());
                            liveStreamBindModel.setLiveName(liveModel.getLiveName());
                            liveStreamBindModel.setType(liveModel.getType());
                            Integer bindId = matchService.insertLiveStreamBind(liveStreamBindModel);
                            idList.add(bindId);
                        }catch (Exception e){
                            logger.info("matchBindLiveStreamList For - log : minor error : " + e);
                        }
                    }
                }
            }catch (Exception e){
                logger.info("matchBindLiveStreamList For - log : minor error : " + e);
            }
        }
        return idList;
    }

    @Override
    public void addLiveMatchList(List<AllMatchModel> list) {
        for( AllMatchModel allMatchModel : list){
            try{
                LiveMatchModel liveMatchModel = new LiveMatchModel();
                liveMatchModel.setAwayName(allMatchModel.getAwayName());
                liveMatchModel.setHomeName(allMatchModel.getHomeName());
                liveMatchModel.setMatchName(allMatchModel.getMatchName());
                liveMatchModel.setKickoffTime(allMatchModel.getKickoffTime());
                List<LiveMatchModel> has = liveMatchMapper.select(liveMatchModel);
                if(has.size()<1){
                    liveMatchModel.setLeagueName(allMatchModel.getLeagueName());
                    int insertRet = liveMatchMapper.insertSelective(liveMatchModel);
                    if( insertRet > 0 && liveMatchModel.getId() != null){
                        for(AllLiveModel allLiveModel  : allMatchModel.getLiveList()){
                            try{
                                LiveMatchBindModel liveMatchBindModel = new LiveMatchBindModel();
                                liveMatchBindModel.setLiveUrl(allLiveModel.getLiveUrl());
                                liveMatchBindModel.setLiveName(allLiveModel.getLiveName());
                                liveMatchBindModel.setType(allLiveModel.getType());
                                liveMatchBindModel.setFid(liveMatchModel.getId().toString());
                                List<LiveMatchBindModel> liveMatchBindModelsList = liveMatchBindMapper.select(liveMatchBindModel);
                                if(liveMatchBindModelsList.size()<1){
                                    liveMatchBindMapper.insertSelective(liveMatchBindModel);
                                }
                            }catch (Exception e){
                                logger.info("addLiveMatchList For - log : minor error : " + e);
                            }
                        }
                    }
                }
            }catch (Exception e){
                logger.info("addLiveMatchList For - log : minor error : " + e);
            }
        }
    }
}
