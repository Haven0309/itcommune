package com.yuchai.itcommune.aspect;

import com.yuchai.itcommune.annotation.ParamNotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *
 * @author Haven
 * @create 2019-01-07 9:40 
 */
@Aspect
@Component
public class ParamNotNullAspect {
    private static final Logger logger = LoggerFactory.getLogger(ParamNotNullAspect.class);

    @Pointcut("@annotation(com.yuchai.itcommune.annotation.ParamNotNull)")
    public void annotationPointcut() {
    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ParamNotNull annotation = method.getAnnotation(ParamNotNull.class);
        String value = annotation.value();
        logger.info("准备"+value);
    }

    @After("annotationPointcut()")
    public void afterPointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ParamNotNull annotation = method.getAnnotation(ParamNotNull.class);
        String value = annotation.value();
        logger.info("结束"+value);
    }

}
