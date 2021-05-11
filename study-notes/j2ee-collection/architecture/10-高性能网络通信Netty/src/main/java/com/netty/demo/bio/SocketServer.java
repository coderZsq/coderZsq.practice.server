package com.netty.demo.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        // 监听 8080 端口
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            System.out.println("等待客户端连接...");

            // 等待客户端连接: 阻塞
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接上来了...");
            new Thread(() -> {
                try {
                    handler(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void handler(Socket clientSocket) throws IOException {
        byte[] bytes = new byte[1024];

        System.out.println("准备读取数据..");
        // 读取客户端数据, 没有数据时阻塞
        int read = clientSocket.getInputStream().read(bytes);
        System.out.println("read完毕..");

        if (read != -1) {
            System.out.println("接收到客户端的数据: " + new String(bytes, 0, read));
        }
        // clientSocket.getOutputStream().write("HelloClient".getBytes());
        // clientSocket.getOutputStream().flush();
    }
}
