package org.geekbang.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect 配置类
 */
@Aspect
public class AspectConfiguration {

    @Pointcut("execution(public * *(..))") // 匹配 Joint Point
    private void anyPublicMethod() { // 方法名即 Pointcut 名
        System.out.println("@Pointcut at any public method.");
    }

    @Before("anyPublicMethod()")          // Joint Point 拦截动作
    public void beforeAnyPublicMethod() {
        System.out.println("@Before any public method.");
    }
}
