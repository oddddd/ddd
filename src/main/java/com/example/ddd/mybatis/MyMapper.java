package com.example.ddd.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.type.BaseTypeHandler;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @InsertProvider(type = BaseInsertProvider.class,method = "dynamicSQL")
    int insertSelective(T record);

}

