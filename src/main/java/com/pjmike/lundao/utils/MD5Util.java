package com.pjmike.lundao.utils;

import org.springframework.util.DigestUtils;


/**
 * @author pjmike
 * @create 2018-05-18 0:31
 */
public class MD5Util {
    public static String getMD5(String password,String salt) {
        String str = password + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
        return md5;
    }
}
