package com.yuchai.itcommune.util;

/**
 * Created by Haven
 * 2018/7/21 17:28
 *
 * @author Haven
 */
public class Result<T>{

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public Result() {
    }

    public Result setCode(ResultCodeEnum resultCode) {
        this.code = resultCode.getCode();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}
