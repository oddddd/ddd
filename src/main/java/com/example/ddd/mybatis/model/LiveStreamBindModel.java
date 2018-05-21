package com.example.ddd.mybatis.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LiveStreamBindModel
 *
 * @author wjp
 * @desc
 * @date Created in 下午2:31 2018/5/21
 */
@Table(name="we_liveStreamBind")
public class LiveStreamBindModel {
    @Id
    private Integer id;
    @Column(name = "liveUrl")
    private String liveUrl;
    @Column(name = "liveName")
    private String liveName;
    @Column(name = "fid")
    private String fid;
    @Column(name = "type")
    private Integer type;
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
     * Getter for property 'liveUrl'.
     *
     * @return Value for property 'liveUrl'.
     */
    public String getLiveUrl() {
        return liveUrl;
    }

    /**
     * Setter for property 'liveUrl'.
     *
     * @param liveUrl Value to set for property 'liveUrl'.
     */
    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
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
     * Getter for property 'fid'.
     *
     * @return Value for property 'fid'.
     */
    public String getFid() {
        return fid;
    }

    /**
     * Setter for property 'fid'.
     *
     * @param fid Value to set for property 'fid'.
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * Getter for property 'type'.
     *
     * @return Value for property 'type'.
     */
    public Integer getType() {
        return type;
    }

    /**
     * Setter for property 'type'.
     *
     * @param type Value to set for property 'type'.
     */
    public void setType(Integer type) {
        this.type = type;
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
