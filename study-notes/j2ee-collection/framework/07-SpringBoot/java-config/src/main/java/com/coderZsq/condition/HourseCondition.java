package com.coderZsq.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class HourseCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 从容器中获取一个Hourse的实例对象
        if (context.getBeanFactory().getBeansOfType(Hourse.class).size() < 1) {
            return false;
        }
        return true;
    }
}
