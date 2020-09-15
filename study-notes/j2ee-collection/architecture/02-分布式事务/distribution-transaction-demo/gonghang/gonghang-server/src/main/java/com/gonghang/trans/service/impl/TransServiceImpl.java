package com.gonghang.trans.service.impl;

import com.gonghang.trans.mapper.TransMapper;
import com.gonghang.trans.service.ITransService;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
@AllArgsConstructor
public class TransServiceImpl implements ITransService {

    private TransMapper transMapper;

    private RocketMQTemplate rocketMQTemplate;
    @Override
    public int transOut(Integer outId, Integer amount) {
        return transMapper.transOut(outId, amount);
    }
    @Override
    public void transOutLog(Integer outId, Integer inId, Integer amount, String transId) {
        transMapper.transOutLog(outId,inId,amount,transId);
    }
    @Override
    public int queryTransLog(String transId) {
        return transMapper.queryTransLog(transId);
    }

/*  普通消息处理流程
    @Override
    @Transactional// 本地事务管理器 DataSourceManager
    public void trans(Integer inId, Integer outId, Integer amount) {
         //1 调用工商银行的转出服务
         transOut(outId, amount);
         //2 发送消息到消息中心, 告诉农业银行 转入
         final HashMap<String, Object> map = new HashMap<>();
         map.put("inId",inId);
         map.put("amount",amount);
         //发送出去了, 但是没有到消息中心
         rocketMQTemplate.syncSend("nonghang-trans",map);
         throw  new RuntimeException("数据库保存异常");
    }*/


    //事务消息
    @Override
    @Transactional
    public void trans(Integer inId, Integer outId, Integer amount) {
        Map<String, Object> map = new HashMap<>();
        map.put("inId",inId);
        map.put("amount",amount);
        map.put("outId",outId);
        map.put("transId", UUID.randomUUID().toString());
        final Message<Map<String, Object>> message = MessageBuilder.withPayload(map).build();
        //1 发送事务消息
        rocketMQTemplate.sendMessageInTransaction("test001","nonghang-trans",message,null);
    }
}
