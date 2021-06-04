# 1.多线程基础

## 为什么会有多线程

本质原因是摩尔定律失效 -> 多核+分布式 时代的来临。

JVM、NIO 是不是都因为这个问题变复杂?

后面讲的分布式系统，也是这个原因。

---

多 CPU 核心意味着同时操作系统有更多的并行 计算资源可以使用。
操作系统以线程作为基本的调度单元。

单线程是最好处理不过的。
线程越多，管理复杂度越高。

跟我们程序员都喜欢自己单干一样。
《人月神话》里说加人可能干得更慢。

可见多核时代的编程更有挑战。

## Java 线程的创建过程

线程与进程的区别是什么?

# 2. Java 多线程\*

## Thread 使用示例

```java
public static void main(String[] args) {
    Runnable task = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread t = Thread.currentThread();
            System.out.println("当前线程:" + t.getName());
        }
    };
    Thread thread = new Thread(task);
    thread.setName("test-thread-1");
    thread.setDaemon(true);
    thread.start();
}
```

- 守护线程
- 启动方式

思考:

1. 输出结果是什么?
2. 为什么?
3. 有哪些方式可以修改?

## 基础接口 - Runnable

```java
// 接口定义
public interface Runnable {
  public abstract void run();
}

// 重要实现
Thread implements Runnable ...

// 示例1
Runnable task = new Runnable() {
  @Override
  public void run() {
    System.out.println("业务逻辑...");
  }
};


// 示例2
public class XXXTask implements Runnable {
  @Override
  public void run() {
    System.out.println("执行逻辑...");
  }
}
```

辨析:
Thread#start():创建新线程
Thread#run() : 本线程调用

## 线程状态

## Thread 类

|             重要属性/方法              |                 说明                 |
| :------------------------------------: | :----------------------------------: |
|         volatile String name;          |       线程名称 - 诊断分析使用        |
|        boolean daemon = false;         | 后台守护线程标志 - 决定 JVM 优雅关闭 |
|            Runnable target;            |      任务(只能通过构造函数传入)      |
|       synchronized void start()        |      [协作]启动新线程并自动执行      |
|              void join()               |  [协作]等待某个线程执行完毕(来汇合)  |
| static native Thread currentThread();  |      静态方法: 获取当前线程信息      |
| static native void sleep(long millis); | 静态方法: 线程睡眠并让出 CPU 时间片  |

Thread : 线程

## wait & notify

|            Object#方法             |                              说明                              |
| :--------------------------------: | :------------------------------------------------------------: |
|            void wait()             |                  放弃锁+等待 0ms+尝试获取锁;                   |
| void wait(long timeout, int nanos) | 放弃锁+wait+到时间自动唤醒/中途唤醒(精度:nanos>0 则 timeout++) |
|  native void wait(long timeout);   | 放弃锁+wait+到时间自动唤醒/中途被唤醒(唤醒之后需要自动获取锁)  |
|       native void notify();        |                   发送信号通知 1 个等待线程                    |
|      native void notifyAll();      |                    发送信号通知所有等待线程                    |

辨析:

- Thread.sleep: 释放 CPU
- Object#wait : 释放锁

## Thread 的状态改变操作\*

1. Thread.sleep(long millis)，一定是当前线程调用此方法，当前线程进入 TIMED_WAITING 状态，但不释放对象锁，
   millis 后线程自动苏醒进入就绪状态。作用:给其它线程执行机会的最佳方式。
2. Thread.yield()，一定是当前线程调用此方法，当前线程放弃获取的 CPU 时间片，但不释放锁资源，由运行状态变为就 绪状态，让 OS 再次选择线程。作用:让相同优先级的线程轮流执行，但并不保证一定会轮流执行。实际中无法保证 yield() 达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。Thread.yield() 不会导致阻塞。该方法与 sleep() 类似，只是不能由用户指定暂停多长时间。
3. t.join()/t.join(long millis)，当前线程里调用其它线程 t 的 join 方法，当前线程进入 WAITING/TIMED_WAITING 状态， 当前线程不会释放已经持有的对象锁。线程 t 执行完毕或者 millis 时间到，当前线程进入就绪状态。
4. obj.wait()，当前线程调用对象的 wait() 方法，当前线程释放对象锁，进入等待队列。依靠 notify()/notifyAll() 唤醒或 者 wait(long timeout) timeout 时间到自动唤醒。
5. obj.notify() 唤醒在此对象监视器上等待的单个线程，选择是任意性的。notifyAll() 唤醒在此对象监视器上等待的所有线 程。

## Thread 的中断与异常处理

1. 线程内部自己处理异常，不溢出到外层。
2. 如果线程被 Object.wait, Thread.join 和 Thread.sleep 三种方法之一阻塞，此时调用该线程 的 interrupt() 方法，那么该线程将抛出一个 InterruptedException 中断异常(该线程必须事 先预备好处理此异常)，从而提早地终结被阻塞状态。如果线程没有被阻塞，这时调用 interrupt() 将不起作用，直到执行到 wait(),sleep(),join() 时,才马上会抛出 InterruptedException。
3. 如果是计算密集型的操作怎么办? 分段处理，每个片段检查一下状态，是不是要终止。
