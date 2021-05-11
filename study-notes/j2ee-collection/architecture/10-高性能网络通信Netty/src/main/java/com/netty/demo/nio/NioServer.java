package com.netty.demo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NioServer {
    public static List<SocketChannel> channels = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 打开服务端 socket 通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 绑定 socket 监听 8080 端口
        ssc.socket().bind(new InetSocketAddress(8080));
        // 设置通道为非阻塞模式
        ssc.configureBlocking(false);
        System.out.println("服务端启动成功...");

        while (true) {
            // 由于上面设置了服务端通道为非阻塞模式, 因此此处不会阻塞
            // NIO 的非阻塞是由操作系统来实现的, 底层就是调用了 Linux 内核的 accept 函数
            SocketChannel channel = ssc.accept();
            if (channel != null) {
                System.out.println("有客户端连接上来了...");
                // 设置客户端为非阻塞
                channel.configureBlocking(false);

                // 将接收到的客户端通道保存到集合中
                channels.add(channel);
            }

            // 遍历已连接的通道尝试读取数据
            Iterator<SocketChannel> iterator = channels.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                // 申请一块内存缓存区域
                ByteBuffer buffer = ByteBuffer.allocate(256);
                // 读取客户端数据, 由于上面配置了非阻塞, 此处并不会阻塞
                int len = sc.read(buffer);
                if (len > 0) {
                    System.out.println("收到客户端消息: " + new String(buffer.array(), 0, len));
                } else if (len < 0) {
                    iterator.remove();
                    System.out.println("客户端断开连接...");
                }
            }
        }
    }
}
