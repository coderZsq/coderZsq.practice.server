package com.sq.dp.designpattern.adapter;

/**
 * 模拟 rpc 协议类
 */
public class RpcProtocol {
    private Serializer serializer;

    public RpcProtocol(Serializer serializer) {
        this.serializer = serializer;
    }

    public byte[] send(String msg) {
        // 序列化消息
        byte[] bytes = serializer.serialize(msg);
        System.out.println("rpc发送消息: " + msg);
        // TODO......
        return bytes;
    }

    public String recv(byte[] bytes) {
        String msg = serializer.deserialize(bytes);
        System.out.println("rpc接收消息: " + msg);
        // 反序列化消息
        return msg;
    }
}
