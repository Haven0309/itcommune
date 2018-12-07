package com.yuchai.itcommune.util;

/**
 * Created by Haven
 * 2018/7/21 17:33
 *
 * @author Haven
 */
public enum ResultCodeEnum {
//    private static final long serialVersionUID = -4053497220251922791L;
    /**
     *
     */
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    private int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }

    int getCode() {
        return this.code;
    }
}
