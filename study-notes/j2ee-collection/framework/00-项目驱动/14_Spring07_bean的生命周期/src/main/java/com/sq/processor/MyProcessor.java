package com.sq.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 可以统一处理所有的Bean
 */
public class MyProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("05 - BeanPostProcessor - postProcessBeforeInitialization - " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("08 - BeanPostProcessor - postProcessAfterInitialization - " + beanName);
        return bean;
    }
}
