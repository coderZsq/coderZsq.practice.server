package com.coderZsq;

import com.coderZsq.file.FileInfo;
import com.coderZsq.file.Files;
import com.coderZsq.map.Map;
import com.coderZsq.map.TreeMap;
import com.coderZsq.set.Set;
import com.coderZsq.set.TreeSet;

public class Main {

    static void test1() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("c", 2);
        map.put("a", 5);
        map.put("b", 6);
        map.put("a", 8);

        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test2() {
        FileInfo fileInfo = Files.read("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/algorithm/10-映射/java/util", new String[]{"java"});
        System.out.println("文件数量" + fileInfo.getFiles());
        System.out.println("代码行数" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量" + words.length);
        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < words.length; i++) {
            Integer count = map.get(words[i]);
            count = (count == null) ? 1 : (count + 1);
            map.put(words[i], count);
        }
        System.out.println(map.size());
        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test3() {
        Set<String> set = new TreeSet<>();
        set.add("a");
        set.add("c");
        set.add("b");
        set.add("c");
        set.add("c");

        set.traversal(new Set.Visitor<String>() {
            @Override
            public boolean visit(String element) {
                System.out.println(element);
                return false;
            }
        });
    }

    public static void main(String[] args) {
	    test3();
    }
}
