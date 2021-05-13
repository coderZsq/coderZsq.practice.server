package cn.wolfcode.netty.demo.netty.base;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Leon
 */
public class NettyClient {

    public static void main(String[] args) throws Exception {
        // 创建一个 NIO 事件循环组（线程组）
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // 创建客户端引导程序（启动程序）
            Bootstrap bootstrap = new Bootstrap();

            // 设置相关参数
            // 设置线程组
            bootstrap.group(group)
                    // 设置客户端通道的实现：NioSocketChannel
                    .channel(NioSocketChannel.class)
                    // 设置通道初始化时的处理器
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 加入处理器
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("Netty 客户端启动成功...");

            // 连接到对应的服务端
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 8082).sync();

            // 监听关闭事件
            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
