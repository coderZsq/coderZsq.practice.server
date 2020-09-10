package com.sq.demo.xatransdemo.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    // gonghang的DataSource
    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.gonghang")
    public DataSource ghDataSource() {
        final AtomikosDataSourceBean sourceBean = new AtomikosDataSourceBean();
        return sourceBean;
    }

    // nonghang的DataSource
    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.nonghang")
    public DataSource nhDataSource() {
        final AtomikosDataSourceBean sourceBean = new AtomikosDataSourceBean();
        return sourceBean;
    }
}
