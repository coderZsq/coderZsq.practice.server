package cn.wolfcode.netty.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author Leon
 */
public class AIOServer {

    public static void main(String[] args) throws Exception {
        // 创建异步服务端 socket 通道，并绑定到 8080 端口
        final AsynchronousServerSocketChannel serverChannel =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8080));

        // 注册接收客户端连接事件：异步-当连接成功后会自动调用 CompletionHandler 回调
        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
                try {
                    // 打印线程信息
                    System.out.println("2--" + Thread.currentThread().getName());
                    // 此处才是真正的接收客户端连接，必须要写，否则客户端无法连接上
                    serverChannel.accept(attachment, this);
                    System.out.println("有新的客户端连接：" + socketChannel.getRemoteAddress());

                    // 准备读取数据，申请读取数据的内存缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    // 注册数据读取处理器：异步处理
                    socketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer buffer) {
                            // 当读取成功时会调用该方法
                            // 打印线程信息
                            System.out.println("3--" + Thread.currentThread().getName());
                            // 翻转缓冲区（针对 ByteBuffer 同时可读可写的特性所做的特殊调整）
                            // 可以理解为将当前可读取的区域翻转到后面，其他空余空间放到前面用于接收其他数据
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, result));

                            // 向客户端回传数据
                            socketChannel.write(ByteBuffer.wrap("<SERVER>: hello AIO client...".getBytes()));
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer buffer) {
                            // 读取数据出现异常时调用
                            exc.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                // 当建立连接失败时调用
                exc.printStackTrace();
            }
        });

        // 打印主线程信息
        System.out.println("1--" + Thread.currentThread().getName());
        // 线程睡眠，等待处理客户端（简单处理）
        Thread.sleep(Integer.MAX_VALUE);
    }
}
