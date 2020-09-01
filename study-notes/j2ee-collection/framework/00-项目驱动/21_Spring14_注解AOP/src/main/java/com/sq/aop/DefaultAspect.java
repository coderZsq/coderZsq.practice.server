package com.sq.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DefaultAspect {
    @Pointcut("within(com.sq.service.impl.UserServiceImpl)")
    public void pc() {}

    @Around("pc()")
    public Object log(ProceedingJoinPoint point) throws Throwable {
        System.out.println("log-----------------------1");

        // 调用目标方法
        Object ret = point.proceed();

        System.out.println("log-----------------------2");
        return ret;
    }

    @Around("pc()")
    public Object watch(ProceedingJoinPoint point) throws Throwable {
        System.out.println("watch-----------------------1");

        // 调用目标方法
        Object ret = point.proceed();

        System.out.println("watch-----------------------2");
        return ret;
    }
}
