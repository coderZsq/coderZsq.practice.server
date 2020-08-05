package com.sq.comsumerhttpserver;

import com.sq.comsumerhttpserver.stream.IReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@SpringBootApplication
@EnableBinding(IReceiveService.class)
public class ComsumerHttpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComsumerHttpServerApplication.class, args);
    }

    @StreamListener("test9999")
    public void onMessage(String msg) {
        System.out.println("消费到的消息是:" + msg);
    }
}