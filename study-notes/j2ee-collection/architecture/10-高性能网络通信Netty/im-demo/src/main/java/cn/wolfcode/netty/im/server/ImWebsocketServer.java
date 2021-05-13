package cn.wolfcode.netty.im.server;

import cn.wolfcode.netty.im.constant.Constants;
import cn.wolfcode.netty.im.server.connertor.ImConnector;
import cn.wolfcode.netty.im.server.handler.ImWebSocketServerHandler;
import cn.wolfcode.netty.im.server.model.MessageProto;
import cn.wolfcode.netty.im.server.proxy.MessageProxy;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;


/**
 * @author Leon
 */
public class ImWebsocketServer {

    private final static Logger log = LoggerFactory.getLogger(ImWebsocketServer.class);

    private ProtobufDecoder decoder = new ProtobufDecoder(MessageProto.Model.getDefaultInstance());

    private MessageProxy proxy = null;
    private ImConnector connector;
    private int port;

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    public void init() throws Exception {
        log.info("start im websocket server ...");

        // Server 服务启动
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();

                // HTTP 请求的解码和编码
                pipeline.addLast(new HttpServerCodec());
                // 把多个消息转换为一个单一的 FullHttpRequest 或是 FullHttpResponse，
                // 原因是 HTTP 解码器会在每个 HTTP 消息中生成多个消息对象 HttpRequest/HttpResponse, HttpContent, LastHttpContent
                pipeline.addLast(new HttpObjectAggregator(Constants.ImServerConfig.MAX_AGGREGATED_CONTENT_LENGTH));
                // 主要用于处理大数据流，比如一个 1G 大小的文件如果你直接传输肯定会撑暴 jvm 内存的; 增加之后就不用考虑这个问题了
                pipeline.addLast(new ChunkedWriteHandler());
                // WebSocket 数据压缩
                pipeline.addLast(new WebSocketServerCompressionHandler());
                // 协议包长度限制
                pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true, Constants.ImServerConfig.MAX_FRAME_LENGTH, false, true));
                // 协议包解码
                pipeline.addLast(new MessageToMessageDecoder<WebSocketFrame>() {
                    @Override
                    protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> objs) throws Exception {
                        ByteBuf buf = ((BinaryWebSocketFrame) frame).content();
                        objs.add(buf);
                        buf.retain();
                    }
                });
                // 协议包编码
                pipeline.addLast(new MessageToMessageEncoder<MessageLiteOrBuilder>() {
                    @Override
                    protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) throws Exception {
                        ByteBuf result = null;
                        if (msg instanceof MessageLite) {
                            result = wrappedBuffer(((MessageLite) msg).toByteArray());
                        }
                        if (msg instanceof MessageLite.Builder) {
                            result = wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray());
                        }
                        // 然后下面再转成 websocket 二进制流，因为客户端不能直接解析 protobuf 编码生成的
                        if (result != null) {
                            WebSocketFrame frame = new BinaryWebSocketFrame(result);
                            out.add(frame);
                        }
                    }
                });
                // 协议包解码时指定 Protobuf 字节数实例化为 CommonProtocol 类型
                pipeline.addLast(decoder);
                pipeline.addLast(new IdleStateHandler(Constants.ImServerConfig.READ_IDLE_TIME, Constants.ImServerConfig.WRITE_IDLE_TIME, 0));
                // 业务处理器
                pipeline.addLast(new ImWebSocketServerHandler(proxy, connector));
            }
        });

        // 可选参数
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        // 绑定接口，同步等待成功
        log.info("start im websocket server at port[" + port + "].");
        ChannelFuture future = bootstrap.bind(port).sync();
        channel = future.channel();
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    log.info("websocket server have success bind to " + port);
                } else {
                    log.error("websocket server fail bind to " + port);
                }
            }
        });
    }

    public void destroy() {
        log.info("destroy im websocket server ...");
        // 释放线程池资源
        if (channel != null) {
            channel.close();
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        log.info("destroy im websocket server complete.");
    }


    public void setPort(int port) {
        this.port = port;
    }

    public void setProxy(MessageProxy proxy) {
        this.proxy = proxy;
    }


    public void setConnector(ImConnector connector) {
        this.connector = connector;
    }


}
