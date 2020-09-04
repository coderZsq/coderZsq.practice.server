package com.sq.demo.mysqldemo.service.impl;

import com.sq.demo.mysqldemo.domain.User;
import com.sq.demo.mysqldemo.mapper.UserMapper;
import com.sq.demo.mysqldemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    public PlatformTransactionManager transactionManager;

    @Autowired
    private UserService userService;

    // propagation 事务传播行为
    @Override
    @Transactional(propagation = Propagation.REQUIRED) // 开启事务
    public void save(User user, String userName) {
        // 调用删除方法
        userService.delete(userName);
        System.out.println("userService = " + userService);
        System.out.println("transactionManager = " + transactionManager);
        userMapper.insert(user);
        int i = 1 / 0; // 模拟异常
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 开启新的事务
    public void delete(String userName) {
        userMapper.delete(userName);
    }

    @Override
    @Transactional(readOnly = true)
    public void query() {
        // 财务报表的查询
        // 查询月记账单 query1
        /**
         * 001 1000
         * 002 1000
         * 003 2000
         */
        // 查询交易数据 query2
        /**
         * 001 500
         * 001 300
         * 001 200
         * 001 300
         */
    }
}
