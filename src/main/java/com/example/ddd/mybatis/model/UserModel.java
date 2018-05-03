package com.example.ddd.mybatis.model;

/**
 * UserModel
 *
 * @author wjp
 * @desc 用户表模型
 * @date Created in 下午2:26 2018/5/2
 */
public class UserModel{
    Integer id;
    String nickName;

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
     * Getter for property 'nickName'.
     *
     * @return Value for property 'nickName'.
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Setter for property 'nickName'.
     *
     * @param nickName Value to set for property 'nickName'.
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
