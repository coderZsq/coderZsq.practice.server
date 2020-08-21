package com.seemygo.shop.cloud.mq.listener;

import com.seemygo.shop.cloud.exception.BusinessException;
import com.seemygo.shop.cloud.mq.MQConstants;
import com.seemygo.shop.cloud.mq.MQLogSendCallback;
import com.seemygo.shop.cloud.mq.msg.CreateSeckillOrderMsg;
import com.seemygo.shop.cloud.mq.msg.DelayOrderMsg;
import com.seemygo.shop.cloud.mq.msg.SeckillFailedMsg;
import com.seemygo.shop.cloud.mq.msg.SeckillSuccessMsg;
import com.seemygo.shop.cloud.resp.CodeMsg;
import com.seemygo.shop.cloud.service.IOrderInfoService;
import com.seemygo.shop.cloud.util.JSONUtil;
import com.seemygo.shop.cloud.web.SeckillCodeMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = MQConstants.SECKILL_ORDER_TOPIC,
        selectorExpression = MQConstants.CREATE_ORDER_TAG,
        consumerGroup = MQConstants.CREATE_ORDER_CONSUMER_GROUP
)
public class CreateOrderMQListener implements RocketMQListener<CreateSeckillOrderMsg> {
    @Autowired
    private IOrderInfoService orderInfoService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(CreateSeckillOrderMsg message) {
        log.info("[创建订单消费者] 收到创建订单消息: {}", JSONUtil.toJSONString(message));

        try {
            // 创建订单
            String orderNo = orderInfoService.doSeckill(message.getSeckillId(), message.getUserId());
            try {
                // 创建订单成功
                // 发送创建订单成功的消息, 由websocket服务通知前端用户
                rocketMQTemplate.asyncSend(MQConstants.SECKILL_SUCCESS_DEST, new SeckillSuccessMsg(message.getUuid(), orderNo), new MQLogSendCallback());
                // 发送延迟消息, 用户检查15分钟后订单是否已支付
                // 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
                rocketMQTemplate.asyncSend(MQConstants.DELAY_ORDER_DEST, new GenericMessage<>(new DelayOrderMsg(orderNo, message.getSeckillId())), new MQLogSendCallback(), 1000, 3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            // 创建订单失败
            log.error("[创建订单消费者] 创建订单失败!", e);
            // 发送订单创建失败消息
            CodeMsg codeMsg = SeckillCodeMsg.OP_ERROR;
            if (e instanceof BusinessException) {
                BusinessException be = (BusinessException) e;
                codeMsg = be.getCodeMsg();
            }
            // uuid/失败的原因: code/msg
            rocketMQTemplate.asyncSend(MQConstants.SECKILL_FAILED_DEST, new SeckillFailedMsg(message.getUuid(), codeMsg), new MQLogSendCallback());
            // 同步Redis库存, 清除本地售完标记, 清除Redis用户重复下单标记
            orderInfoService.seckillFailed(message.getSeckillId(), message.getUserId());
        }
    }
}
