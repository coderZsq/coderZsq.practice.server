package com.coderZsq.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class NormalCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 1. 获取到ConditionalBean的value值.class
        Map<String, Object> annoMap = metadata.getAnnotationAttributes(ConditionalBean.class.getName());
        Class<?> value = (Class<?>) annoMap.get("value");
        // 2. 去容器中判断.class 有没有对应的实例bean对象, 如果有, 返回true, 否则, 返回false
        if (context.getBeanFactory().getBeansOfType(value).size() < 1) {
            return false;
        }
        return true;
    }
}
