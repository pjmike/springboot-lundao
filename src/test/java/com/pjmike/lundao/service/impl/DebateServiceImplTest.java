package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.service.DebateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DebateServiceImplTest {
    @Autowired
    private DebateService debateService;
    @Test
    public void save() {
        Debate debate = new Debate();
        debate.setContent("sdfsd");
        debate.setTitle("sdfd");
        debateService.save(debate);
        System.out.println(debate.getDebateId());
    }

    @Test
    public void findById() {
    }
}