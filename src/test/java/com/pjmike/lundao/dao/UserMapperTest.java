package com.pjmike.lundao.dao;

import com.pjmike.lundao.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    private Logger logger = LoggerFactory.getLogger(UserMapperTest.class);
    @Autowired
    private UserMapper userMapper;
    @Test
    public void findUser() {
        User user = new User();
//        user.setUsername("pjmike");
        user.setPhone("13572446538");
        Assert.assertNotNull(userMapper.selectOne(user));
        Assert.assertNotNull(userMapper.selectOneByUserName("pjmike"));
        logger.info("user:{}",user);
    }
}