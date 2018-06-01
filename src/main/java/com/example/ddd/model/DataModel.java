package com.example.ddd.model;

import com.example.ddd.ddd.Model;

/**
 * DataModel
 *
 * @author wjp
 * @desc
 * @date Created in 下午3:56 2018/6/1
 */
public class DataModel extends Model{
    private Integer code;
    private String context;
    private Object data;

    @Override
    public String toString() {
        return "DataModel{" +
                "code=" + code +
                ", context='" + context + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * Getter for property 'code'.
     *
     * @return Value for property 'code'.
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Setter for property 'code'.
     *
     * @param code Value to set for property 'code'.
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Getter for property 'context'.
     *
     * @return Value for property 'context'.
     */
    public String getContext() {
        return context;
    }

    /**
     * Setter for property 'context'.
     *
     * @param context Value to set for property 'context'.
     */
    public void setContext(String context) {
        this.context = context;
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
}
