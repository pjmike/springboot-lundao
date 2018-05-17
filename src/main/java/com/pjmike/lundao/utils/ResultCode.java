package com.pjmike.lundao.utils;

/**
 * 响应码枚举
 *
 * @author pjmike
 * @create 2018-05-17 17:44
 */
public enum  ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAIL(400),
    /**
     * 未授权
     */
    UNAUTHORIZED(401),
    /**
     * 资源不存在
     */
    NOT_FOUND(404),
    /**
     * 服务器错误
     */
    INTERNAL_SERVER_ERROR(500);
    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
