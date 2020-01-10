package com.coderZsq._05_anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解中抽象方法(属性)类型: 只能是基本类型, String, Class, annotation, 枚举, 数组
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface VIP {
    String value();
    int age() default 18;
    String[] hobby() default {"A", "B"};
}
