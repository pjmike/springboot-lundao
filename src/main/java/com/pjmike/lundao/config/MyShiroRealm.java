package com.pjmike.lundao.config;


import com.auth0.jwt.interfaces.Claim;
import com.pjmike.lundao.exception.ObjectException;
import com.pjmike.lundao.service.UserService;
import com.pjmike.lundao.utils.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author pjmike
 * @create 2018-05-17 11:27
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 身份认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException, ObjectException {
        String token = (String) authenticationToken.getPrincipal();
        if (StringUtils.isBlank(token)) {
            throw new ObjectException("您还没登录,请先登录");
        }
        Map<String, Claim> map = null;
        try {
            if (JwtToken.verifyTokenTime(token)) {
                throw new ObjectException("身份认证过期,请重新登录");
            }
            map = JwtToken.verifyToken(token);
        } catch (UnsupportedEncodingException e) {
            log.info("JWT验证失败: ",e.getMessage());
            e.printStackTrace();
        }
        //拿用户的id
        Integer userid = map.get("userid").asInt();
        if (userid == null) {
            log.info("userid 不存在");
            throw new ObjectException("请求错误");
        }
        if (userService.findById(userid) == null) {
            throw new ObjectException("用户不存在");
        }
        return new SimpleAuthenticationInfo(token, token, "myRealm");
    }
}
