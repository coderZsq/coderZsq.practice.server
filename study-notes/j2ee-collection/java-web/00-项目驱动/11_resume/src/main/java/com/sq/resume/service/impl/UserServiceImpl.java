package com.sq.resume.service.impl;

import com.sq.resume.bean.User;
import com.sq.resume.dao.UserDao;
import com.sq.resume.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Override
    public User get(User user) {
        return ((UserDao) dao).get(user);
    }
}
