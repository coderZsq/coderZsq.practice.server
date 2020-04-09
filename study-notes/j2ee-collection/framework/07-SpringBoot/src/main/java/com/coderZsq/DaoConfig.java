package com.coderZsq;

import com.coderZsq.domain.DaoBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {
    @Bean
    public DaoBean daoBean() {
        return new DaoBean();
    }
}
