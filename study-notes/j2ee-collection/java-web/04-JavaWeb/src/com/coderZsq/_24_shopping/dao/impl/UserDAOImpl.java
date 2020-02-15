package com.coderZsq._24_shopping.dao.impl;

import com.coderZsq._24_shopping.dao.IUserDAO;
import com.coderZsq._24_shopping.domain.User;
import com.coderZsq._24_shopping.handler.BeanHandler;
import com.coderZsq._24_shopping.util.JdbcTemplate;

public class UserDAOImpl implements IUserDAO {
    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM t_user WHERE username = ?";
        return  JdbcTemplate.query(sql, new BeanHandler<>(User.class), username);
    }
}
