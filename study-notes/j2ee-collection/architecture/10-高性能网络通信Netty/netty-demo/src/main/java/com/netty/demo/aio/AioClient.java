package cn.wolfcode.netty.demo.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * @author Leon
 */
public class AioClient {

    public static void main(String... args) throws Exception {
        // 创建异步 socket 通道
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        // 绑定到指定的服务端
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080)).get();
        // 向服务端写数据
        socketChannel.write(ByteBuffer.wrap("<CLIENT>: hello AIO Server...".getBytes()));
        // 申请空间
        ByteBuffer buffer = ByteBuffer.allocate(512);
        // 读取服务端回传数据
        Integer len = socketChannel.read(buffer).get();
        if (len != -1) {
            System.out.println("收到服务端回传消息：" + new String(buffer.array(), 0, len));
        } else {
            socketChannel.close();
        }
    }
}
