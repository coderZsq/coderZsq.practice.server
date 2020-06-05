package com.coderZsq;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

public class App3 {
    public static void main(String[] args) {
        // 1 类加载器 加载对应的操作
        ExtensionLoader<IRun> loader = ExtensionLoader.getExtensionLoader(IRun.class);
        // 2 获取到适配器
        /*
         * 步骤: 1 在方法上面需要有一个注解 @Adaptive
         *      2 对于方法来说, 必须接受一个参数URL或者是有url getter属性的参数
         */
        IRun adaptiveExtension = loader.getAdaptiveExtension();
        URL url = new URL("dubbo", "127.0.0.1", 20880);
        url.addParameter("i.run", "rab");
        adaptiveExtension.run(url); // IRun$Adaptive 的方法 run
    }
}
