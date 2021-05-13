package cn.wolfcode.netty.demo.netty.reconnect;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * handler 的生命周期回调接口调用顺序:
 * handlerAdded -> channelRegistered -> channelActive -> channelRead -> channelReadComplete
 * -> channelInactive -> channelUnRegistered -> handlerRemoved
 * <p>
 * handlerAdded: 新建立的连接会按照初始化策略，把 handler 添加到该 channel 的 pipeline 里面，也就是 channel.pipeline.addLast(new LifeCycleInBoundHandler) 执行完成后的回调；
 * channelRegistered: 当该连接分配到具体的 worker 线程后，该回调会被调用。
 * channelActive：channel 的准备工作已经完成，所有的 pipeline 添加完成，并分配到具体的线上上，说明该 channel 准备就绪，可以使用了。
 * channelRead：客户端向服务端发来数据，每次都会回调此方法，表示有数据可读；
 * channelReadComplete：服务端每次读完一次完整的数据之后，回调该方法，表示数据读取完毕；
 * channelInactive：当连接断开时，该回调会被调用，说明这时候底层的 TCP 连接已经被断开了。
 * channelUnRegistered: 对应 channelRegistered，当连接关闭后，释放绑定的 worker 线程；
 * handlerRemoved： 对应 handlerAdded，将 handler 从该 channel 的 pipeline 移除后的回调方法。
 *
 * @author Leon
 */
public class LifeCycleInBoundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("channelRegistered: channel 注册到 NioEventLoop");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("channelUnregistered: channel 取消和 NioEventLoop 的绑定");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("channelActive: channel 准备就绪");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("channelInactive: channel 被关闭");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("channelRead: channel 中有可读的数据");
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("channelReadComplete: channel 读数据完成");
        super.channelReadComplete(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("handlerAdded: handler 被添加到 channel 的 pipeline");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("handlerRemoved: handler 从 channel 的 pipeline 中移除");
        super.handlerRemoved(ctx);
    }
}
