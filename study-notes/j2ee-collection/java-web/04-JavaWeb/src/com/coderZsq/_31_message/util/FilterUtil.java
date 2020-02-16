package com.coderZsq._31_message.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 敏感字过滤
 */
public class FilterUtil {
    private static List<String> stopWords = new ArrayList<>();
    static {
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("stopwords.txt"), "UTF-8");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (CommUtil.hasLength(line)) {
                stopWords.add(line);
            }
        }
        sc.close();
    }

    /**
     * 执行过滤的方法
     * @param msg
     * @return
     */
    public static String filter(String msg) {
        for (String s : stopWords) {
            if (msg.indexOf(s) >= 0) {
                msg = msg.replaceAll(s, buildMask(s, "*"));
            }
        }
        return msg;
    }

    private static String buildMask(String s, String mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(mask);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(filter("我去年买了个表还有AV, 哈哈哈"));
    }
}
