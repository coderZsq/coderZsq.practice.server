package com.sq.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class LogAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("LogAdvice - before ------------------");
    }
}
