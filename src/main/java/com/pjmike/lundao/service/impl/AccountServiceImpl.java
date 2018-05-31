package com.pjmike.lundao.service.impl;

import com.pjmike.lundao.dao.UserMapper;
import com.pjmike.lundao.domain.bo.User;
import com.pjmike.lundao.domain.vo.Account;
import com.pjmike.lundao.service.AccountService;
import com.pjmike.lundao.utils.BeanCopyUtils;
import com.pjmike.lundao.utils.CommonUtil;
import com.pjmike.lundao.utils.MD5Util;
import org.springframework.beans.BeanUtils;
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
    public User loadAccount(String phone) {
        return userMapper.selectOneByPhone(phone);
    }

    @Override
    public boolean isAccountExistByUserName(String username) {
        User user = userMapper.selectOneByUserName(username);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean registerAccount(Account account) {
        //盐值
        String salt = CommonUtil.getRandomString(6);
        //对密码加密
        String pwdByMd5 = MD5Util.getMD5(account.getPassword(),salt);
        account.setPassword(pwdByMd5);
        User user = new User();
        //将account里的username,password,phone属性拷贝到user
        BeanUtils.copyProperties(account,user,BeanCopyUtils.getNullPropertyNames(account));
        user.setSalt(salt);
        return userMapper.insert(user) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }
}
