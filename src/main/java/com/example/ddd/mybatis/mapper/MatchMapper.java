package com.example.ddd.mybatis.mapper;

import com.example.ddd.mybatis.MyMapper;
import com.example.ddd.mybatis.model.MatchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

/**
 * MatchMapper
 *
 * @author wjp
 * @desc
 * @date Created in 下午3:32 2018/5/18
 */
@Mapper
public interface MatchMapper extends MyMapper<MatchModel> {
    @Select("SELECT * FROM we_match WHERE kickoffTime = #{kickoffTime}")
    List<MatchModel> findMatchByKickoffTime(@Param("kickoffTime") Timestamp kickoffTime);
}
