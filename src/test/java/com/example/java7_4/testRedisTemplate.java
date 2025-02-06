package com.example.java7_4;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class testRedisTemplate {
    @Autowired
    public RedisTemplate redisTemplate;
    @Test
    public void test(){
        System.out.println(redisTemplate);
    }
}
