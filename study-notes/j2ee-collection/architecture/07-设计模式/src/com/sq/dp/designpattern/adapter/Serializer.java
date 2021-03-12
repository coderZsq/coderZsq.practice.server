package com.sq.dp.designpattern.adapter;

/**
 * 序列化接口
 */
public interface Serializer {
    byte[] serialize(String msg);

    String deserialize(byte[] bytes);
}

