package com.example.ddd.mybatis.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LiveMatchModel
 *
 * @author wjp
 * @desc
 * @date Created in 下午7:29 2018/5/30
 */
@Table(name="we_liveMatch")
public class LiveMatchModel {
    @Id
    private Integer id;
    @Column(name = "matchId")
    private String matchId;
    @Column(name = "matchName")
    private String matchName;
    @Column(name = "kickoffTime")
    private String kickoffTime;
    @Column(name = "saleEndTime")
    private String saleEndTime;
    @Column(name = "homeId")
    private Integer homeId;
    @Column(name = "homeName")
    private String homeName;
    @Column(name = "homeLogo")
    private String homeLogo;
    @Column(name = "homeleagueId")
    private Integer homeleagueId;
    @Column(name = "homeleagueName")
    private String homeleagueName;
    @Column(name = "homePoint")
    private Integer homePoint;
    @Column(name = "homeLike")
    private Integer homeLike;
    @Column(name = "awayId")
    private Integer awayId;
    @Column(name = "awayName")
    private String awayName;
    @Column(name = "awayLogo")
    private String awayLogo;
    @Column(name = "awayleagueId")
    private Integer awayleagueId;
    @Column(name = "awayleagueName")
    private String awayleagueName;
    @Column(name = "awayPoint")
    private Integer awayPoint;
    @Column(name = "awayLike")
    private Integer awayLike;
    @Column(name = "leagueId")
    private Integer leagueId;
    @Column(name = "leagueName")
    private String leagueName;
    @Column(name = "leagueType")
    private String leagueType;
    @Column(name = "isEnd")
    private Integer isEnd;
    @Column(name = "liveNum")
    private Integer liveNum;
    @Column(name = "liveName")
    private String liveName;
    @Column(name = "homeWin")
    private Integer homeWin;
    @Column(name = "awayWin")
    private Integer awayWin;
    @Column(name = "deuce")
    private Integer deuce;
    @Column(name = "updateTime")
    private String updateTime;
    @Column(name = "createTime")
    private String createTime;

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for property 'matchId'.
     *
     * @return Value for property 'matchId'.
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * Setter for property 'matchId'.
     *
     * @param matchId Value to set for property 'matchId'.
     */
    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    /**
     * Getter for property 'matchName'.
     *
     * @return Value for property 'matchName'.
     */
    public String getMatchName() {
        return matchName;
    }

    /**
     * Setter for property 'matchName'.
     *
     * @param matchName Value to set for property 'matchName'.
     */
    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    /**
     * Getter for property 'kickoffTime'.
     *
     * @return Value for property 'kickoffTime'.
     */
    public String getKickoffTime() {
        return kickoffTime;
    }

    /**
     * Setter for property 'kickoffTime'.
     *
     * @param kickoffTime Value to set for property 'kickoffTime'.
     */
    public void setKickoffTime(String kickoffTime) {
        this.kickoffTime = kickoffTime;
    }

    /**
     * Getter for property 'saleEndTime'.
     *
     * @return Value for property 'saleEndTime'.
     */
    public String getSaleEndTime() {
        return saleEndTime;
    }

