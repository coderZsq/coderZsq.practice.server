package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义 {@link Component} Scan
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan
public @interface MyComponentScan {

    @AliasFor(annotation = ComponentScan.class, attribute = "value") // 隐性别名
            // "多态", 子注解提供新的属性方法引用"父" (元) 注解中的属性方法
    String[] scanBasePackages() default {};

    // scanBasePackages ->
    //          @AliasFor @ComponentScan.basePackages -> @AliasFor @ComponentScan.value (显性别名)
    // @AliasFor @ComponentScan.value // 传递隐性别名
}
