package com.example.ddd.crawler.facade.impl;

import com.example.ddd.crawler.facade.CrawlerFacade;
import com.example.ddd.crawler.model.AllLiveModel;
import com.example.ddd.crawler.model.AllMatchModel;
import com.example.ddd.crawler.model.OddsModel;
import com.example.ddd.library.OkHttp;
import com.example.ddd.library.RedisDao;
import com.example.ddd.mybatis.mapper.ConsultAuthorMapper;
import com.example.ddd.mybatis.mapper.ConsultClassMapper;
import com.example.ddd.mybatis.mapper.LiveMatchBindMapper;
import com.example.ddd.mybatis.mapper.LiveMatchMapper;
import com.example.ddd.mybatis.model.*;
import com.example.ddd.mybatis.service.ConsultService;
import com.example.ddd.mybatis.service.MatchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    @Autowired
    RedisDao redisDao;
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

    @Cacheable(value = "crawler500Detail",key = "'crawler500Detail' + #id")
    @Override
    public Object getMatchOddsById(Integer id) {
        try{
            MatchModel matchModel = matchService.selectMatchById(id);
            List<OddsModel> oddsModels = new ArrayList<>();
            if(matchModel != null && matchModel.getOddsUrl() != null){
                OkHttp okHttp = new OkHttp();
                byte[] b = okHttp.getStringByUrlTwo(matchModel.getOddsUrl());
                String html = new String(b, "GB2312");
                Document doc = Jsoup.parse(html);
                Elements trs = doc.getElementById("datatb").getElementsByTag("tbody").get(0).children();
                for(Element tr : trs){
                    try{
                        String oddsName = tr.child(1).attr("title");
                        //及时赔率
                        Element firstTr = tr.child(2).getElementsByTag("tr").get(0);
                        String firstOddsWin = firstTr.getElementsByTag("td").get(0).html();
                        String firstOddsDeuce = firstTr.getElementsByTag("td").get(1).html();
                        String firstOddsLose = firstTr.getElementsByTag("td").get(2).html();
                        Element nowTr = tr.child(2).getElementsByTag("tr").get(1);
                        String nowOddsWin = nowTr.getElementsByTag("td").get(0).html();
                        String nowOddsDeuce = nowTr.getElementsByTag("td").get(1).html();
                        String nowOddsLose = nowTr.getElementsByTag("td").get(2).html();
                        //及时概率
                        Element firstRateTr =  tr.child(3).getElementsByTag("tr").get(0);
                        String firstRateWin = firstRateTr.getElementsByTag("td").get(0).html();
                        String firstRateDeuce = firstRateTr.getElementsByTag("td").get(1).html();
                        String firstRateLose = firstRateTr.getElementsByTag("td").get(2).html();
                        Element nowtRateTr =  tr.child(3).getElementsByTag("tr").get(1);
                        String nowRateWin = nowtRateTr.getElementsByTag("td").get(0).html();
                        String nowRateDeuce = nowtRateTr.getElementsByTag("td").get(1).html();
                        String nowRateLose = nowtRateTr.getElementsByTag("td").get(2).html();
                        //返还率
                        String firstReturnRate =  tr.child(4).getElementsByTag("td").get(1).html();
                        String nowReturnRate =  tr.child(4).getElementsByTag("td").get(2).html();
                        //及时凯利
                        Element firstKellyTr =  tr.child(5).getElementsByTag("tr").get(0);
                        String firstKellyWin = firstKellyTr.getElementsByTag("td").get(0).html();
                        String firstKellyDeuce = firstKellyTr.getElementsByTag("td").get(1).html();
                        String firstKellyLose = firstKellyTr.getElementsByTag("td").get(2).html();
                        Element nowtKellyTr =  tr.child(5).getElementsByTag("tr").get(1);
                        String nowKellyWin = nowtKellyTr.getElementsByTag("td").get(0).html();
                        String nowKellyDeuce = nowtKellyTr.getElementsByTag("td").get(1).html();
                        String nowKellyLose = nowtKellyTr.getElementsByTag("td").get(2).html();
                        OddsModel oddsModel = new OddsModel();
                        oddsModel.setOddsName(oddsName);
                        oddsModel.setFirstOddsWin(firstOddsWin);
                        oddsModel.setFirstOddsDeuce(firstOddsDeuce);
                        oddsModel.setFirstOddsLose(firstOddsLose);
                        oddsModel.setNowOddsWin(nowOddsWin);
                        oddsModel.setNowOddsDeuce(nowOddsDeuce);
                        oddsModel.setNowOddsLose(nowOddsLose);
                        oddsModel.setFirstRateWin(firstRateWin);
                        oddsModel.setFirstRateDeuce(firstRateDeuce);
                        oddsModel.setFirstRateLose(firstRateLose);
                        oddsModel.setNowRateWin(nowRateWin);
                        oddsModel.setNowRateDeuce(nowRateDeuce);
                        oddsModel.setNowRateLose(nowRateLose);
                        oddsModel.setFirstReturnRate(firstReturnRate);
                        oddsModel.setNowReturnRate(nowReturnRate);
                        oddsModel.setFirstKellyWin(firstKellyWin);
                        oddsModel.setFirstKellyDeuce(firstKellyDeuce);
                        oddsModel.setFirstKellyLose(firstKellyLose);
                        oddsModel.setNowKellyWin(nowKellyWin);
                        oddsModel.setNowKellyDeuce(nowKellyDeuce);
                        oddsModel.setNowKellyLose(nowKellyLose);
                        oddsModels.add(oddsModel);
                    }catch (Exception e){
                        logger.info("getMatchOddsById For - log : minor error : " + e);
                    }
                }
                return oddsModels;
            }else{
                return oddsModels;
            }
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
