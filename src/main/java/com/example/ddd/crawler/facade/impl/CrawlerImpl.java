package com.example.ddd.crawler.facade.impl;

import com.example.ddd.crawler.facade.CrawlerFacade;
import com.example.ddd.mybatis.mapper.ConsultAuthorMapper;
import com.example.ddd.mybatis.model.ConsultAuthorModel;
import com.example.ddd.mybatis.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CrawlerImpl
 *
 * @author wjp
 * @desc
 * @date Created in 下午6:15 2018/5/3
 */
public class CrawlerImpl implements CrawlerFacade{
    @Autowired
    ConsultAuthorMapper consultAuthorMapper;
    @Autowired
    ConsultService consultService;

    @Override
    public Integer addAuthorByAuthorName(String name) {
        try{
            ConsultAuthorModel hasAuthor = consultService.selectAuthorByName(name);
            if(hasAuthor != null)
                return hasAuthor.getId();
            ConsultAuthorModel insert = new ConsultAuthorModel();
            insert.setUpdateTime(name);
            Integer id = consultAuthorMapper.insertSeletTive(insert);
            return id;
        }catch (Exception e){
            return null;
        }

    }
}
