package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.domain.bo.User;
import com.pjmike.lundao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void update() {
        User user = new User();
        user.setId(5);
        user.setEmail("11734474@163.com");
        userService.update(user);
    }

    @Test
    public void updateAvatar() {
        User user = userService.findById(1);
        user.setAvatar("xxxxxxxxxxxccccccccccccc");
        userService.update(user);
    }

    @Test
    public void updatePassword() {
        User user = new User();
        user.setPhone("13572446538");
        user.setPassword("456343543");
        userService.updateUserPassword(user);
    }
}