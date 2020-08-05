package com.sq.producerhtttpserver.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ISenderService {
    @Output("test9527") // 定义channel名字
    MessageChannel vipChannel();
}
