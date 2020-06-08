package com.coderZsq;

import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * 适配器类的单元测试
 */
public class App4 {
    public static void main(String[] args) {
        // 返回一个加载器
        ExtensionLoader<IPay> loader = ExtensionLoader.getExtensionLoader(IPay.class);
        // 获取对应的适配器对象
        AdaptivePay.setRule_name("ali");
        IPay pay = loader.getAdaptiveExtension();
        pay.pay("coderZsq", 300);
    }
}
