package com.sq.demo.sync;

import org.openjdk.jol.info.ClassLayout;

import java.nio.ByteOrder;

public class Demo2 {
    public static void main(String[] args) {
        // 锁的状态只会升级不会降级
        // VM options: -XX:BiasedLockingStartupDelay=10 设置偏向锁开启的延时时间

        Object obj = new Object();
        synchronized (obj) {
            System.out.println("=====================");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
            System.out.println("=====================");
        }
        // 单一线程, 轻量级锁 000
        // new Thread() {
        //     @Override
        //     public void run() {
        //         synchronized (obj) {
        //             System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        //             /*
        //             java.lang.Object object internals:
        //              OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        //                   0     4        (object header)                           d0 ca a8 0f (11010000 11001010 10101000 00001111) (262720208)
        //                   4     4        (object header)                           00 70 00 00 (00000000 01110000 00000000 00000000) (28672)
        //                   8     4        (object header)                           86 06 00 00 (10000110 00000110 00000000 00000000) (1670)
        //                  12     4        (loss due to the next object alignment)
        //             Instance size: 16 bytes
        //             Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
        //
        //              */
        //             System.out.println("轻量级锁=====================");
        //         }
        //     }
        // }.start();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    synchronized (obj) {
                        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                        /*
                        重量级锁: 010
                        java.lang.Object object internals:
                         OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
                              0     4        (object header)                           02 7a 9e ac (00000010 01111010 10011110 10101100) (-1398900222)
                              4     4        (object header)                           8e 7f 00 00 (10001110 01111111 00000000 00000000) (32654)
                              8     4        (object header)                           86 06 00 00 (10000110 00000110 00000000 00000000) (1670)
                             12     4        (loss due to the next object alignment)
                        Instance size: 16 bytes
                        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
                        */
                        System.out.println("重量级锁=====================");
                    }
                }
            }.start();
        }
        System.out.println(ByteOrder.nativeOrder()); // LITTLE_ENDIAN 小端模式
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        /*
        -XX:BiasedLockingStartupDelay=10 无锁 001
        java.lang.Object object internals:
          OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
               0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
               4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
               8     4        (object header)                           86 06 00 00 (10000110 00000110 00000000 00000000) (1670)
              12     4        (loss due to the next object alignment)
         Instance size: 16 bytes
         Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

        -XX:BiasedLockingStartupDelay=0 偏向锁 101
         java.lang.Object object internals:
          OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
               0     4        (object header)                           05 e0 00 f0 (00000101 11100000 00000000 11110000) (-268378107)
               4     4        (object header)                           f8 7f 00 00 (11111000 01111111 00000000 00000000) (32760)
               8     4        (object header)                           86 06 00 00 (10000110 00000110 00000000 00000000) (1670)
              12     4        (loss due to the next object alignment)
         Instance size: 16 bytes
         Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */
    }
}
