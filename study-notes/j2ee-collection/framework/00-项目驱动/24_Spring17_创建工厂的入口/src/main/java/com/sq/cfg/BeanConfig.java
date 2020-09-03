package com.sq.cfg;

import com.sq.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.sq")
public class BeanConfig {
    @Bean
    public UserServiceImpl userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setName("@Bean");
        return userService;
    }
}
