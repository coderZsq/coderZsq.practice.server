package com.coderZsq.domain;

import lombok.Data;
import lombok.ToString;

@Data@ToString
public class DataSource {
    private String username;
    private String password;
    private String driverClassName;
    private String url;
}
