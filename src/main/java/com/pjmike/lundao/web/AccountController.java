package com.pjmike.lundao.web;

import com.pjmike.lundao.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册和登录操作
 *
 * @author pjmike
 * @create 2018-05-17 21:38
 */
@RestController
public class AccountController {

    @PostMapping("/register")
    public Result accountRegister() {
        return null;
    }

    @PostMapping("/login")
    public Result accountLogin() {
        return null;
    }
}
