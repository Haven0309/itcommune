package com.yuchai.itcommune.annotation;

import java.lang.annotation.*;

/**
 * @author Haven
 * @create 2019-01-08 9:56
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLoginToken {
    boolean required() default true;
}
