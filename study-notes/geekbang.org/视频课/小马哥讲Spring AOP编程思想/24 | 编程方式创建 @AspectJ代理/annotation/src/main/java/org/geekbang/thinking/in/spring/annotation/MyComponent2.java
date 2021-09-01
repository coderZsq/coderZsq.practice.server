package org.geekbang.thinking.in.spring.annotation;

import java.lang.annotation.*;

/**
 * {@link MyComponent} "派生"注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyComponent2 {
}
