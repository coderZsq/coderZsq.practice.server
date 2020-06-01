package com.coderZsq.serialization.ser.impl;

import com.coderZsq.serialization.ser.ISerialize;

import java.io.*;

public class JdkSerialize implements ISerialize {
    @Override
    public <T> byte[] serialize(T obj) {
        // 2. 定义一个用于接收对象信息的字节数组输入流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 1. 使用对象的输入流读取对象信息
        try {
            // 对象输出流, 传入对象输出的目的地
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            // 写入一个对象
            oos.writeObject(obj);
            // 把字节数组流转换为字节数组
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("序列化失败", e);
        }
    }

    @Override
    public <T> T deSerialize(byte[] buffer, Class<T> clazz) {
        // 1. 把字节数组转换为输入流
        ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (T)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("反序列化失败", e);
        }
    }
}
