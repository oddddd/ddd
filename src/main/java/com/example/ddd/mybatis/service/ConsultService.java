package com.example.ddd.mybatis.service;

import com.example.ddd.mybatis.model.ConsultAuthorModel;
import com.example.ddd.mybatis.model.ConsultClassModel;
import com.example.ddd.mybatis.model.ConsultModel;

import java.util.List;

public interface ConsultService {
    ConsultModel selectConsultByUrl(String url);
    ConsultModel selectConsultById(Integer id);
    List<ConsultAuthorModel> selectAuthorByName(String name);
    List<ConsultClassModel> selectClassByName(String name);
}
