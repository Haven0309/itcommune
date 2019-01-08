package com.yuchai.itcommune.annotation;

import java.lang.annotation.*;

/**
 * @author liugh
 * @since on 2018/5/9.
 */
@Target({ElementType.METHOD, ElementType.TYPE})          // 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME)     // 运行时有效
@Documented
public @interface ParamNotNull {
    /**
     * 必填参数
     */
    String value() default "";
}
