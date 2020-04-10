package com.coderzsq._02_configproperties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
// @ConfigurationProperties(prefix = "db")
@ToString
public class MyDruidDataSource {
    private String username;
    private String password;
    private String url;
    private String driverClassName;
}
