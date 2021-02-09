package com.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Leon
 * @date 2020/12/27
 */
public class OOMTest {

    public static List<Object> list = new ArrayList<>();

    // JVM设置
    // -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./jvm.dump
    public static void main(String[] args) {
        int i = 0;
        while (true) {
            list.add(new User(i++, UUID.randomUUID().toString()));
        }
    }
}
