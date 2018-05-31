package com.pjmike.lundao.utils;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 响应结果类
 *
 * @author pjmike
 * @create 2018-05-17 17:32
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
