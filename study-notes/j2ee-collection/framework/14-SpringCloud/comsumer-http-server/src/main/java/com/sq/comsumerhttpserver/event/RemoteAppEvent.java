package com.sq.comsumerhttpserver.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

// 远程处理事件
@Data
public class RemoteAppEvent extends ApplicationEvent {

    public RemoteAppEvent(Object source) {
        super(source);
    }
}
