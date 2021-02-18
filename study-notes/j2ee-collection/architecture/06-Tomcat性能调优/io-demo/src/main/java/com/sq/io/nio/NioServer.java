package com.sq.io.nio;

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
 * @date 2021/1/1
 */
public class NioServer implements Runnable {

    private Selector selector;
    private Selector selector1;
    private ServerSocketChannel serverChannel;
    private volatile boolean started;

    /**
     * 构造方法
     *
     * @param port 指定要监听的端口号
     */
    public NioServer(int port) {
        try {
            selector = Selector.open();
            selector1 = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            started = true;
            System.out.println("服务器已启动，端口号：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        started = false;
    }

    @Override
    public void run() {
        while (started) {
            try {
                selector.select(1000);
                // selectedKeys: key = 准备好数据的连接
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
                selector1.select(1000);
                Set<SelectionKey> keys1 = selector1.selectedKeys();
                Iterator<SelectionKey> it1 = keys1.iterator();
                SelectionKey key1 = null;
                while (it1.hasNext()) {
                    key1 = it1.next();
                    it1.remove();
                    try {
                        handleInput(key1);
                    } catch (Exception e) {
                        if (key1 != null) {
                            key1.cancel();
                            if (key1.channel() != null) {
                                key1.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        if (selector != null)
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (selector1 != null)
            try {
                selector1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector1, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                // ByteBuffer 不是直接内存
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(buffer);
                if (readBytes > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String msg = new String(bytes, "UTF-8");
                    System.out.println("服务器收到消息：" + msg);
                    doWrite(sc, "hello client");
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        byte[] bytes = response.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }

}
