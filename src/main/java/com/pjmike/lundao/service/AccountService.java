package com.pjmike.lundao.service;

import com.pjmike.lundao.domain.User;

/**
 * @author pjmike
 * @create 2018-05-17 22:07
 */
public interface AccountService {
    /**
     * 载入用户信息
     *
     * @param id
     * @return
     */
    User loadAccount(Integer id);

    /**
     * 用户是否已被注册
     *
     * @param id
     * @return
     */
    boolean isAccountExistByUid(Integer id);

    /**
     * 注册用户
     *
     * @param account
     * @return
     */
    boolean registerAccount(User account);


}
