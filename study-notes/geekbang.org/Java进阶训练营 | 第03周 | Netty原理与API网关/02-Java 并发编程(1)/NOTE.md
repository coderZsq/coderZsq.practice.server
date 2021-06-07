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

## Thread 状态

1、本线程主动操作 2、被动:

- 遇到锁
- 被通知

# 3.线程安全\*

## 多线程执行会遇到什么问题?

多个线程竞争同一资源时，如果对资源的访问顺序敏感，就称存在竞态条件。
导致竞态条件发生的代码区称作临界区。
不进行恰当的控制，会导致线程安全问题

## 并发相关的性质

原子性:原子操作，注意跟事务 ACID 里原子性的区别与联系 对基本数据类型的变量的读取和赋值操作是原子性操作，即这些操作是不可被中断的，
要么执行，要么不执行。

只有语句 1 是原子操作。

```java
x = 10; // 语句1
y = x; // 语句2
x++; // 语句3
x = x + 1; // 语句4
```

多个线程并发问题
类似于
多个事务的并发问题

---

可见性:对于可见性，Java 提供了 volatile 关键字来保证可见性。
当一个共享变量被 volatile 修饰时，它会保证修改的值会立即被更新到主存，当有其他
线程需要读取时，它会去内存中读取新值。
另外，通过 synchronized 和 Lock 也能够保证可见性，synchronized 和 Lock 能保证 同一时刻只有一个线程获取锁然后执行同步代码，并且在释放锁之前会将对变量的修改 刷新到主存当中。
volatile 并不能保证原子性。

---

有序性:Java 允许编译器和处理器对指令进行重排序，但是重排序过程不会影响到单线程程序的执行，却会影 响到多线程并发执行的正确性。可以通过 volatile 关键字来保证一定的“有序性”(synchronized 和 Lock 也可以)。
happens-before 原则(先行发生原则):

1. 程序次序规则:一个线程内，按照代码先后顺序
2. 锁定规则:一个 unLock 操作先行发生于后面对同一个锁的 lock 操作
3. Volatile 变量规则:对一个变量的写操作先行发生于后面对这个变量的读操作
4. 传递规则:如果操作 A 先行发生于操作 B，而操作 B 又先行发生于操作 C，则可以得出 A 先于 C
5. 线程启动规则:Thread 对象的 start() 方法先行发生于此线程的每个一个动作
6. 线程中断规则:对线程 interrupt() 方法的调用先行发生于被中断线程的代码检测到中断事件的发生
7. 线程终结规则:线程中所有的操作都先行发生于线程的终止检测，我们可以通过 Thread.join() 方法结束、 Thread.isAlive() 的返回值手段检测到线程已经终止执行
8. 对象终结规则:一个对象的初始化完成先行发生于他的 finalize() 方法的开始

## 一个简单的实际例子

最简单的例子
多线程计数
如何解决?

## synchronized 的实现

1. 使用对象头标记字(Object monitor)
2. Synchronized 方法优化
3. 偏向锁: BiaseLock

```java
public class SyncCounter {
  private int sum = 0;
  public synchronized int incrAndGet() {
    return ++sum;
  }
  public int addAndGet() {
    synchronized(this) {
      return ++sum;
    }
  }
  public int getSum() {
    return sum;
  }
}
```

```java
// 测试代码
public static void testSyncCounter1() {
  int loopNum = 100_0000;
  SyncCounter counter = new SyncCounter();
  IntStream.range(0, loopNum).parallel().forEach(i -> counter.incrAndGet());
}
```

思考: 哪种方式性能更高?

- 同步块 : 粒度小
- 同步方法: 专有指令

## volatile

1. 每次读取都强制从主内存刷数据
2. 适用场景: 单个线程写;多个线程读
3. 原则: 能不用就不用，不确定的时候也不用
4. 替代方案: Atomic 原子操作类

```java
x = 2; // 语句1
y = 0; // 语句2
flag = true; // 语句3 -> 假如是volatile的
x = 4; // 语句4
y = -1; // 语句5
```

那么，语句 1 和 2，不会被重排到 3 的后面，4 和 5 也不会到前面。
同时可以保证 1 和 2 的结果是对 3、4、5 可见。

## final

|  final 定义类型   |                                                             说明                                                              |
| :---------------: | :---------------------------------------------------------------------------------------------------------------------------: |
|  final class XXX  |                                                          不允许继承                                                           |
|    final 方法     |                                                        不允许 Override                                                        |
|  final 局部变量   |                                                          不允许修改                                                           |
|  final 实例属性   | · 构造函数/初始化块/\<init>之后不允许变更; ·只能赋值一次 · 安全发布: 构造函数结束返回时, final 域最新的值被保证对其他线程可见 |
| final static 属性 |                                        \<client> 静态块执行后不允许变更; 只能赋值一次                                         |

思考: final 声明的引用类型与原生类型在处理时有什么区别?
Java 里的常量替换。写代码最大化用 final 是个好习惯。
