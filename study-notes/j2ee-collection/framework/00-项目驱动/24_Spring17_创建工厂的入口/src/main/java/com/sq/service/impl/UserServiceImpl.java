package com.sq.service.impl;

public class UserServiceImpl {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
