package cn.wolfcode.netty.im.util;

import io.netty.channel.ChannelHandlerContext;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author Leon
 */
public class ImUtils {

    /**
     * byte数组转换成16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 获取IP地址及端口
     *
     * @param socketAddress
     * @return {ip}:{prot}  字符串
     */
    public static String getIpAndProt(InetSocketAddress socketAddress) {
        String address = "";
        address = getIp(socketAddress) + ":" + socketAddress.getPort();
        return address;
    }

    /**
     * 获取IP地址
     *
     * @param socketAddress
     * @return {ip} 字符串
     */
    public static String getIp(InetSocketAddress socketAddress) {
        String ip = "";
        if (socketAddress != null) {
            InetAddress address = socketAddress.getAddress();
            ip = (address == null) ? socketAddress.getHostName() : address.getHostAddress();
        }
        return ip;
    }


    public static String getRemoteAddress(ChannelHandlerContext ctx) {
        InetSocketAddress remote = (InetSocketAddress) ctx.channel().remoteAddress();
        return getIpAndProt(remote);
    }

    public static String getLocalAddress(ChannelHandlerContext ctx) {
        InetSocketAddress local = (InetSocketAddress) ctx.channel().localAddress();
        return getIpAndProt(local);
    }


}
