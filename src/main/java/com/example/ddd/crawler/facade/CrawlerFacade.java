package com.example.ddd.crawler.facade;

import com.example.ddd.crawler.model.AllMatchModel;

import java.util.List;

public interface CrawlerFacade {

    /**
     * 查询作者id 没有就添加
     * @param name
     * @return
     */
    Integer addAuthorByAuthorName(String name);

    /**
     * 查询分类id 没有就添加
     * @param name
     * @return
     */
    Integer addClassByClassName(String name);

    /**
     * 模糊查询某场比赛
     * @param homeName
     * @param awayName
     * @param kickoffTime
     * @return
     */
    Integer selectMatchIdByHomeNameAwayNameKickoffTime(String homeName,String awayName,String kickoffTime);

    List<Integer> matchBindLiveStreamList(List<AllMatchModel> list);
}
