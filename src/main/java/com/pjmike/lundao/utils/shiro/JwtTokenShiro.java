package com.pjmike.lundao.utils.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author pjmike
 * @create 2018-05-17 21:05
 */
public class JwtTokenShiro implements AuthenticationToken {
    /**
     * 密匙
     */
    private String token;

    public JwtTokenShiro(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
