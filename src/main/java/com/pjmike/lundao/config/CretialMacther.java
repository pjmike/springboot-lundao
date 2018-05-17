package com.pjmike.lundao.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import java.util.Objects;

/**
 * @author pjmike
 * @create 2018-04-18 21:24
 */
public class CretialMacther implements CredentialsMatcher{

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String password = new String(token.getPassword());
        String dbpassword = (String) authenticationInfo.getCredentials();
        return Objects.equals(password, dbpassword);
    }
}
