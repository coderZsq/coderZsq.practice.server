package com.coderZsq._01_review;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

// 主板
public class MotherBoard {

    // 存储安装的USB设备对象
    private static Map<String, IUSB> plugins = new HashMap<>();

    static {
        Properties p = new Properties();
        try {
            // 从classpath的根路径去加载plugins.properties文件
            InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("plugins.properties");
            p.load(inStream);
            // ---------------------
            // 读取Properties文件, 创建USB组件对象
            Set<Map.Entry<Object, Object>> entries = p.entrySet();
            // mouse=com.coderZsq._01_review.Mouse
            for (Map.Entry<Object, Object> entry : entries) {
                String className = entry.getValue().toString();
                IUSB usbObject = (IUSB) Class.forName(className).newInstance();
                plugins.put(entry.getKey().toString(), usbObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 主板通信
    public void doWork() {
        // 迭代出每一个安装在主板上的组件, 并调用其功能
        for (IUSB usb : plugins.values()) {
            usb.swapData();
        }
    }
}
