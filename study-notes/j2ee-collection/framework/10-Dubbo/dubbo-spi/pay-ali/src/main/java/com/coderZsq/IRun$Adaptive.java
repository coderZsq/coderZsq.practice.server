package com.coderZsq;

import org.apache.dubbo.common.extension.ExtensionLoader;

public class IRun$Adaptive implements com.coderZsq.IRun {
    @Override
    public void run(org.apache.dubbo.common.URL arg0) {
        // 获取到参数
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        org.apache.dubbo.common.URL url = arg0;
        // 从参数中获取i.run --> rab
        String extName = url.getParameter("i.run", "rab");
        if (extName == null)
            throw new IllegalStateException("Failed to get extension (com.coderZsq.IRun) name from url (" + url.toString() + ") use keys([i.run])");
        // 生成一个具体实现的对象
        com.coderZsq.IRun extension = (com.coderZsq.IRun) ExtensionLoader.getExtensionLoader(com.coderZsq.IRun.class).getExtension(extName);
        // 调用具体的实际的业务方法
        extension.run(arg0);
    }
}