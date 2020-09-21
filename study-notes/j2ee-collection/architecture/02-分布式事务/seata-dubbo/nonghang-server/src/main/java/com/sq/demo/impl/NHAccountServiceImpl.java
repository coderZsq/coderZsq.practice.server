package com.sq.demo.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.sq.demo.mapper.NHAccountMapper;
import demo.service.IGHAccountService;
import demo.service.INHAccountService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service
public class NHAccountServiceImpl implements INHAccountService {

    @Autowired
    private NHAccountMapper nhAccountMapper;

    //导入dubbo的远程服务接口
    @Reference
    private IGHAccountService ghAccountService;


    @Override
    @Transactional
    public void transOut(Long id, Integer amount) throws Exception {
        nhAccountMapper.transOut(id, amount);
    }


    //转账服务 --事务发起者 开启全局事务
    @GlobalTransactional
    public void trans(Long outId, Long inId, Integer amount) throws Exception {
        //1 调用本地转出服务
        transOut(outId,amount);  // 数据是否写入到数据库
        //2 调用远程的转入服务  可能调用失败, 但是没有异常抛出
        // 容错机制: 熔断, 降级(没有异常抛出)
        ghAccountService.transIn(inId,amount);
        // 3 模拟异常
        //抛出异常 回滚全局事务
        int i=1/0;
    }
}
