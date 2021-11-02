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
@MyComponentScan
public @interface MyComponentScan2 {

    @AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackages") // 隐性别名
    String[] basePackages() default {};

    // @MyComponent2.basePackages
    // -> @MyComponentScan.scanBasePackages
    // -> @ComponentScan.value
    // -> @ComponentScan.basePackages

    /**
     * 与元注解 @MyComponentScan 同名属性
     * @return
     */
    String[] scanBasePackages() default {};

    @AliasFor("scanBasePackages")
    String[] packages() default {}; // packages 覆盖了 scanBasePackages 覆盖了元注解 scanBasePackages
}
