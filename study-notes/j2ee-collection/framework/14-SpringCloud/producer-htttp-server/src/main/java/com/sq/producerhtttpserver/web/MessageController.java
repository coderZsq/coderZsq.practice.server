package com.sq.producerhtttpserver.web;

import com.sq.producerhtttpserver.stream.ISenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    private ISenderService senderService;

    @RequestMapping("sendMsg")
    public String sendMsg(String msg) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("charset-encoding", "UTF-8");
        headers.put("content-type", MediaType.TEXT_PLAIN_VALUE);
        senderService.vipChannel().send(new GenericMessage<>(msg, headers));
        return "success";
    }
}
