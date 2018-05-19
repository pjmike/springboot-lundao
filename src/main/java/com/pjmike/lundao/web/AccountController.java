package com.pjmike.lundao.web;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.pjmike.lundao.domain.User;
import com.pjmike.lundao.domain.vo.Account;
import com.pjmike.lundao.exception.ServiceException;
import com.pjmike.lundao.service.AccountService;
import com.pjmike.lundao.utils.CommonUtil;
import com.pjmike.lundao.utils.MD5Util;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.ResultUtils;
import com.pjmike.lundao.utils.aliyun.SmsDemo;
import com.pjmike.lundao.utils.jwt.JwtToken;
import com.pjmike.lundao.utils.redis.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * 注册和登录操作
 *
 * @author pjmike
 * @create 2018-05-17 21:38
 */
@Slf4j
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RedisOperator redisOperator;
    /**
     * 注册
     *
     * @param account
     * @return
     */
    @PostMapping("/sign_up")
    public Result accountRegister(@Validated({Account.group1.class}) @RequestBody Account account) {
        String codeValue = redisOperator.get(account.getPhone());
        if (codeValue == null) {
            return ResultUtils.error("验证码无效或已过期，请重新发送验证码");
        }
        if (!Objects.equals(codeValue, account.getCode())) {
            return ResultUtils.error("验证码无效或已过期，请重新发送验证码");
        }
        if (accountService.isAccountExistByUserName(account.getUsername())) {
            return ResultUtils.error("账号已存在");
        }
        if (accountService.registerAccount(account)) {
            return ResultUtils.success();
        } else {
            return ResultUtils.error("注册失败");
        }
    }

    /**
     * 登录
     *
     * @param account
     * @return
     */
    @PostMapping("/sign_in")
    public Result accountLogin(@RequestBody Account account, HttpServletResponse response) throws UnsupportedEncodingException {
        User user = accountService.loadAccount(account.getPhone());
        if (user == null) {
            throw new ServiceException("手机号不存在,请重新注册");
        }
        String pwd = MD5Util.getMD5(account.getPassword(), user.getSalt());
        if (!StringUtils.equals(pwd,user.getPassword())) {
            throw new ServiceException("密码错误,请重新输入");
        }
        String token = JwtToken.createToken(user.getId());
        // 时间以秒计算,token有效刷新时间是token有效过期时间的2倍
        long refreshPeriodTime = 36000L;
        //将token放在redis里
        redisOperator.set("JWT-SESSION-" + user.getId(), token, refreshPeriodTime);
        response.setHeader("Authorization",token);
        user.setSalt(null);
        user.setPassword(null);
        return ResultUtils.success(user);
    }

    @PostMapping("/authCode")
    public Result sendAuthCode(@RequestBody @Validated({Account.group2.class})Account account) throws ClientException {
        System.out.println(account.getPhone());
        SendSmsResponse smsResponse = SmsDemo.sendSms(account.getPhone());
        log.info("smsResponse code: " + smsResponse.getCode() + ", message: " + smsResponse.getMessage());
        if (!Objects.equals("OK", smsResponse.getCode())) {
            return ResultUtils.error("短信发送失败");
        }
        return ResultUtils.success();
    }

    @GetMapping("/unAuthorized")
    public Result unauthorized() {
        return ResultUtils.error("用户未登陆/身份信息过期,请重新登录");
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
