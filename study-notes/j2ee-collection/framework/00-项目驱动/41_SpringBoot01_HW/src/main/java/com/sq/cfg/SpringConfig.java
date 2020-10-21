package com.sq.cfg;

import com.sq.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringConfig {
    @Bean
    public Person person() {
        return new Person();
    }
}
