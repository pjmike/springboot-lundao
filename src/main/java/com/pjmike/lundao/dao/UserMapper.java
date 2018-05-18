package com.pjmike.lundao.dao;

import com.pjmike.lundao.domain.User;
import com.pjmike.lundao.utils.MyMapper;

/**
 * @author pjmike
 */
public interface UserMapper extends MyMapper<User> {
    User selectOneById(Integer id);

    User selectOneByPhone(String phone);
}