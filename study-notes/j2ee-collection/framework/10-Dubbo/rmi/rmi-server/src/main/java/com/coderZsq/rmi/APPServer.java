package com.coderZsq.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

// 启动服务
public class APPServer {
    public static void main(String[] args) throws Exception {
        // 1. 注册服务端口
        LocateRegistry.createRegistry(1099);
        // 2. 提供具体的服务, 先创建服务
        IHelloService helloService = new HelloServiceImpl();
        // 3. 发布远程服务
        Naming.bind("rmi://127.0.0.1:1099/hello", helloService);
        System.out.println("服务启动成功");
    }
}
