package com.example.ddd.mybatis.service.impl;

import com.example.ddd.mybatis.mapper.ConsultAuthorMapper;
import com.example.ddd.mybatis.mapper.ConsultClassMapper;
import com.example.ddd.mybatis.mapper.ConsultMapper;
import com.example.ddd.mybatis.model.ConsultAuthorModel;
import com.example.ddd.mybatis.model.ConsultClassModel;
import com.example.ddd.mybatis.model.ConsultModel;
import com.example.ddd.mybatis.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * ConsultImpl
 *
 * @author wjp
 * @desc
 * @date Created in 下午4:37 2018/5/3
 */
@Repository
public class ConsultImpl implements ConsultService{
    @Autowired
    ConsultMapper consultMapper;
    @Autowired
    ConsultAuthorMapper consultAuthorMapper;
    @Autowired
    ConsultClassMapper consultClassMapper;

    @Override
    public ConsultModel selectConsultByUrl(String url) {
        ConsultModel select = new ConsultModel();
        select.setUrl(url);
        return consultMapper.selectOne(select);
    }

    @Override
    public ConsultModel selectConsultById(Integer id) {
        return consultMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ConsultAuthorModel> selectAuthorByName(String name) {
        ConsultAuthorModel select = new ConsultAuthorModel();
        select.setConsultAuthorName(name);
        return consultAuthorMapper.select(select);
    }

    @Override
    public List<ConsultClassModel> selectClassByName(String name) {
        ConsultClassModel select = new ConsultClassModel();
        select.setConsultClassName(name);
        return consultClassMapper.select(select);
    }
}
