package com.sq.aqs;

import sun.misc.Unsafe;

/**
 * 原子性: 在执行的操作中的过程, 不会被中断
 */
public class Demo04 {
    private int count = 0;
    private static Unsafe unsafe = UnsafeUtil.getInstance();
    private static long countOffset; // count 在内存中的一个物理地址
    static {
        try {
            countOffset = unsafe.objectFieldOffset(Demo04.class.getDeclaredField("count"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Demo04 demo04 = new Demo04();

        // 创建两个线程, 每个线程加100000次
        final Thread t1 = new Thread(() -> {
            for (int j = 0; j < 100000; j++) {
                for (;;) {
                    int expect = demo04.count; // 期望值, 内存当中的一个值
                    int update = expect + 1; // 修改之后的一个值
                    boolean flag = unsafe.compareAndSwapInt(demo04, countOffset, expect, update); // 如果替换成功, 返回true, 否则返回false
                    if (flag) { // 操作成功, 如果操作不成功, 继续进行值的获取, 和比较
                        break;
                    }
                }
            }
        });
        final Thread t2 = new Thread(() -> {
            for (int j = 0; j < 100000; j++) {
                for (;;) {
                    int expect = demo04.count; // 期望值, 内存当中的一个值
                    int update = expect + 1; // 修改之后的一个值
                    boolean flag = unsafe.compareAndSwapInt(demo04, countOffset, expect, update); // 如果替换成功, 返回true, 否则返回false
                    if (flag) { // 操作成功, 如果操作不成功, 继续进行值的获取, 和比较
                        break;
                    }
                }
            }
        });
        t1.start();
        t2.start();
        // 需要等待t1, t2线程执行完毕
        t1.join(); // 合并线程
        t2.join(); // 合并线程
        System.out.println("count = " + demo04.count);

        System.out.println(UnsafeUtil.getInstance());
    }
}
