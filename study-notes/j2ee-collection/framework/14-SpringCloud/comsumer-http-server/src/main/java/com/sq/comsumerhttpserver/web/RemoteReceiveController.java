package com.sq.comsumerhttpserver.web;

import com.sq.comsumerhttpserver.event.RemoteAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoteReceiveController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping("remote/receive/event")
    public String receive(@RequestBody Map map) {
        Object data = map.get("data");
        publisher.publishEvent(new RemoteAppEvent(data));
        return "ok";
    }
}
