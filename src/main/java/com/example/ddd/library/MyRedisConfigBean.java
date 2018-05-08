package com.example.ddd.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * MyRedisConfigBean
 *
 * @author wjp
 * @desc
 * @date Created in 下午4:01 2018/5/7
 */
@Component
public class MyRedisConfigBean {
    @Autowired
    private StringRedisTemplate template;
}
