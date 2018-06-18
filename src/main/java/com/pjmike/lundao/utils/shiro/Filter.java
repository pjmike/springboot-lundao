package com.pjmike.lundao.utils.shiro;

import com.alibaba.fastjson.JSON;
import com.pjmike.lundao.exception.ObjectException;
import com.pjmike.lundao.utils.Result;
import com.pjmike.lundao.utils.jwt.JwtToken;
import com.pjmike.lundao.utils.redis.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author pjmike
 * @create 2018-05-18 16:40
 */
@Slf4j
@Component
public class Filter extends BasicHttpAuthenticationFilter {
    @Autowired
    private RedisOperator redisOperator;

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        //获取Authorization字段
        String authorization = httpServletRequest.getHeader("Authorization");
        String userId = httpServletRequest.getHeader("userId");
        PrintWriter writer = httpServletResponse.getWriter();
        if (StringUtils.isBlank(authorization) || StringUtils.isBlank(userId)) {
            writer.write(JSON.toJSONString(new Result(400, "用户未登陆，请登陆")));
            writer.flush();
            return false;
        }
        if (JwtToken.verifyTokenTime(authorization)) {
            log.info("JWT已过期,判断redis中存储的token是否过期");
            //jwt过期，如果redis中的token没过期，即refresh时间，refresh时间是jwt过期时间的两倍,在refresh有效期内重新下发token给客户端
            if (redisOperator.get("JWT-SESSION-" + userId) == null) {
                writer.write(JSON.toJSONString(new Result(400, "身份信息已过期,请重新登录")));
                return false;
            }
            String refreshToken = JwtToken.createToken(Long.valueOf(userId));
            httpServletResponse.setHeader("Authorization", refreshToken);
            //再次设置redis
            //将jwt放在redis中，设置jwt过期时间的两倍，即refresh刷新时间
            long refreshTime = 36000L;
            redisOperator.set("JWT-SESSION-" + userId, refreshToken, refreshTime);
            //下发新的jwt
            writer.write(JSON.toJSONString(new Result(200, "new JWT", refreshToken)));
            return false;
        } else {
            JwtTokenShiro token = new JwtTokenShiro(authorization);
            getSubject(request, response).login(token);
            return true;
        }
    }
}
