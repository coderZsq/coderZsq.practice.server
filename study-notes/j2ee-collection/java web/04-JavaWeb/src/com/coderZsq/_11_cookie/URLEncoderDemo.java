package com.coderZsq._11_cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncoderDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String msg = "西门吹雪";
        // 编码
        String ret = URLEncoder.encode(msg, "UTF-8");
        System.out.println(ret);
        // 解码
        String str = URLDecoder.decode(ret, "UTF-8");
        System.out.println(str);
    }
}
