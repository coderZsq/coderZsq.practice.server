package com.coderZsq;

import com.coderZsq.domain.ControllerBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {
    @Bean
    public ControllerBean controllerBean() {
        return new ControllerBean();
    }
}
