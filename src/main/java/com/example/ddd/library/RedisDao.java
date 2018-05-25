package com.example.ddd.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * RedisDao
 * redis 简单操作
 * @author wjp
 * @desc
 * @date Created in 上午11:53 2018/5/7
 */
@Repository
public class RedisDao {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String head = "wev-";

    /**
     * 永久存一个key
     * @param key
     * @param value
     */
    public void setStringKeyValue(String key,String value){
        key = head  +key;
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        ops.set(key,value);
    }

    /**
     * 获取一个value
     * @param key
     * @return
     */
    public String getStringKeyValue(String key){
        key = head  +key;
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * 时间保存一个key
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean setStringKeyValueTime(String key, String value, long time){
        try {
            key = head  +key;
            ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
            ops.set(key, value);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception t) {
            return false;
        }
    }

    /**
     * 永久存一个key
     * @param key
     * @param value
     */
    public boolean setObjectKeyValue(String key,Object value){
        try{
            key = head  +key;
            ValueOperations<Object,Object> ops = redisTemplate.opsForValue();
            byte[] b = ObjectUtil.objectToBytes(value);
            ops.set(key,b.toString());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获取一个value
     * @param key
     * @return
     */
    public <T> T getObjectKeyValue(String key){
        try{
            key = head + key;
            Object result;
            ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
            return (T) result;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 时间保存一个key
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean setObjectKeyValueTime(String key, Object value, long time){
        try {
            key = head  + key;
            ValueOperations<Object,Object> ops = redisTemplate.opsForValue();
            ops.set(key, value);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception t) {
            return false;
        }
    }

    /**
     * key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try {
            key = head  +key;
            return redisTemplate.hasKey(key);
        } catch (Exception t) {
            return false;
        }
    }

    /**
     * 删除一个key
     * @param key
     * @return
     */
    public boolean remove(String key){
        try {
            key = head  +key;
            redisTemplate.delete(key);
            return true;
        } catch (Exception t) {
        }
        return false;
    }
}
