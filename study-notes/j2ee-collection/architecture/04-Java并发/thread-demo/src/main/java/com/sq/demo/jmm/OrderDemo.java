package com.sq.demo.jmm;

/**
 * 有序性
 * volatile: 有序性, 可见性
 */
public class OrderDemo {
    private volatile static int x = 0, y = 0;
    private volatile static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1; // 指令重排序 --> 不能影响原来的执行逻辑结果 提升处理运算性能
                    x = b; // ====> 先执行 x = b; 再去执行 a = 1
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            // 第一句执行的代码: a = 1, b = 1   x,y中至少有一个值为1, 不可能都为0
            t1.start(); // 启动t1线程
            t2.start(); // 启动t2线程
            t1.join(); // 等待t1线程执行完成
            t2.join(); // 等待t2线程执行完成
            String result = "第" + i+ "次: (" + x + ", " + y + ")";
            // x, y 的结果 ---> t1 先执行 (0, 1)
            // x, y 的结果 ---> t2 先执行 (1, 0)
            // t1 --> 第一条 t2 ---> 第一条 (1, 1)
            // (0, 0)
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
