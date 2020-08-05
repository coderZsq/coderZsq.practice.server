package com.sq.orderserver.web;

import com.sq.orderserver.event.RemoteAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoteSenderController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping("/remote/send/event")
    public String receive(String limitNum, String serviceId) {
        System.out.println("业务处理");
        // 发布事件
        publisher.publishEvent(new RemoteAppEvent(limitNum, serviceId));
        return "success";
    }
}
