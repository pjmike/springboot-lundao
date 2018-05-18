package com.pjmike.lundao.utils;

/**
 * 响应结果工具类
 *
 * @author pjmike
 * @create 2018-05-17 17:41
 */
public class ResultUtils {
    private static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";
    private static final String DEFAULT_FAIL__MESSAGE = "请求错误";

    public static Result success() {
        return new Result().setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result success(Object data) {
        return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result error(String message) {
        return new Result().setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    public static Result error(Object object) {
        return new Result().setCode(ResultCode.FAIL)
                .setMessage(DEFAULT_FAIL__MESSAGE)
                .setData(object);
    }
}
