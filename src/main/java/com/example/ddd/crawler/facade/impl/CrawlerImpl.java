package com.example.ddd.crawler.facade.impl;

import com.example.ddd.crawler.facade.CrawlerFacade;
import com.example.ddd.mybatis.mapper.ConsultAuthorMapper;
import com.example.ddd.mybatis.model.ConsultAuthorModel;
import com.example.ddd.mybatis.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CrawlerImpl
 *
 * @author wjp
 * @desc
 * @date Created in 下午6:15 2018/5/3
 */
@Service
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
            insert.setConsultAuthorName(name);
            consultAuthorMapper.insertSelective(insert);
            return insert.getId();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }
}
