package com.example.ddd.mybatis.mapper;

import com.example.ddd.mybatis.MyMapper;
import com.example.ddd.mybatis.model.LiveMatchModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LiveMatchMapper extends MyMapper<LiveMatchModel> {
}
