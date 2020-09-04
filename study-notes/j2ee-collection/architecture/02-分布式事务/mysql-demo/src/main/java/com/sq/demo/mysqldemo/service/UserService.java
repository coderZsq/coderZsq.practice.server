package com.sq.demo.mysqldemo.service;

import com.sq.demo.mysqldemo.domain.User;

public interface UserService {
    public void save(User user, String userName);

    public void delete(String userName);

    public void query();
}
