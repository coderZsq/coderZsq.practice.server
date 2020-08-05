package com.sq.producerhtttpserver.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

//  远程处理事件
@Data
public class RemoteAppEvent extends ApplicationEvent {

    // 服务Id
    private String serviceId;

    public RemoteAppEvent(Object source, String serviceId) {
        super(source);
        this.serviceId = serviceId;
    }
}
