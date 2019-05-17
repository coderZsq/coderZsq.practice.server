package com.coderZsq;

import java.util.HashMap;
import java.util.Map;

public class Main {

    static void test1() {
        String string = "jack";
        int len = string.length();
        int hashCode = 0;
        for (int i = 0; i < len; i++) {
            char c = string.charAt(i);
//            hashCode = (hashCode << 5) - hashCode + c;
            hashCode = hashCode * 31 + c;
        }
        System.out.println(hashCode);
        System.out.println(string.hashCode());
    }

    static void test2() {
        Integer a = 100;
        Float b = 10.6f;
        Long c = 156L;
        Double d = 10.9;
        String e = "rose";

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());
        System.out.println(e.hashCode());
    }

    public static void main(String[] args) {
        Person p1 = new Person(10, 1.67f, "jack");
        Person p2 = new Person(10, 1.67f, "jack");
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

        Map<Object, Object> map = new HashMap<>();
        map.put(p1, "abc");
        map.put("test", "ccc");
        map.put(p2, "bcd");
        System.out.println(map.size());
    }
}
