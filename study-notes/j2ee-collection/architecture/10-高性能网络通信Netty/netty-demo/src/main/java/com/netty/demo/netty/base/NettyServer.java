package cn.wolfcode.netty.demo.netty.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Leon
 */
public class NettyServer {

    public static void main(String[] args) throws Exception {

        // 创建两个线程组，分别用于处理连接和读写事件（业务），NioEventLoop 的个数默认为 cpu 核数的两倍
        // bossGroup 只处理连接事件，当有客户端建立连接时由该线程组进行处理
        EventLoopGroup bossGroup = new NioEventLoopGroup(8);

        // workerGroup 只处理业务（读写事件）
        EventLoopGroup workerGroup = new NioEventLoopGroup(32);
        try {
            // 创建服务端引导程序
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 配置服务端相关参数，首先保定线程组
            bootstrap.group(bossGroup, workerGroup)
                    // 设置服务端通道实现
                    .channel(NioServerSocketChannel.class)
                    // 初始化服务器连接队列大小，服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接。
                    // 多个客户端同时来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 创建初始化对象，并绑定服务端处理器，当客户端连接上来时，根据对应的事件执行处理器中对应的方法
                    // 思考此处的 handler 是绑定在 NioServerSocketChannel 还是绑定在 NioSocketChannel
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 此处的 ch = NioSocketChannel = 客户端通道
                            // 对 workerGroup 的 SocketChannel 设置处理器
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("Netty 服务端启动完成...");

            // 绑定一个端口并且同步, 生成了一个 ChannelFuture 异步对象，通过 isDone() 等方法可以判断异步事件的执行情况
            // 启动服务器(并绑定端口)，bind 是异步操作，sync 方法是等待异步操作执行完毕
            ChannelFuture cf = bootstrap.bind(8081).sync();
            ChannelFuture cf2 = bootstrap.bind(8082).sync();
            // 给 cf 注册监听器，监听我们关心的事件
            /* cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (cf.isSuccess()) {
                        System.out.println("监听端口 8080 成功");
                    } else {
                        System.out.println("监听端口 8080 失败");
                    }
                }
            });*/
            // 等待服务端监听端口关闭，closeFuture 是异步操作
            // 通过 sync 方法同步等待通道关闭处理完毕，这里会阻塞等待通道关闭完成，内部调用的是Object的wait()方法
            cf.channel().closeFuture().sync();
            cf2.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
