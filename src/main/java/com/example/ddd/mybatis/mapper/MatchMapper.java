package com.example.ddd.mybatis.mapper;

import com.example.ddd.mybatis.MyMapper;
import com.example.ddd.mybatis.model.MatchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select("SELECT * FROM we_match WHERE matchId = #{matchId}")
    List<MatchModel> findMatchByMatchId(@Param("matchId") String matchId);

    @Update("UPDATE we_match set homePoint = #{homePoint},awayPoint = #{awayPoint} WHERE matchId = #{matchId}")
    Integer updateMatchPointByMatchId(@Param("matchId") String matchId,@Param("homePoint") Integer homePoint,@Param("awayPoint") Integer awayPoint);

    @Update("UPDATE we_match set oddsUrl = #{oddsUrl} WHERE id = #{id}")
    Integer updateOddsUrlById(@Param("id") Integer id,@Param("oddsUrl") String oddsUrl);
}

