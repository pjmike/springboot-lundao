package com.pjmike.lundao.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Test
    public void loadAccount() {
        String phone = "13452471357";
        Assert.assertNotNull(accountService.loadAccount(phone));
        System.out.println(accountService.loadAccount(phone));
    }
}