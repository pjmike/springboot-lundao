package com.pjmike.lundao.dao;

import com.pjmike.lundao.domain.bo.Debate;
import com.pjmike.lundao.domain.vo.DebateVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DebateMapperTest {
    @Autowired
    private DebateMapper mapper;

    @Test
    public void insert() {
        Debate debate = new Debate();
        debate.setTitle("sdffsd");
        debate.setContent("dfsd");
        mapper.insert(debate);
        System.out.println(debate);
    }

    @Test
    public void find() {
        DebateVo debateVo = mapper.selectByIdWithThesis(1);
        System.out.println(debateVo);
    }
}