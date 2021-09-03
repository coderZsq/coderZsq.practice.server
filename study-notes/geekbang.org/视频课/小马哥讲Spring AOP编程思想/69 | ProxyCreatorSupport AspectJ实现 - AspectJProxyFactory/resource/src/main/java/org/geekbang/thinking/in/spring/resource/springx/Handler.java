package org.geekbang.thinking.in.spring.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 简单地继承 {@link sun.net.www.protocol.x.Handler} 类
 */
public class Handler extends sun.net.www.protocol.x.Handler {

    // -Djava.protocol.handler.pkgs=org.geekbang.thinking.in.spring.resource
    public static void main(String[] args) throws IOException {
        // springx 协议
        URL url = new URL("springx:///META-INF/production.properties"); // 类似于 classpath:/META-INF/default.properties
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
