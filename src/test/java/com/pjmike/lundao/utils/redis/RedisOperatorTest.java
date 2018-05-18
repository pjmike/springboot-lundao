package com.pjmike.lundao.utils.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisOperatorTest {
    @Autowired
    private  RedisOperator redisOperator;
    //    private static RedisOperator redisOperator = new RedisOperator();


    @Test
    public void redisTest() {
        redisOperator.set("hi","world");
    }
}