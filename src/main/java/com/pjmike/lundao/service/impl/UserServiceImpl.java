package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.dao.UserMapper;
import com.pjmike.lundao.domain.bo.User;
import com.pjmike.lundao.service.UserService;
import com.pjmike.lundao.utils.CommonUtil;
import com.pjmike.lundao.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author pjmike
 * @create 2018-05-17 22:29
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User save(User model) {
        userMapper.insert(model);
        return model;
    }

    @Override
    public void update(User model) {
        userMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectOneById(id);
    }

    @Override
    public void updateUserPassword(User user) {
        //盐值
        String salt = CommonUtil.getRandomString(6);
        log.info("salt :{}",salt);
        //对密码加密
        String pwdByMd5 = MD5Util.getMD5(user.getPassword(),salt);
        user.setPassword(pwdByMd5);
        user.setSalt(salt);
        userMapper.updateByPrimaryKeySelective(user);
    }
}