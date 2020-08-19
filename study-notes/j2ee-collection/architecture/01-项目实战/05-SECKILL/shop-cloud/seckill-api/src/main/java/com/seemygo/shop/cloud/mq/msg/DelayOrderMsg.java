package com.seemygo.shop.cloud.mq.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DelayOrderMsg implements Serializable {
    private String orderNo;
    private Long seckillId;
}
