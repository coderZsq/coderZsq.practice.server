package com.sq.resume.service;

import com.sq.resume.bean.User;

public interface UserService extends BaseService<User> {
    User get(User user);
}
