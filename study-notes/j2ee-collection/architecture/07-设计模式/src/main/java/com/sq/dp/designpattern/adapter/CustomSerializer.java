package com.sq.dp.designpattern.adapter;

import java.nio.charset.StandardCharsets;

/**
 * 自定义序列化实现
 */
public class CustomSerializer implements Serializer {
    @Override
    public byte[] serialize(String msg) {
        System.out.println("自定义序列化: " + msg);
        return msg.getBytes();
    }

    @Override
    public String deserialize(byte[] bytes) {
        String msg = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("自定义反序列化: " + msg);
        return msg;
    }
}
