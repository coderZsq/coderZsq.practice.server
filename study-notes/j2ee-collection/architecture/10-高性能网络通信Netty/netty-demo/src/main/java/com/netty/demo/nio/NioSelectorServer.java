package cn.wolfcode.netty.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Leon
 */
public class NioSelectorServer {

    public static void main(String[] args) throws IOException {

        // 打开服务端 socket 通道
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        // 绑定到 8080 端口
        serverSocket.socket().bind(new InetSocketAddress(8080));

        // 设置服务端为非阻塞
        serverSocket.configureBlocking(false);

        // 打开 Selector 用于处理 channel（此处就是 epoll 的应用）
        Selector selector = Selector.open();

        // 把服务端 channel 注册到 selector，并且注册 accept 事件，表示当有客户端连接上来时，进行处理
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动完成...");

        while (true) {
            // 此处进行阻塞，当有事件发生时才继续往后走
            selector.select();

            // 通过 selector 拿到所有被触发事件对应的 key 实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            // 对有事件的 key 进行遍历处理
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                // 如果是 OP_ACCEPT 事件，则表示有客户端连接上来了，将客户端通道也注册到 selector 上
                if (key.isAcceptable()) {
                    // 拿到事件对应的服务端通道
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // 接收客户端连接
                    SocketChannel socketChannel = server.accept();
                    // 设置客户端通道为非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 将客户端通道注册到 selector 上面，并注册读事件（如果需要写数据也可以注册写事件）
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功...");
                } else if (key.isReadable()) {

                    // 如果是 OP_READ 事件，表示有客户端发送了数据过来
                    // 获取到对应的客户端通道，对数据进行处理
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 申请缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    // 读取数据，此处同样是非阻塞的
                    int len = socketChannel.read(buffer);

                    // 如果有数据，把数据打印出来
                    if (len > 0) {
                        System.out.println("接收到消息：" + new String(buffer.array(), 0, len));
                    } else if (len == -1) {
                        // 如果客户端断开连接，关闭 Socket
                        System.out.println("客户端断开连接");
                        socketChannel.close();
                    }
                }

                //从事件集合里删除本次处理的key，防止下次select重复处理
                iterator.remove();
            }
        }
    }
}
