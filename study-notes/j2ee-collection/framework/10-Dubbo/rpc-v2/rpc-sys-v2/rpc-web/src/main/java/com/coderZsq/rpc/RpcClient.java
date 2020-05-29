package com.coderZsq.rpc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcClient {
    private String host;
    private int port;
    private RpcDiscover rpcDiscover;

    public RpcClient(RpcDiscover rpcDiscover) {
        this.rpcDiscover = rpcDiscover;
    }

    public Object send(RpcRequest rpcRequest) {
        // 2 从zookeeper注册中心获取
        String serverAddr = rpcDiscover.discover();
        String[] args = serverAddr.split(":");
        host = args[0];
        port = Integer.valueOf(args[1]);
        // 1. 连接服务端
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            // 1 通过输出流发送数据到服务端 --> 对象输出流
            // 调用一个方法 找到对应的字节码对象 通过一个名称 找到对应的方法 调用方法的参数类型, 实际参数列表
            oos.writeObject(rpcRequest);
            oos.flush();
            // 2 接收服务端数据
            RpcResponse rpcResponse = (RpcResponse) ois.readObject();
            System.out.println("服务端响应给客户端的请求结果: rpcResponse = " + rpcResponse);
            return rpcResponse.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
