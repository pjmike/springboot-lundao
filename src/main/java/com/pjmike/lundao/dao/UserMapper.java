package com.pjmike.lundao.dao;

import com.pjmike.lundao.domain.bo.User;
import com.pjmike.lundao.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {
    User selectOneById(Integer id);

    User selectOneByPhone(String phone);

    User selectOneByUserName(String username);
}