package cn.wolfcode.wolf2w.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户参数注入注解
 * 1>贴有该注解用户参数使用自定义的参数解析器
 */
@Target({ElementType.PARAMETER})  //表示贴在参数上
@Retention(RetentionPolicy.RUNTIME)
public @interface UserParam {
}
