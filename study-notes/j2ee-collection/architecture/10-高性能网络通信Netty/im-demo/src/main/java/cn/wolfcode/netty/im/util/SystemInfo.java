package cn.wolfcode.netty.im.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * 获取当前系统信息
 *
 * @author Leon
 */
public class SystemInfo {
    /**
     * 当前实例
     */
    private static SystemInfo currentSystem = null;
    private InetAddress localHost = null;

    private SystemInfo() {
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static class SystemInfoInstanceHolder {
        private static SystemInfo instance = new SystemInfo();
    }

    /**
     * 单例模式获取对象
     *
     * @return
     */
    public static SystemInfo getInstance() {
        return SystemInfoInstanceHolder.instance;
    }

    /**
     * 本地IP
     *
     * @return IP地址
     */
    public String getIP() {
        return localHost.getHostAddress();
    }

    /**
     * 获取用户机器名称
     *
     * @return
     */
    public String getHostName() {
        return localHost.getHostName();
    }

    /**
     * 获取Mac地址
     *
     * @return Mac地址，例如：F0-4D-A2-39-24-A6
     */
    public String getMac() {
        NetworkInterface byINetAddress;
        try {
            byINetAddress = NetworkInterface.getByInetAddress(localHost);
            byte[] hardwareAddress = byINetAddress.getHardwareAddress();
            return getMacFromBytes(hardwareAddress);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前系统名称
     *
     * @return 当前系统名，例如： windows xp
     */
    public String getSystemName() {
        Properties sysProperty = System.getProperties();
        // 系统名称
        return sysProperty.getProperty("os.name");
    }

    /**
     * 获取当前系统名称
     *
     * @return 当前系统名，例如：windows xp
     */
    public String getSystem() {
        Properties sysProperty = System.getProperties();
        // 系统名称
        String systemName = sysProperty.getProperty("sun.desktop");
        return systemName;
    }


    private String getMacFromBytes(byte[] bytes) {
        StringBuilder mac = new StringBuilder(bytes.length);
        byte currentByte;
        boolean first = false;
        for (byte b : bytes) {
            if (first) {
                mac.append("-");
            }
            currentByte = (byte) ((b & 240) >> 4);
            mac.append(Integer.toHexString(currentByte));
            currentByte = (byte) (b & 15);
            mac.append(Integer.toHexString(currentByte));
            first = true;
        }
        return mac.toString().toUpperCase();
    }
}
