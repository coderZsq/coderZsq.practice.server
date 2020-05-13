package com.coderZsq.login_app.service;

import com.coderZsq.login_app.domain.User;

public interface IUserService {
      User queryByName(String username);
      void insert(User user);

    User queryByNameByCache(String username) throws Exception ;
}
