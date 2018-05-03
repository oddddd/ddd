package com.example.ddd.mybatis.service;

import com.example.ddd.mybatis.model.ConsultAuthorModel;
import com.example.ddd.mybatis.model.ConsultModel;

public interface ConsultService {
    ConsultModel selectConsultByUrl(String url);

    ConsultAuthorModel selectAuthorByName(String name);
}
