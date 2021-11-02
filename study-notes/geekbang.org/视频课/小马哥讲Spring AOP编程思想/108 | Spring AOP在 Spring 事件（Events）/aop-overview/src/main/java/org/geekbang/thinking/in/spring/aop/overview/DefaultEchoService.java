package org.geekbang.thinking.in.spring.aop.overview;

import org.springframework.context.annotation.Configuration;

/**
 * 默认 {@link EchoService} 实现
 */
@Configuration // @Configuration 需要 @ComponentScan -> ConfigurationClassPostProcessor
// CGLIB 代理对象
public class DefaultEchoService implements EchoService {

    @Override
    public String echo(String message) {
        return "[ECHO] " + message;
    }
}
