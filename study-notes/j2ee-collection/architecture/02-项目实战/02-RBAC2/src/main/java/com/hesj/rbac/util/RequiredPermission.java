package com.hesj.rbac.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//1.使用位置(类,方法,字段)--->方法上面
@Target(ElementType.METHOD)
//2.保留时间(源代码,字节码,运行时期)--->运行时期
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredPermission {
//    String name();
//    String expression();
    String[] value();
}
