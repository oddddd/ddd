package com.example.ddd.mybatis.mapper;

import com.example.ddd.mybatis.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT id,nickName FROM we_user WHERE status = #{status}")
    List<UserModel> findByStatus(@Param("status") Integer status);
}
