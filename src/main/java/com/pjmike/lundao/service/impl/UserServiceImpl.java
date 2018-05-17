package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.dao.UserMapper;
import com.pjmike.lundao.domain.User;
import com.pjmike.lundao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pjmike
 * @create 2018-05-17 22:29
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void save(User model) {
        userMapper.insert(model);
    }

    @Override
    public void update(User model) {

    }

    @Override
    public User findById(Integer id) {
        return userMapper.seleteOneById(id);
    }
}
