package com.coderZsq;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI("rab")
public interface IRun {
    @Adaptive // 生成一个方法适配器
    public void run(URL url);
}
