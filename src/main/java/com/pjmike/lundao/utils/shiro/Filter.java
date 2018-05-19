package com.pjmike.lundao.utils.shiro;

import com.pjmike.lundao.exception.ObjectException;
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
import java.io.UnsupportedEncodingException;

/**
 * @author pjmike
 * @create 2018-05-18 16:40
 */
@Slf4j
public class Filter extends BasicHttpAuthenticationFilter {
    @Autowired
    private RedisOperator redisOperator;

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //获取Authorization字段
        String authorization = httpServletRequest.getHeader("Authorization");
        String userId = httpServletRequest.getHeader("userId");
        if (StringUtils.isBlank(authorization) || StringUtils.isBlank(userId)) {
            throw new ObjectException("用户未登录,请前往登录");
        }
        try {
            if (JwtToken.verifyTokenTime(authorization)) {
                log.info("JWT已过期,判断redis中存储的token是否过期");
                //jwt过期，如果redis中的token没过期，即refresh时间，refresh时间是jwt过期时间的两倍,在refresh有效期内重新下发token给客户端
                if (redisOperator.ttl("JWT-SESSION-" + userId) > 0) {
                    String refreshToken = JwtToken.createToken(Long.valueOf(userId));
                    httpServletResponse.setHeader("Authorization", refreshToken);
                }
                throw new ObjectException("身份信息已过期,请重新登录");
            }
        } catch (UnsupportedEncodingException e) {
            log.info("JWT验证失败: ", e.getMessage());
            e.printStackTrace();
        }
        JwtTokenShiro token = new JwtTokenShiro(authorization);
        getSubject(request, response).login(token);
        return true;
    }
}
