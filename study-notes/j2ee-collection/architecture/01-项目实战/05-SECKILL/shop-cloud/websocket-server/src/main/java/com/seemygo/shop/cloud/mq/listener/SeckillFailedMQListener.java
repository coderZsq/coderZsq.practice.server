package com.seemygo.shop.cloud.mq.listener;

import com.seemygo.shop.cloud.core.WebSocketSessionManager;
import com.seemygo.shop.cloud.mq.MQConstants;
import com.seemygo.shop.cloud.mq.msg.SeckillFailedMsg;
import com.seemygo.shop.cloud.mq.msg.SeckillSuccessMsg;
import com.seemygo.shop.cloud.resp.Result;
import com.seemygo.shop.cloud.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = MQConstants.SECKILL_ORDER_TOPIC,
        selectorExpression = MQConstants.SECKILL_FAILED_TAG,
        consumerGroup = MQConstants.SECKILL_FAILED_CONSUMER_GROUP
)
public class SeckillFailedMQListener implements RocketMQListener<SeckillFailedMsg> {

    @Override
    public void onMessage(SeckillFailedMsg message) {

        log.info("[秒杀失败消息] 收到秒杀订单失败消息: {}", JSONUtil.toJSONString(message));

        // 获取到消息内容, 得到用户的连接, 向用户发送秒杀失败的消息
        Result<?> result = Result.error(message.getCodeMsg());
        // 消息发送重试机制
        boolean ret;
        int count = 0, max = 3;
        do {
            // 发送消息给客户端
            ret = WebSocketSessionManager.INSTANCE.sendMsg(message.getUuid(), result);
            // 如果消息发送失败, 睡10ms, 然后再进行消息重发
            // 睡10ms
            if (!ret) {
                try {
                    Thread.sleep(10L);
                } catch (Exception ignored) {
                }
            }
            count++;
        } while (!ret && count <= max);
    }
}
