package com.coderZsq;

public class Main {

    public static void main(String[] args) {
        /*
         * 进程(Process)
         *
         * 什么是进程?
         * 在操作系统中运行的一个应用程序
         *
         * 比如同时打开QQ、微信，操作系统就会分别启动2个进程
         *
         * 每个进程之间是独立的，每个进程均运行在其专用且受保护的内存空间内
         * 在 Windows 中，可以通过“任务管理器”查看正在运行的进程
         * */

        /*
         * 线程(Thread)
         *
         * 什么是线程?
         * 1个进程要想执行任务，必须得有线程(每 1 个进程至少要有 1 个线程)
         * 一个进程的所有任务都在线程中执行
         *
         * 比如使用酷狗播放音乐、使用迅雷下载文件，都需要在线程中执行
         * */

        /*
         * 线程的串行
         *
         * 1个线程中任务的执行是串行的
         * 如果要在 1 个线程中执行多个任务，那么只能一个一个地按顺序执行这些任务
         * 在同一时间内，1 个线程只能执行 1 个任务
         *
         * 比如在 1 个线程中下载 3 个文件(分别是文件 A、文件 B、文件 C)
         * */

        /*
         * 多线程
         *
         * 什么是多线程
         * 1个进程中可以开启多个线程，所有线程可以并行(同时)执行不同的任务
         * 进程 → 车间，线程 → 车间工人
         * 多线程技术可以提高程序的执行效率
         *
         * 比如同时开启 3 个线程分别下载 3 个文件(分别是文件 A、文件 B、文件 C)
         * */

        /*
         * 多线程的原理
         *
         * 同一时间，CPU 的 1 个核心只能处理 1 个线程(只有 1 个线程在工作)
         * 多线程并发(同时)执行，其实是 CPU 快速地在多个线程之间调度(切换)
         *
         * 如果 CPU 调度线程的速度足够快，就造成了多线程并发执行的假象
         * 如果是多核 CPU，才是真正地实现了多个线程同时执行
         * 思考:如果线程非常非常多，会发生什么情况?
         * CPU 会在 N 个线程之间调度，消耗大量的 CPU 资源，CPU 会累死
         * 每条线程被调度执行的频次会降低(线程的执行效率降低)
         * */

        /*
         * 多线程的优缺点
         *
         * 优点
         * 能适当提高程序的执行效率
         * 能适当提高资源利用率(CPU、内存利用率)
         *
         * 缺点
         * 开启线程需要占用一定的内存空间，如果开启大量的线程，会占用大量的内存空间，降低程序的性能
         * 线程越多，CPU 在调度线程上的开销就越大
         * 程序设计更加复杂
         * 比如线程之间的通信问题、多线程的数据共享问题
         * */

        /*
         * 默认线程
         *
         * 每一个 Java 程序启动后，会默认开启一个线程，称为主线程(main 方法所在的线程)
         * 每一个线程都是一个 java.lang.Thread 对象，可以通过 Thread.currentThread 方法获取当前的线程对象
         * */
        {
            // Thread[main,5,main]
            System.out.println(Thread.currentThread());
        }

        /*
         * 开启新线程的第 1 种方法
         * */
        {
            // 传入一个Runnable实例, 在run方法中编写子线程需要执行的任务
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("开启了新线程: " + Thread.currentThread());
                }
            });
            thread.start();
        }

        {
            Thread thread = new Thread(() -> {
                System.out.println("开启了新线程: " + Thread.currentThread());
            });
            thread.start();
        }

        /*
         * 开启新线程的第 2 种方法
         *
         * 注意
         * 直接调用线程的 run 方法并不能开启新线程
         * 调用线程的 start 方法才能成功开启新线程
         *
         * Thread 类实现了 Runnable 接口
         * */
        {
            MyThread thread = new MyThread();
            thread.start();
        }

        /*
         * 多线程的内存布局
         *
         * PC 寄存器(Program Counter Register):每一个线程都有自己的 PC 寄存器
         *
         * Java 虚拟机栈(Java Virtual Machine Stack):每一个线程都有自己的 Java 虚拟机栈
         *
         * 堆(Heap):多个线程共享堆
         *
         * 方法区(Method Area):多个线程共享方法区
         *
         * 本地方法栈(Native Method Stack):每一个线程都有自己的本地方法栈
         * */

        /*
         * 线程的状态
         *
         * 可以通过 Thread.getState 方法获得线程的状态(线程一共有 6 种状态)
         *
         * NEW (新建):尚未启动
         *
         * RUNNABLE (可运行状态):正在 JVM 中运行
         * 或者正在等待操作系统的其他资源(比如处理器)
         *
         * BLOCK (阻塞状态):正在等待监视器锁(内部锁)
         *
         * WAITING(等待状态):在等待另一个线程
         * 调用以下方法会处于等待状态
         * 没有超时值的 Object.wait
         * 没有超时值的 Thread.join
         * LockSupport.park
         *
         * TIMED_WAITING (定时等待状态)
         * 调用以下方法会处于定时等待状态
         * Thread.sleep
         * 有超时值的 Object.wait
         * 有超时值的 Thread.join
         * LockSupport.parkNanos
         * LockSupport.parkUntil
         *
         * TERMINATED (终止状态):已经执行完毕
         * */

        /*
         * sleep、interrupt
         *
         * 可以通过 Thread.sleep 方法暂停当前线程，进入WAITING状态
         * 在暂停期间，若调用线程对象的 interrupt 方法中断线程，会抛出 java.lang.InterruptedException 异常
         * */
        {
            
        }
    }
}
