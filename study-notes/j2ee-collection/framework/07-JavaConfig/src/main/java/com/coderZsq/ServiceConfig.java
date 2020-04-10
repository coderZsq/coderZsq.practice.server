package com.coderZsq;

import com.coderZsq.domain.ServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public ServiceBean serviceBean() {
        return new ServiceBean();
    }
}
