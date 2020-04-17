package com.coderzsq._09_condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.StandardMethodMetadata;

public class MissingBeanCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (metadata instanceof StandardMethodMetadata) {
            Class<?> returnType = ((StandardMethodMetadata) metadata).getIntrospectedMethod().getReturnType();
            if (context.getBeanFactory().getBeansOfType(returnType).isEmpty()) { // 在容器中没有bean对象的时候
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
