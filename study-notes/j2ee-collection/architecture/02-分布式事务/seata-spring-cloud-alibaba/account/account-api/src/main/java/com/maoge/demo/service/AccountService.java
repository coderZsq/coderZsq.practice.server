package com.maoge.demo.service;

import com.maoge.demo.domain.Account;

public interface AccountService {
    /**
     * 扣减账户余额
     * @param id
     * @param amount
     */
    public void reduce(Integer id,Integer amount);
    public Account queryById(Integer id);
}
