package com.sq.demo.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.sq.demo.mapper.GHAccountMapper;
import demo.service.IGHAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hesj
 * @Created by 叩丁狼教育 hesj
 * 需要通过dubbo 服务提供的接口, 需要使用dubbo的Service进行标记
 */
@Service
public class GHAccountServiceImpl implements IGHAccountService {
    @Autowired
    private GHAccountMapper ghAccountMapper;
    @Override
    @Transactional
    public void transIn(Long id, Integer amount) {
        ghAccountMapper.transIn(id, amount);
    }
}
