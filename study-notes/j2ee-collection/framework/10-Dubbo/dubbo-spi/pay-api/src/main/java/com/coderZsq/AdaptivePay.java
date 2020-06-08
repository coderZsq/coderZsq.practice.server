package com.coderZsq;

import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.ExtensionLoader;

@Adaptive
public class AdaptivePay implements IPay {
    private static String rule_name = null; // ali, wx

    public static void setRule_name(String rule_name) {
        AdaptivePay.rule_name = rule_name;
    }

    @Override
    public void pay(String name, Integer amount) {
        // 1 根据适配的规则, 找到具体的实现
        ExtensionLoader<IPay> loader = ExtensionLoader.getExtensionLoader(IPay.class);
        IPay pay = null;
        if (rule_name != null && rule_name.length() > 0) {
            pay = loader.getExtension(rule_name);
        } else {
            pay = loader.getDefaultExtension();
        }
        // 2 调用具体的实现业务操作
        pay.pay(name, amount);
    }
}
