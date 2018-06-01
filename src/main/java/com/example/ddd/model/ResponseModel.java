package com.example.ddd.model;

import com.example.ddd.ddd.Model;

/**
 * ResponseModel
 *
 * @author wjp
 * @desc
 * @date Created in 下午3:54 2018/6/1
 */
public class ResponseModel extends Model{
    private Integer ret;
    private Object data;
    private String msg;

    @Override
    public String toString() {
        return "ResponseModel{" +
                "ret=" + ret +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    /**
     * Getter for property 'ret'.
     *
     * @return Value for property 'ret'.
     */
    public Integer getRet() {
        return ret;
    }

    /**
     * Setter for property 'ret'.
     *
     * @param ret Value to set for property 'ret'.
     */
    public void setRet(Integer ret) {
        this.ret = ret;
    }

    /**
     * Getter for property 'data'.
     *
     * @return Value for property 'data'.
     */
    public Object getData() {
        return data;
    }

    /**
     * Setter for property 'data'.
     *
     * @param data Value to set for property 'data'.
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Getter for property 'msg'.
     *
     * @return Value for property 'msg'.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Setter for property 'msg'.
     *
     * @param msg Value to set for property 'msg'.
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
