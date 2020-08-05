package com.sq.comsumerhttpserver.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface IReceiveService {
    @Input("test9999") // channel 的名字
    public MessageChannel receiveChannel();
}