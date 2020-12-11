package com.sq.demo;

import java.util.concurrent.ConcurrentHashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        // HashMap 不是线程安全的
        // 方式一: ConcurrentHashMap
        final ConcurrentHashMap<Long, String> hashMap = new ConcurrentHashMap<>();
        // 方式二: HashTable
        // 方式三: Collections.synchronizedMap
        hashMap.put(1L, "sq");
    }
}
