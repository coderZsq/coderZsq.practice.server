package com.coderZsq.serialization.ser;

/**
 * 定义序列化接口
 */
public interface ISerialize {
    /**
     * 序列化方法, 把一个对象转换为字节数组
     */
    <T> byte[] serialize(T obj);

    /**
     * 反序列方法, 把一个字节数组转换为一个对象
     */
    <T> T deSerialize(byte[] buffer, Class<T> clazz);
}
