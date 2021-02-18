package com.sq.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author Leon
 * @date 2021/1/1
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

    @Override
    public void completed(AsynchronousSocketChannel channel, AioServer serverHandler) {
        System.out.println("连接的客户端数：" + AioServer.clientCount.incrementAndGet());
        serverHandler.channel.accept(serverHandler, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new ReadHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AioServer serverHandler) {
        exc.printStackTrace();
        serverHandler.latch.countDown();
    }
}
