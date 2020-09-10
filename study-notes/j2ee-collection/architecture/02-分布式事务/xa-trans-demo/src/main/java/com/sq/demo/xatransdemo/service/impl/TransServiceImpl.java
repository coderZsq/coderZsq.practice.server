package com.sq.demo.xatransdemo.service.impl;

import com.sq.demo.xatransdemo.mapper.gonghang.GhAccountMapper;
import com.sq.demo.xatransdemo.mapper.nonghang.NhAccountMapper;
import com.sq.demo.xatransdemo.service.TransService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TransServiceImpl implements TransService {
    @Resource
    private GhAccountMapper ghAccountMapper;

    @Resource
    private NhAccountMapper nhAccountMapper;

    @Resource
    private PlatformTransactionManager transactionManager;

    @Transactional
    @Override
    public void trans(int transIn, int tranOut, int amount) {
        System.out.println("transactionManager = " + transactionManager);
        // 工行账号转入
        ghAccountMapper.tranIn(transIn, amount);
        // 农行账户 转出
        nhAccountMapper.tranOut(tranOut, amount);
        int i = 1 / 0;
    }
}
