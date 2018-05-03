package com.example.ddd.mybatis.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ConsultAuthorModel
 *
 * @author wjp
 * @desc
 * @date Created in 下午5:35 2018/5/3
 */
@Table(name="we_consultAuthor")
public class ConsultAuthorModel {
    @Id
    private Integer id;
    @Column(name = "consultAuthorName")
    private String consultAuthorName;
    @Column(name = "bindUser")
    private Integer bindUser;
    @Column(name = "status")
    private Integer status;
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
     * Getter for property 'consultAuthorName'.
     *
     * @return Value for property 'consultAuthorName'.
     */
    public String getConsultAuthorName() {
        return consultAuthorName;
    }

    /**
     * Setter for property 'consultAuthorName'.
     *
     * @param consultAuthorName Value to set for property 'consultAuthorName'.
     */
    public void setConsultAuthorName(String consultAuthorName) {
        this.consultAuthorName = consultAuthorName;
    }

    /**
     * Getter for property 'bindUser'.
     *
     * @return Value for property 'bindUser'.
     */
    public Integer getBindUser() {
        return bindUser;
    }

    /**
     * Setter for property 'bindUser'.
     *
     * @param bindUser Value to set for property 'bindUser'.
     */
    public void setBindUser(Integer bindUser) {
        this.bindUser = bindUser;
    }

    /**
     * Getter for property 'status'.
     *
     * @return Value for property 'status'.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Setter for property 'status'.
     *
     * @param status Value to set for property 'status'.
     */
    public void setStatus(Integer status) {
        this.status = status;
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
