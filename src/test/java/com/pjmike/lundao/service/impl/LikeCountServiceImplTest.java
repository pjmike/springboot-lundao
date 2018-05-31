package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.service.LikeCountSerivce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LikeCountServiceImplTest {
    @Autowired
    private LikeCountSerivce likeCountSerivce;
    @Test
    public void getLikeCountsByType() {
        assertNotNull(likeCountSerivce.getLikeCountsByType(1));
        System.out.println(likeCountSerivce.getLikeCountsByType(1));
    }
}