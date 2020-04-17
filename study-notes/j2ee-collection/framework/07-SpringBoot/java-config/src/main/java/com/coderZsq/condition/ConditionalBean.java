package com.coderZsq.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) // 注解的作用域, 可以在什么实际有效 RUNTIME: 代表虚拟机运行还有效
@Conditional(NormalCondition.class)
public @interface ConditionalBean {
    Class<?> value();
}
