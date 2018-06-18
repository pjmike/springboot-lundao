package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.domain.vo.DebateVo;
import com.pjmike.lundao.service.DebateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        List<Debate> debateList = debateService.findAllByPageAndSize(1, 10);
        System.out.println(debateList);
    }

    @Test
    public void find() {
       DebateVo debateVos = debateService.findWithThesisIdAndWithoutUser(1);
        System.out.println(debateVos);
    }
}