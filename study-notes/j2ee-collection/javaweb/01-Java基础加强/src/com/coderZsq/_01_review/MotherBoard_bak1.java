package com.coderZsq._01_review;

import java.util.HashMap;
import java.util.Map;

// 主板
public class MotherBoard_bak1 {

    // 存储安装的USB设备对象
    private Map<String, IUSB> plugins = new HashMap<>();

    // 把鼠标安装在主板上
    public void install(String name, IUSB usb) {
        plugins.put(name, usb);
    }

    // 从主板上卸载掉指定名词的组件
    public void uninstall(String name) {
        plugins.remove(name);
    }

    // 主板通信
    public void doWork() {
        // 迭代出每一个安装在主板上的组件, 并调用其功能
        for (IUSB usb : plugins.values()) {
            usb.swapData();
        }
    }
}
