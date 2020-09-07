package com.sq.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
// @PropertySource("db.properties")
public class UserServiceImpl {
    @Value("${jdbc.username}")
    private String name;

    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
