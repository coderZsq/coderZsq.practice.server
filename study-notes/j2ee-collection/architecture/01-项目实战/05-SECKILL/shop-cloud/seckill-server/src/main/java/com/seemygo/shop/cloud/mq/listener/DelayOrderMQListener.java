package com.seemygo.shop.cloud.mq.listener;

import com.seemygo.shop.cloud.mq.MQConstants;
import com.seemygo.shop.cloud.mq.msg.DelayOrderMsg;
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
        topic = MQConstants.SECKILL_ORDER_TOPIC,
        selectorExpression = MQConstants.DELAY_ORDER_TAG,
        consumerGroup = MQConstants.DELAY_ORDER_CONSUMER_GROUP
)
public class DelayOrderMQListener implements RocketMQListener<DelayOrderMsg> {

    @Autowired
    private IOrderInfoService orderInfoService;

    @Override
    public void onMessage(DelayOrderMsg message) {
        log.info("[延迟订单消息] 收到延迟订单消息, 检查订单是否已支付: {}", JSONUtil.toJSONString(message));
        // 检查订单是否超时, 如果已超时, 需要将订单库存进行回补(MySQL, Redis), 清除本地售完标记
        orderInfoService.checkTimeout(message.getOrderNo(), message.getSeckillId());
    }
}
