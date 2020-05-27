package com.example.rpcservice;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            final Socket socket = serverSocket.accept();
            final ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            final Object o = ois.readObject();
            System.out.println("o = " + o);
            // 写出数据
            final ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("测试数据");
            oos.flush();
        }
    }
}
