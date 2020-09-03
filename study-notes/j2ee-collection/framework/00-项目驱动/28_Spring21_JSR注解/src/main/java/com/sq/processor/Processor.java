package com.sq.processor;

import com.sq.domain.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof Student)) return bean;
        System.out.println("BeanPostProcessor - before");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof Student)) return bean;
        System.out.println("BeanPostProcessor - after");
        return bean;
    }
}
