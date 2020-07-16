package com.sq.resume.dao;

import com.sq.resume.bean.User;

public interface UserDao extends BaseDao<User> {
    User get(User user);
}
