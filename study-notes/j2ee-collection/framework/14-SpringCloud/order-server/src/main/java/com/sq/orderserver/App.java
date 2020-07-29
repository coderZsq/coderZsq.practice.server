package com.sq.orderserver;

import org.springframework.web.client.RestTemplate;

public class App {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 30; i++) {
            new Thread() { // 模拟30个线程同时发送请求
                @Override
                public void run() {
                    RestTemplate restTemplate = new RestTemplate();
                    String value = restTemplate.getForObject("http://localhost:8090/test1", String.class);
                    System.out.println("value = " + value);
                }
            }.start();
        }

        System.in.read();
    }
}
