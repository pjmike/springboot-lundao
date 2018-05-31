package com.pjmike.lundao.dao;

import com.pjmike.lundao.domain.Likecount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LikecountMapperTest {
    @Autowired
    private LikecountMapper likecountMapper;
    @Test
    public void selectByTypeAndTypeId() {
        Assert.assertNotNull(likecountMapper.selectByTypeAndTypeId(1, 2));
    }
}