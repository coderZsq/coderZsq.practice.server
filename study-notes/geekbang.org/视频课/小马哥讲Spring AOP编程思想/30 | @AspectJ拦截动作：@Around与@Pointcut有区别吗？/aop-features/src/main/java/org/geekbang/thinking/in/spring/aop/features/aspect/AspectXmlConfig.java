package org.geekbang.thinking.in.spring.aop.features.aspect;

/**
 * Aspect XML 配置类
 */
public class AspectXmlConfig {
    public void beforeAnyPublicMethod() {
        System.out.println("@Before any public method.");
    }
}
