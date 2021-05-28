package jvm;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class JvmAppClassLoaderAddURL {
    public static void main(String[] args) {
        String appPath = "file:/Users/zhushuangquan/Codes/GitHub/coderZsq.practice.server/study-notes/geekbang.org/Java进阶训练营 | 第01周 | JVM核心技术/src/jvm";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            URL url = new URL(appPath);
            addURL.invoke(urlClassLoader, url);
            Class.forName("jvm.Hello"); // 效果跟Class.forName("jvm.Hello").newInstance()一样
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
