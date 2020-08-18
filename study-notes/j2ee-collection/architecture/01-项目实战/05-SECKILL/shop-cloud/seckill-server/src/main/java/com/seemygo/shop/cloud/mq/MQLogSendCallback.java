package com.seemygo.shop.cloud.mq;

import com.seemygo.shop.cloud.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

@Slf4j
public class MQLogSendCallback implements SendCallback {
    @Override
    public void onSuccess(SendResult sendResult) {
        // 当异步消息发送成功时执行
        log.info("[SendCallback] 发送消息成功: {}", JSONUtil.toJSONString(sendResult));
    }
    @Override
    public void onException(Throwable throwable) {
        // 当消息发送时出现异常
        log.error("[SendCallback] 消息发送出现异常!", throwable);
    }
}
