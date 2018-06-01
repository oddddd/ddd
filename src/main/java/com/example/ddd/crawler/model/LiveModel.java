package com.example.ddd.crawler.model;

import com.example.ddd.ddd.Model;

import java.io.Serializable;

/**
 * LiveModel
 *
 * @author wjp
 * @desc
 * @date Created in 下午4:46 2018/5/29
 */
public class LiveModel extends Model implements Serializable {
    private String matchId;
    private String content;
    private String time;
    private String homePoint;
    private String awayPoint;
    private String type;

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
     * Getter for property 'content'.
     *
     * @return Value for property 'content'.
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter for property 'content'.
     *
     * @param content Value to set for property 'content'.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter for property 'time'.
     *
     * @return Value for property 'time'.
     */
    public String getTime() {
        return time;
    }

    /**
     * Setter for property 'time'.
     *
     * @param time Value to set for property 'time'.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Getter for property 'homePoint'.
     *
     * @return Value for property 'homePoint'.
     */
    public String getHomePoint() {
        return homePoint;
    }

    /**
     * Setter for property 'homePoint'.
     *
     * @param homePoint Value to set for property 'homePoint'.
     */
    public void setHomePoint(String homePoint) {
        this.homePoint = homePoint;
    }

    /**
     * Getter for property 'awayPoint'.
     *
     * @return Value for property 'awayPoint'.
     */
    public String getAwayPoint() {
        return awayPoint;
    }

    /**
     * Setter for property 'awayPoint'.
     *
     * @param awayPoint Value to set for property 'awayPoint'.
     */
    public void setAwayPoint(String awayPoint) {
        this.awayPoint = awayPoint;
    }

    /**
     * Getter for property 'type'.
     *
     * @return Value for property 'type'.
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for property 'type'.
     *
     * @param type Value to set for property 'type'.
     */
    public void setType(String type) {
        this.type = type;
    }
}
