package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.domain.Favour;
import com.pjmike.lundao.service.LikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavourServiceImplTest {
    @Autowired
    private LikeService likeService;
    @Test
    public void save() {
        Favour favour = new Favour();
        favour.setType(1);
        favour.setTypeId(4);
        favour.setStatus(0);
        favour.setUid(3);
        likeService.save(favour);
    }
}