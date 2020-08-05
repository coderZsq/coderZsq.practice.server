package com.sq.comsumerhttpserver.listener;

import com.sq.comsumerhttpserver.event.RemoteAppEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RemoteAppEventListener implements ApplicationListener<RemoteAppEvent> {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void onApplicationEvent(RemoteAppEvent event) {
        Object obj = event.getSource();
        System.out.println(serverPort + "需要调整的数据: " + obj);
    }
}
