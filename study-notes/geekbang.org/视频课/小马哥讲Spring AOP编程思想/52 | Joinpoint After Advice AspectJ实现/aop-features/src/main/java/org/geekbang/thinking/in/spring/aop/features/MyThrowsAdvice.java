package org.geekbang.thinking.in.spring.aop.features;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MyThrowsAdvice implements ThrowsAdvice {
    // public void afterThrowing(RuntimeException e) {
    //     System.out.printf("Exception : %s\n", e);
    // }
    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.printf("Method : %s, args : %s, target : %s, exception : %s\n",
                method,
                Arrays.asList(args),
                target,
                e
        );
    }
}
