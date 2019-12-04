package com.coderZsq;

public class Main {

    public static void main(String[] args) {
        test1();
        // 数组
        String[] urls = {};

        BloomFilter<String> bf = new BloomFilter<String>(10_0000_0000, 0.01);
        /*
            for (String url : urls) {
                if (bf.contains(url)) continue;
                // 爬这个url
                //......

                // 放进BloomFilter中
                bf.put(url);
            }
         */

        for (String url : urls) {
            if (bf.put(url) == false) continue;
            // 爬这个url
            // ......
        }
    }

    static void test1() {
        BloomFilter<Integer> bf = new BloomFilter<>(1_0000_0000, 0.01);
        for (int i = 1; i <= 1_0000_0000; i++) {
            bf.put(i);
        }
        int count = 0;
        for (int i = 1_0000_0001; i <= 2_0000_0000; i++) {
            if (bf.contains(i)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
