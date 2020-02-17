package com.coderZsq.shopping.test;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class StringUtilsDemo {
    public static void main(String[] args) {
        System.out.println(StringUtils.isNotBlank(null)); // false
        System.out.println(StringUtils.isNotEmpty(null)); // false

        System.out.println(StringUtils.isNotBlank("")); // false
        System.out.println(StringUtils.isNotEmpty("")); // false

        // isNotBlank: 会消除字符串前后空格再判断
        System.out.println(StringUtils.isNotBlank(" ")); // false
        System.out.println(StringUtils.isNotEmpty(" ")); // true

        // ======================================
        List<String> list = Arrays.asList("1", "2", "3", "4");
        String str = StringUtils.join(list, " AND ");
        System.out.println(str);
    }
}
