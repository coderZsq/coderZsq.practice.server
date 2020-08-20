package com.sq;

import com.sq.domain.Dog;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 单例设计模式: 一个对象在整个程序运行过程中只有1个实例
 */
public class ConnTest {
    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(ctx.getBean("dog", Dog.class));
    }
}
