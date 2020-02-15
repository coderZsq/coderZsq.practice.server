package com.coderZsq._24_shopping.dao;


import com.coderZsq._24_shopping.domain.User;

public interface IUserDAO {
    /**
     * 根据账号去查询该账号对应的用户对象
     * @param username 登录账户(唯一)
     */
    User getUserByUsername(String username);
}
