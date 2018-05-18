package com.pjmike.lundao.utils;

import java.util.Random;

/**
 * @author pjmike
 * @create 2018-05-18 15:59
 */
public class CommonUtil {
    /**
     * 生成盐
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "qwertyuiopasdfghjklzxcvbnm0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
