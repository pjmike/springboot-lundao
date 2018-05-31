package com.pjmike.lundao.service;

import com.pjmike.lundao.domain.bo.User;
import com.pjmike.lundao.domain.vo.Account;

/**
 * @author pjmike
 * @create 2018-05-17 22:07
 */
public interface AccountService {
    /**
     * 载入用户信息
     *
     * @param phone
     * @return
     */
    User loadAccount(String phone);

    /**
     * 用户是否已被注册
     *
     * @param username
     * @return
     */
    boolean isAccountExistByUserName(String username);

    /**
     * 注册用户
     *
     * @param account
     * @return
     */
    boolean registerAccount(Account account);


}
