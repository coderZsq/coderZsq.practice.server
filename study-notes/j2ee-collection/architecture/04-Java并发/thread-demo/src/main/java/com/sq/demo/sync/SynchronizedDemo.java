package com.sq.demo.sync;

import java.util.concurrent.TimeUnit;

public class SynchronizedDemo {
    /**
     * 同步锁: 锁定静态方法
     *
     * synchronized: 在jdk1.6 之前 性能很差 重量级锁
     * 类锁: SynchronizedDemo.class
     */
    public synchronized static void test2() {
        System.out.println("SynchronizedDemo.test2");
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo demo = new SynchronizedDemo();
        // 开启两个线程:
        new Thread() {
            @Override
            public void run() {
                demo.test1();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                demo.test3();
            }
        }.start();

        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 同步锁
     *
     * 对象锁: this: 监视器对象
     */
    public synchronized void test1() {
        System.out.println("SynchronizedDemo.test1");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 锁定同步代码块
     */
    public void test3() {
        // 业务逻辑1
        // 业务逻辑3
        // 同步代码块: this
        synchronized (this) {
            // 业务逻辑2
            System.out.println("SynchronizedDemo.test3");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    $ javap -c com/sq/demo/SynchronizedDemo
    Compiled from "SynchronizedDemo.java"
    public class com.sq.demo.sync.SynchronizedDemo {
      public com.sq.demo.sync.SynchronizedDemo();
        Code:
           0: aload_0
           1: invokespecial #1                  // Method java/lang/Object."<init>":()V
           4: return

      public synchronized void test1();
        Code:
           0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
           3: ldc           #13                 // String SynchronizedDemo.test1
           5: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
           8: getstatic     #21                 // Field java/util/concurrent/TimeUnit.SECONDS:Ljava/util/concurrent/TimeUnit;
          11: ldc2_w        #27                 // long 5l
          14: invokevirtual #29                 // Method java/util/concurrent/TimeUnit.sleep:(J)V
          17: goto          25
          20: astore_1
          21: aload_1
          22: invokevirtual #35                 // Method java/lang/InterruptedException.printStackTrace:()V
          25: return
        Exception table:
           from    to  target type
               8    17    20   Class java/lang/InterruptedException

      public static synchronized void test2();
        Code:
           0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
           3: ldc           #38                 // String SynchronizedDemo.test2
           5: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
           8: return

      public void test3();
        Code:
           0: aload_0
           1: dup
           2: astore_1
           3: monitorenter
           4: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
           7: ldc           #40                 // String SynchronizedDemo.test3
           9: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
          12: getstatic     #21                 // Field java/util/concurrent/TimeUnit.SECONDS:Ljava/util/concurrent/TimeUnit;
          15: ldc2_w        #27                 // long 5l
          18: invokevirtual #29                 // Method java/util/concurrent/TimeUnit.sleep:(J)V
          21: goto          29
          24: astore_2
          25: aload_2
          26: invokevirtual #35                 // Method java/lang/InterruptedException.printStackTrace:()V
          29: aload_1
          30: monitorexit
          31: goto          39
          34: astore_3
          35: aload_1
          36: monitorexit
          37: aload_3
          38: athrow
          39: return
        Exception table:
           from    to  target type
              12    21    24   Class java/lang/InterruptedException
               4    31    34   any
              34    37    34   any

      public static void main(java.lang.String[]) throws java.lang.InterruptedException;
        Code:
           0: new           #42                 // class com/sq/demo/SynchronizedDemo
           3: dup
           4: invokespecial #44                 // Method "<init>":()V
           7: astore_1
           8: new           #45                 // class com/sq/demo/SynchronizedDemo$1
          11: dup
          12: aload_1
          13: invokespecial #47                 // Method com/sq/demo/SynchronizedDemo$1."<init>":(Lcom/sq/demo/SynchronizedDemo;)V
          16: invokevirtual #50                 // Method com/sq/demo/SynchronizedDemo$1.start:()V
          19: new           #53                 // class com/sq/demo/SynchronizedDemo$2
          22: dup
          23: aload_1
          24: invokespecial #55                 // Method com/sq/demo/SynchronizedDemo$2."<init>":(Lcom/sq/demo/SynchronizedDemo;)V
          27: invokevirtual #56                 // Method com/sq/demo/SynchronizedDemo$2.start:()V
          30: getstatic     #21                 // Field java/util/concurrent/TimeUnit.SECONDS:Ljava/util/concurrent/TimeUnit;
          33: ldc2_w        #57                 // long 10l
          36: invokevirtual #29                 // Method java/util/concurrent/TimeUnit.sleep:(J)V
          39: return
    }

     */
}
