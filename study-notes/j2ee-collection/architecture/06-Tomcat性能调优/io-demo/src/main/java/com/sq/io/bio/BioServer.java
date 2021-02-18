package com.sq.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Leon
 * @date 2021/1/1
 */
public class BioServer {

    private static ServerSocket server;
    private static int PORT = 8080;

    static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        if (server != null) return;

        try {
            server = new ServerSocket(PORT);
            System.out.println("服务器已启动，准备接收客户端数据...");
            while (true) {
                Socket socket = server.accept();
                executorService.submit(new ServerHandler(socket));
            }
        } finally {
            if (server != null) {
                server.close();
                server = null;
                System.out.println("服务器已关闭...");
            }
        }
    }

    private static class ServerHandler implements Runnable {

        private Socket socket;

        public ServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            InputStream in = null;
            OutputStream out = null;
            try {
                byte[] recv = new byte[1024];
                in = socket.getInputStream();
                in.read(recv);
                System.out.println("服务器收到消息：" + new String(recv, StandardCharsets.UTF_8));
                out = socket.getOutputStream();
                out.write("hello client".getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    in = null;
                }
            }
        }
    }
}
