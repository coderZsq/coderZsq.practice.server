package com.gonghang.trans.mq;

import com.alibaba.fastjson.JSON;
import com.gonghang.trans.service.ITransService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@RocketMQTransactionListener(txProducerGroup = "test001")
public class TransactionListenerImpl  implements RocketMQLocalTransactionListener {
    @Autowired
    private ITransService transService;
    //执行本地事务
    @Override
    @Transactional
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        //获取到对应的参数
        System.out.println("执行本地事务0000");
        byte[] bytes = (byte[]) msg.getPayload();
        Map<String,Object> map= (Map<String, Object>) JSON.parse(bytes);
        Integer inId = (Integer) map.get("inId");
        String transId = (String) map.get("transId");
        Integer outId = (Integer) map.get("outId");
        Integer amount = (Integer) map.get("amount");
        transService.transOut(outId,amount); //执行业务操作
        //记录执行日志
        transService.transOutLog(outId,inId,amount,transId);
        return RocketMQLocalTransactionState.UNKNOWN;
    }
    //事务回查方法
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println("开启事务回查");
        byte[] bytes = (byte[]) msg.getPayload();
        Map<String,Object> map= (Map<String, Object>) JSON.parse(bytes);
        String transId = (String) map.get("transId");
        //查询交易日志
        int count = transService.queryTransLog(transId);
        if(count>0){
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.UNKNOWN; //默认情况, 回查15次, 如果查不到, 就返回Rollback
    }
}
