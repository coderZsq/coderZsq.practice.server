package com.coderZsq;

import org.apache.dubbo.common.extension.ExtensionLoader;

public class App2 {
    public static void main(String[] args) {
        // 1 类加载器 加载对应的操作
        ExtensionLoader<IPay> loader = ExtensionLoader.getExtensionLoader(IPay.class);
        // 2 获取实际对象
        IPay wx = loader.getDefaultExtension();
        wx.pay("009", 200);
    }
}
