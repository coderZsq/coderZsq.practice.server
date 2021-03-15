package com.sq.dp.designpattern.adapter;

import java.nio.charset.StandardCharsets;

/**
 * ProtoBuf 序列化
 */
public class ProtoBuf {
    public byte[] serialize(String msg) {
        System.out.println("ProtoBuf 序列化: " + msg);
        return msg.getBytes();
    }

    public String deserialize(byte[] bytes) {
        String msg = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("ProtoBuf 序列化: " + msg);
        return msg;
    }
}