    /**
     * Setter for property 'saleEndTime'.
     *
     * @param saleEndTime Value to set for property 'saleEndTime'.
     */
    public void setSaleEndTime(String saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    /**
     * Getter for property 'homeId'.
     *
     * @return Value for property 'homeId'.
     */
    public Integer getHomeId() {
        return homeId;
    }

    /**
     * Setter for property 'homeId'.
     *
     * @param homeId Value to set for property 'homeId'.
     */
    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    /**
     * Getter for property 'homeName'.
     *
     * @return Value for property 'homeName'.
     */
    public String getHomeName() {
        return homeName;
    }

    /**
     * Setter for property 'homeName'.
     *
     * @param homeName Value to set for property 'homeName'.
     */
    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    /**
     * Getter for property 'homeLogo'.
     *
     * @return Value for property 'homeLogo'.
     */
    public String getHomeLogo() {
        return homeLogo;
    }

    /**
     * Setter for property 'homeLogo'.
     *
     * @param homeLogo Value to set for property 'homeLogo'.
     */
    public void setHomeLogo(String homeLogo) {
        this.homeLogo = homeLogo;
    }

    /**
     * Getter for property 'homeleagueId'.
     *
     * @return Value for property 'homeleagueId'.
     */
    public Integer getHomeleagueId() {
        return homeleagueId;
    }

    /**
     * Setter for property 'homeleagueId'.
     *
     * @param homeleagueId Value to set for property 'homeleagueId'.
     */
    public void setHomeleagueId(Integer homeleagueId) {
        this.homeleagueId = homeleagueId;
    }

    /**
     * Getter for property 'homeleagueName'.
     *
     * @return Value for property 'homeleagueName'.
     */
    public String getHomeleagueName() {
        return homeleagueName;
    }

    /**
     * Setter for property 'homeleagueName'.
     *
     * @param homeleagueName Value to set for property 'homeleagueName'.
     */
    public void setHomeleagueName(String homeleagueName) {
        this.homeleagueName = homeleagueName;
    }

    /**
     * Getter for property 'homePoint'.
     *
     * @return Value for property 'homePoint'.
     */
    public Integer getHomePoint() {
        return homePoint;
    }

    /**
     * Setter for property 'homePoint'.
     *
     * @param homePoint Value to set for property 'homePoint'.
     */
    public void setHomePoint(Integer homePoint) {
        this.homePoint = homePoint;
    }

    /**
     * Getter for property 'homeLike'.
     *
     * @return Value for property 'homeLike'.
     */
    public Integer getHomeLike() {
        return homeLike;
    }

    /**
     * Setter for property 'homeLike'.
     *
     * @param homeLike Value to set for property 'homeLike'.
     */
    public void setHomeLike(Integer homeLike) {
        this.homeLike = homeLike;
    }

    /**
     * Getter for property 'awayId'.
     *
     * @return Value for property 'awayId'.
     */
    public Integer getAwayId() {
        return awayId;
    }

    /**
     * Setter for property 'awayId'.
     *
     * @param awayId Value to set for property 'awayId'.
     */
    public void setAwayId(Integer awayId) {
        this.awayId = awayId;
    }

    /**
     * Getter for property 'awayName'.
     *
     * @return Value for property 'awayName'.
     */
    public String getAwayName() {
        return awayName;
    }

    /**
     * Setter for property 'awayName'.
     *
     * @param awayName Value to set for property 'awayName'.
     */
    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    /**
     * Getter for property 'awayLogo'.
     *
     * @return Value for property 'awayLogo'.
     */
    public String getAwayLogo() {
        return awayLogo;
    }

    /**
     * Setter for property 'awayLogo'.
     *
     * @param awayLogo Value to set for property 'awayLogo'.
     */
    public void setAwayLogo(String awayLogo) {
        this.awayLogo = awayLogo;
    }

    /**
     * Getter for property 'awayleagueId'.
     *
     * @return Value for property 'awayleagueId'.
     */
    public Integer getAwayleagueId() {
        return awayleagueId;
    }

    /**
     * Setter for property 'awayleagueId'.
     *
     * @param awayleagueId Value to set for property 'awayleagueId'.
     */
    public void setAwayleagueId(Integer awayleagueId) {
        this.awayleagueId = awayleagueId;
    }

    /**
     * Getter for property 'awayleagueName'.
     *
     * @return Value for property 'awayleagueName'.
     */
    public String getAwayleagueName() {
        return awayleagueName;
    }

    /**
     * Setter for property 'awayleagueName'.
     *
     * @param awayleagueName Value to set for property 'awayleagueName'.
     */
    public void setAwayleagueName(String awayleagueName) {
        this.awayleagueName = awayleagueName;
    }

    /**
     * Getter for property 'awayPoint'.
     *
     * @return Value for property 'awayPoint'.
     */
    public Integer getAwayPoint() {
        return awayPoint;
    }

    /**
     * Setter for property 'awayPoint'.
     *
     * @param awayPoint Value to set for property 'awayPoint'.
     */
    public void setAwayPoint(Integer awayPoint) {
        this.awayPoint = awayPoint;
    }

    /**
     * Getter for property 'awayLike'.
     *
     * @return Value for property 'awayLike'.
     */
    public Integer getAwayLike() {
        return awayLike;
    }

    /**
     * Setter for property 'awayLike'.
     *
     * @param awayLike Value to set for property 'awayLike'.
     */
    public void setAwayLike(Integer awayLike) {
        this.awayLike = awayLike;
    }

    /**
     * Getter for property 'leagueId'.
     *
     * @return Value for property 'leagueId'.
     */
    public Integer getLeagueId() {
        return leagueId;
    }

    /**
     * Setter for property 'leagueId'.
     *
     * @param leagueId Value to set for property 'leagueId'.
     */
    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    /**
     * Getter for property 'leagueName'.
     *
     * @return Value for property 'leagueName'.
     */
    public String getLeagueName() {
        return leagueName;
    }

    /**
     * Setter for property 'leagueName'.
     *
     * @param leagueName Value to set for property 'leagueName'.
     */
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    /**
     * Getter for property 'leagueType'.
     *
     * @return Value for property 'leagueType'.
     */
    public String getLeagueType() {
        return leagueType;
    }

    /**
     * Setter for property 'leagueType'.
     *
     * @param leagueType Value to set for property 'leagueType'.
     */
    public void setLeagueType(String leagueType) {
        this.leagueType = leagueType;
    }

    /**
     * Getter for property 'isEnd'.
     *
     * @return Value for property 'isEnd'.
     */
    public Integer getIsEnd() {
        return isEnd;
    }

    /**
     * Setter for property 'isEnd'.
     *
     * @param isEnd Value to set for property 'isEnd'.
     */
    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * Getter for property 'liveNum'.
     *
     * @return Value for property 'liveNum'.
     */
    public Integer getLiveNum() {
        return liveNum;
    }

    /**
     * Setter for property 'liveNum'.
     *
     * @param liveNum Value to set for property 'liveNum'.
     */
    public void setLiveNum(Integer liveNum) {
        this.liveNum = liveNum;
    }

    /**
     * Getter for property 'liveName'.
     *
     * @return Value for property 'liveName'.
     */
    public String getLiveName() {
        return liveName;
    }

    /**
     * Setter for property 'liveName'.
     *
     * @param liveName Value to set for property 'liveName'.
     */
    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    /**
     * Getter for property 'homeWin'.
     *
     * @return Value for property 'homeWin'.
     */
    public Integer getHomeWin() {
        return homeWin;
    }

    /**
     * Setter for property 'homeWin'.
     *
     * @param homeWin Value to set for property 'homeWin'.
     */
    public void setHomeWin(Integer homeWin) {
        this.homeWin = homeWin;
    }

    /**
     * Getter for property 'awayWin'.
     *
     * @return Value for property 'awayWin'.
     */
    public Integer getAwayWin() {
        return awayWin;
    }

    /**
     * Setter for property 'awayWin'.
     *
     * @param awayWin Value to set for property 'awayWin'.
     */
    public void setAwayWin(Integer awayWin) {
        this.awayWin = awayWin;
    }

    /**
     * Getter for property 'deuce'.
     *
     * @return Value for property 'deuce'.
     */
    public Integer getDeuce() {
        return deuce;
    }

    /**
     * Setter for property 'deuce'.
     *
     * @param deuce Value to set for property 'deuce'.
     */
    public void setDeuce(Integer deuce) {
        this.deuce = deuce;
    }

    /**
     * Getter for property 'updateTime'.
     *
     * @return Value for property 'updateTime'.
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * Setter for property 'updateTime'.
     *
     * @param updateTime Value to set for property 'updateTime'.
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Getter for property 'createTime'.
     *
     * @return Value for property 'createTime'.
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Setter for property 'createTime'.
     *
     * @param createTime Value to set for property 'createTime'.
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
