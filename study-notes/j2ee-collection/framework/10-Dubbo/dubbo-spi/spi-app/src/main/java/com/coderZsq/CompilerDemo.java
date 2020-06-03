package com.coderZsq;

import org.apache.dubbo.common.compiler.Compiler;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.lang.reflect.Method;

public class CompilerDemo {
    public static void main(String[] args) throws Exception {
        // 演示一下动态编译
        String code = "package com.coderZsq;\n" +
                "\n" +
                "public class HelloDemo {\n" +
                "    public String say() {\n" +
                "        System.out.println(\"调用方法\");\n" +
                "        return \"abc\";\n" +
                "    }\n" +
                "}";
        Compiler compiler = ExtensionLoader.getExtensionLoader(Compiler.class).getDefaultExtension();
        Class<?> clz = compiler.compile(code, Thread.currentThread().getContextClassLoader());
        // 通过反射创建实例对象
        Object obj = clz.newInstance();
        // 找到反射的方法
        Method say = clz.getMethod("say");
        // 调用方法
        Object result = say.invoke(obj);
        System.out.println("result = " + result);
    }
}
