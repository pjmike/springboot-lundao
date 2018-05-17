package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.dao.UserMapper;
import com.pjmike.lundao.domain.User;
import com.pjmike.lundao.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pjmike
 * @create 2018-05-17 22:12
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User loadAccount(Integer id) {
        return null;
    }

    @Override
    public boolean isAccountExistByUid(Integer id) {
        User user = userMapper.seleteOneById(id);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean registerAccount(User account) {
        return userMapper.insert(account) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }
}
