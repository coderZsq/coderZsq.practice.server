package com.sq.mongo.service;

import com.sq.mongo.entity.User;

import java.util.List;

public interface IUserService {
    void save(User user);
    void update(User user);
    void delete(String id);
    User get(String id);
    List<User> list();
}
