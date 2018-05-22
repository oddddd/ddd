package com.example.ddd.netty.handler.model;

import com.example.ddd.ddd.Model;

/**
 * DataModel
 *
 * @author wjp
 * @desc
 * @date Created in 下午2:26 2018/5/22
 */
public class DataModel extends Model {
    private String head;
    private String Protocol;
    private String body;
    private String time;
    private String token;
    private String tail;

    /**
     * Getter for property 'head'.
     *
     * @return Value for property 'head'.
     */
    public String getHead() {
        return head;
    }

    /**
     * Setter for property 'head'.
     *
     * @param head Value to set for property 'head'.
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * Getter for property 'protocol'.
     *
     * @return Value for property 'protocol'.
     */
    public String getProtocol() {
        return Protocol;
    }

    /**
     * Setter for property 'protocol'.
     *
     * @param protocol Value to set for property 'protocol'.
     */
    public void setProtocol(String protocol) {
        Protocol = protocol;
    }

    /**
     * Getter for property 'body'.
     *
     * @return Value for property 'body'.
     */
    public String getBody() {
        return body;
    }

    /**
     * Setter for property 'body'.
     *
     * @param body Value to set for property 'body'.
     */
    public void setBody(String body) {
        this.body = body;
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
     * Getter for property 'token'.
     *
     * @return Value for property 'token'.
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter for property 'token'.
     *
     * @param token Value to set for property 'token'.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter for property 'tail'.
     *
     * @return Value for property 'tail'.
     */
    public String getTail() {
        return tail;
    }

    /**
     * Setter for property 'tail'.
     *
     * @param tail Value to set for property 'tail'.
     */
    public void setTail(String tail) {
        this.tail = tail;
    }
}
