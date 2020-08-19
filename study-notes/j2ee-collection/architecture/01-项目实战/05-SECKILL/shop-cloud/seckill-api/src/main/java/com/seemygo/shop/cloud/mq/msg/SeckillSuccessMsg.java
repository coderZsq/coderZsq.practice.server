package com.seemygo.shop.cloud.mq.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 秒杀成功消息
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeckillSuccessMsg implements Serializable {
    private String uuid;
    private String orderNo;
}
