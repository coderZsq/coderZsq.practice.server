package com.maoge.demo.service.impl;

import com.maoge.demo.domain.Account;
import com.maoge.demo.mapper.AccountMapper;
import com.maoge.demo.service.AccountService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;
    @Override
    public void reduce(Integer id, Integer amount) {
        int i=1/0;
        accountMapper.reduce(id, amount);
    }

    @GlobalTransactional(timeoutMills = 3000)
    @Override
    public Account queryById(Integer id) {
        return accountMapper.queryById(id);
    }
}
