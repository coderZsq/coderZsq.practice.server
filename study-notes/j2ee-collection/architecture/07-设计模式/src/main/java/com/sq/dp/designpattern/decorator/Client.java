package com.sq.dp.designpattern.decorator;

import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "A0035");
        map.put("msg", "服务端炸了!");

        JSONLogDecorator log = new JSONLogDecorator(new Log4jLogger());
        log.info(map.toString());
        log.jsonInfo(map);
    }
}
