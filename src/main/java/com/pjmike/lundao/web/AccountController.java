package com.pjmike.lundao.web;

import com.pjmike.lundao.domain.User;
import com.pjmike.lundao.service.AccountService;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * 注册和登录操作
 *
 * @author pjmike
 * @create 2018-05-17 21:38
 */
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/register")
    public Result accountRegister(@Valid @RequestBody User user) {
        if (accountService.isAccountExistByUid(user.getPhone())) {
            return ResultUtils.error("账号已存在");
        }
        return null;
    }
    @PostMapping("/login")
    public Result accountLogin() {
        return null;
    }
}
