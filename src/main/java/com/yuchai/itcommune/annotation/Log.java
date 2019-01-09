package com.yuchai.itcommune.annotation;

import java.lang.annotation.*;

/**
 * @author Haven
 * @create 2019-01-08 10:42
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    boolean required() default true;
}
