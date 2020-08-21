package com.seemygo.shop.cloud.mq.msg;

import com.seemygo.shop.cloud.resp.CodeMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 秒杀失败消息
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeckillFailedMsg {
    private String uuid;
    private CodeMsg codeMsg;
}
