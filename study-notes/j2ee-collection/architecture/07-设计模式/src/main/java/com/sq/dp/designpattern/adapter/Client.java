package com.sq.dp.designpattern.adapter;

/**
 * 客户端测试
 */
public class Client {
    public static void main(String[] args) {
        Serializer serializer = new SerializeAdapter();
        RpcProtocol protocol = new RpcProtocol(serializer);
        byte[] bytes = protocol.send("hello rpc");
        protocol.recv(bytes);
    }
}
