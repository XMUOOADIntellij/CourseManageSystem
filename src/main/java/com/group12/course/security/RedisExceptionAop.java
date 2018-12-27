package com.group12.course.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ConcurrentModificationException;

/**
 * 用于拦截由于 redis 产生的 ConcurrentModification
 * 若产生了再执行一次即可解决
 *
 * @author Xu Gang
 * @date 2018年12月28日
 */
@Aspect
public class RedisExceptionAop {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(* com.group12.course.*.*.*(..))")
    public Object concurrentModificationFilter(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (ConcurrentModificationException ex){
            return joinPoint.proceed();
        }
    }
}
