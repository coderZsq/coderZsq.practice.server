package com.coderZsq.rpc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcServer {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private int port;

    private String hostname;

    private ApplicationContext applicationContext;

    private RpcRegistry rpcRegistry;

    public RpcServer(String hostname, int port, ApplicationContext applicationContext, RpcRegistry rpcRegistry) {
        this.hostname = hostname;
        this.port = port;
        this.applicationContext = applicationContext;
        this.rpcRegistry = rpcRegistry;
    }

    // 服务启动类 --> 启动一个ServerSocket
    @PostConstruct
    public void startup() {
        try {
            // 1 创建一个socket通信
            ServerSocket serverSocket = new ServerSocket(port);
            // 2. 往注册中心zookeeper中注册对应的地址信息 host, port --> 地址信息
            String addrName = null; // 0.0.0.0:9000
            String addr = hostname;
            addrName = addr + ":" + port;
            System.out.println("addrName = " + addrName);
            rpcRegistry.createNode(addrName);
            System.out.println("服务端启动成功, 等待客户端连接.....");
            // 2 接收客户端请求
            while (true) {
                Socket socket = serverSocket.accept(); // 等待客户端连接 如果没有客户端连接, 会阻塞在这里
                // 处理请求 --> 使用多线程技术 --> 线程池技术
                executorService.submit(new RpcServerHandler(socket, applicationContext));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
