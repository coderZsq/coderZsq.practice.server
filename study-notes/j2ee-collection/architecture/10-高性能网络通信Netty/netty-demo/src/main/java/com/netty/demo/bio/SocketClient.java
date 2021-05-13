package cn.wolfcode.netty.demo.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Leon
 */
public class SocketClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8080);

        //发送数据到服务端
        socket.getOutputStream().write("<CLIENT>: Hello.......".getBytes());
        socket.getOutputStream().flush();
        System.out.println("数据发送完成");

        byte[] bytes = new byte[1024];
        //接收服务端回复数据
        int read = socket.getInputStream().read(bytes);
        System.out.println("收到服务端回传数据：" + new String(bytes, 0, read));
        socket.close();
    }
}
