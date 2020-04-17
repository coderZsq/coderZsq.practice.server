package com.coderZsq;

import lombok.Data;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Data
public class ConditionOnMissBean implements Condition {
    // private String beanName;
    //
    // public ConditionOnMissBean(String beanName) {
    //     this.beanName = beanName;
    // }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 容器中存在Nanny对象的实例, 就创建
        // 否则, 就不创建
        if (context.getBeanFactory().getBeanDefinition("rongmomo") != null) {
            return true;
        } else {
            return true;
        }
    }
}
