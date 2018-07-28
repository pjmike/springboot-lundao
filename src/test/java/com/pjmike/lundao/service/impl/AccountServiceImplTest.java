package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.utils.CommonUtil;
import com.pjmike.lundao.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

    @Test
    public void registerAccount() {
        //盐值
        String salt = CommonUtil.getRandomString(6);
        //对密码加密
        String pwdByMd5 = MD5Util.getMD5("123456789520",salt);
        System.out.println(salt);
        System.out.println(pwdByMd5);
    }
}