package com.example.ddd.crawler.facade.impl;

import com.example.ddd.crawler.facade.CrawlerFacade;
import com.example.ddd.mybatis.mapper.ConsultAuthorMapper;
import com.example.ddd.mybatis.mapper.ConsultClassMapper;
import com.example.ddd.mybatis.model.ConsultAuthorModel;
import com.example.ddd.mybatis.model.ConsultClassModel;
import com.example.ddd.mybatis.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    ConsultAuthorMapper consultAuthorMapper;
    @Autowired
    ConsultClassMapper consultClassMapper;
    @Autowired
    ConsultService consultService;
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
}
