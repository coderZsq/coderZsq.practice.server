package com.example.rpcservice;

import com.coderZsq.product.common.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketDemo {
    public static void main(String[] args) throws Exception {
        final Socket socket = new Socket("127.0.0.1", 8888);
        final ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        // 2 封装请求参数
        RpcRequest rpcRequest = new RpcRequest();
        // 设置方法
        rpcRequest.setMethodName("method.getName()");
        // 设置请求参数
        rpcRequest.setParameters(args);
        // 设置参数类型
        rpcRequest.setParameterTypes(null);
        // 传入接口的简单名称
        rpcRequest.setClassName(null);
        oos.writeObject(rpcRequest); // 往服务端发送请求
        oos.flush();
        final ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        final Object obj = ois.readObject();
        System.out.println("obj = " + obj);
    }
}
