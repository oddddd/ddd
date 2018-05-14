package com.example.ddd.mybatis.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ConsultClassModel
 *
 * @author wjp
 * @desc
 * @date Created in 下午6:32 2018/5/14
 */
@Table(name="we_consultClass")
public class ConsultClassModel {
    @Id
    private Integer id;
    @Column(name = "consultClassName")
    private String consultClassName;
    @Column(name = "top")
    private Integer top;
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
     * Getter for property 'consultClassName'.
     *
     * @return Value for property 'consultClassName'.
     */
    public String getConsultClassName() {
        return consultClassName;
    }

    /**
     * Setter for property 'consultClassName'.
     *
     * @param consultClassName Value to set for property 'consultClassName'.
     */
    public void setConsultClassName(String consultClassName) {
        this.consultClassName = consultClassName;
    }

    /**
     * Getter for property 'top'.
     *
     * @return Value for property 'top'.
     */
    public Integer getTop() {
        return top;
    }

    /**
     * Setter for property 'top'.
     *
     * @param top Value to set for property 'top'.
     */
    public void setTop(Integer top) {
        this.top = top;
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
