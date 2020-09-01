package com.sq.service.impl;

import com.sq.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public void list() {
        System.out.println("UserServiceImpl - list");
    }
}
