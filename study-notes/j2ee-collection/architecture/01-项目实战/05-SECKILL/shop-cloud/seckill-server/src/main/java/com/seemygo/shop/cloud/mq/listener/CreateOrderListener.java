package com.seemygo.shop.cloud.mq.listener;

import com.seemygo.shop.cloud.mq.MQConstants;
import com.seemygo.shop.cloud.mq.msg.CreateSeckillOrderMsg;
import com.seemygo.shop.cloud.service.IOrderInfoService;
import com.seemygo.shop.cloud.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = MQConstants.CREATE_ORDER_DEST,
        selectorExpression = MQConstants.CREATE_ORDER_TAG,
        consumerGroup = MQConstants.CREATE_ORDER_CONSUMER_GROUP
)
public class CreateOrderListener implements RocketMQListener<CreateSeckillOrderMsg> {
    @Autowired
    private IOrderInfoService orderInfoService;

    @Override
    public void onMessage(CreateSeckillOrderMsg message) {
        log.info("[创建订单消费者] 收到创建订单消息: {}", JSONUtil.toJSONString(message));
        // 创建订单
        orderInfoService.doSeckill(message.getSeckillId(), message.getUserId());
    }
}
