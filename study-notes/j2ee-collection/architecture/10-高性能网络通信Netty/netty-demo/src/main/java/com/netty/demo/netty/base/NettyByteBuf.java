package cn.wolfcode.netty.demo.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author Leon
 */
public class NettyByteBuf {

    public static void main(String[] args) {
        // 创建 byteBuf 对象，该对象内部包含一个字节数组 byte[10]
        // 通过 readerIndex 和 writerIndex 和 capacity，将 buffer 分成三个区域
        // 已经读取的区域：[0, readerIndex)
        // 可读取的区域：[readerIndex, writerIndex)
        // 可写的区域: [writerIndex, capacity)
        ByteBuf byteBuf = Unpooled.buffer(1);
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 8; i++) {
            byteBuf.writeByte(i);
        }
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuf.getByte(i));
        }
        System.out.println("byteBuf=" + byteBuf);

        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuf.readByte());
        }
        System.out.println("byteBuf=" + byteBuf);


        // 用 Unpooled 工具类创建 ByteBuf
        ByteBuf byteBuf2 = Unpooled.copiedBuffer("hello, byteBuf!", CharsetUtil.UTF_8);

        // 使用相关的方法
        if (byteBuf2.hasArray()) {
            // 数据内容
            byte[] content = byteBuf2.array();

            //将 content 转成字符串
            System.out.println(new String(content, CharsetUtil.UTF_8));
            System.out.println("byteBuf2=" + byteBuf2);

            // 获取数组0这个位置的字符h的ascii码，h=104
            System.out.println(byteBuf2.getByte(0));

            // 可读的字节数 12
            int len = byteBuf2.readableBytes();
            System.out.println("len=" + len);

            // 使用 for 取出各个字节
            for (int i = 0; i < len; i++) {
                System.out.println((char) byteBuf2.getByte(i));
            }

            // 范围读取
            System.out.println(byteBuf2.getCharSequence(0, 6, CharsetUtil.UTF_8));
            System.out.println(byteBuf2.getCharSequence(6, 6, CharsetUtil.UTF_8));
        }
    }
}
