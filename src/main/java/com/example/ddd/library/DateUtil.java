package com.example.ddd.library;

import java.sql.Timestamp;

/**
 * DateUtil
 *
 * @author wjp
 * @desc
 * @date Created in 下午7:04 2018/5/18
 */
public class DateUtil {
    public static Timestamp str2TimeStamp(String date){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }
}
