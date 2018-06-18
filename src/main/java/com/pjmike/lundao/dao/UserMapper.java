package com.pjmike.lundao.dao;

import com.pjmike.lundao.domain.bo.User;
import com.pjmike.lundao.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserMapper extends MyMapper<User> {
    User selectOneById(Integer id);

    User selectOneByPhone(String phone);

    User selectOneByUserName(String username);

    User changePassword(@Param("phone") String phone,@Param("password") String password);

    int updateAavatar(User user);
}