package com.pjmike.lundao.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThesisMapperTest {
    @Autowired
    private ThesisMapper mapper;
    @Test
    public void findAllByDebateId() {
        System.out.println(mapper.findAllByDebateId(1));
    }
}