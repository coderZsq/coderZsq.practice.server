package com.nonghang.trans.service.impl;

import com.nonghang.trans.mapper.TransMapper;
import com.nonghang.trans.service.ITransService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransServiceImpl implements ITransService {

    private TransMapper transMapper;

    @Override
    @Transactional
    public void tranIn(Integer inId,Integer amount,String transId) {
        //在执行业务之前, 需要判断当前的额交易是否已经执行过
        int count = transMapper.queryTransLog(transId);
        if(count < 1){
            transMapper.transIn(inId,amount);
            //插入日志
            transMapper.transInLog(inId,amount,transId);
        }else{
            throw  new RuntimeException("业务已经执行成功");
        }
    }
}
