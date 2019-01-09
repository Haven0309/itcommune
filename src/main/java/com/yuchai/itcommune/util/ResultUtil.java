package com.yuchai.itcommune.util;

/**
 * Created by Haven
 * 2018/7/21 17:42
 *
 * @author Haven
 */
public class ResultUtil {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    //成功
    public static <T> Result<T> genSuccessResult(T data) {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genSuccessResult() {
        return genSuccessResult(null);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCodeEnum.FAIL)
                .setMessage(message);
    }

    public static Result genUnauthorizedResult() {
        return new Result()
                .setCode(ResultCodeEnum.UNAUTHORIZED)
                .setMessage("权限不足！");
    }
}
