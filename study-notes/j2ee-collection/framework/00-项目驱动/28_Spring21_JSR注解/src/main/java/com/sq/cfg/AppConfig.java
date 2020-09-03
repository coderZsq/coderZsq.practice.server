package com.sq.cfg;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("com.sq")
@ImportResource("applicationContext.xml")
public class AppConfig {
}
