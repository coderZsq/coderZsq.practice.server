package com.coderZsq._23_i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // ResourceBundleDemo可以读取资源文件, 获取其中的信息
        // app_zh_CN.properties
        ResourceBundle rb = ResourceBundle.getBundle("app", Locale.CHINA);
        String username = rb.getString("username");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        String company = rb.getString("company");
        company = new String(company.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(username + "," + company);
    }
}
