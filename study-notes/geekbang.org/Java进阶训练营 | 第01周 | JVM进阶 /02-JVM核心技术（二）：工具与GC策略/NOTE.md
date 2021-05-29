# 1.JDK 内置命令行工具

## JVM 命令行工具

|      工具      |                                                            简介                                                             |
| :------------: | :-------------------------------------------------------------------------------------------------------------------------: |
|      java      |                                                     Java 应用的启动程序                                                     |
|     javac      |                                                     JDK 内置的编译工具                                                      |
|     javap      |                                                   反编译 class 文件的工具                                                   |
|    javadoc     |                                    根据 Java 代码和标准注释，自动生成相关的 API 说明文档                                    |
|     javah      |                                         JNI 开发时，根据 java 代码生成需要的.h 文件                                         |
|    extcheck    |                                 检查某个 jar 文件和运行时扩展 jar 有没有版本冲突，很少使用                                  |
|      jdb       |            Java Debugger: 可以调试本地和远端程序，属于 JPDA 中的一个 demo 实现，供其他调试器参考。开发时很少使用            |
|     jdeps      |                                               探测 class 或 jar 包需要的依赖                                                |
|      jar       | 打包工具，可以将文件和目录打包成为.jar 文件；.jar 文件本质上就是 zip 文件，只是后缀不同。使用时按顺序对应好选项和参数即可。 |
|    keytool     |                                  安全证书和秘钥的管理工具；（支持生成、导入、导出等操作）                                   |
|   jarsigner    |                                                   JAR 文件签名和验证工具                                                    |
|   policytool   |                                    实际上这是一款图形界面工具，管理本机的 Java 安全策略                                     |
|   jps/jinfo    |                                                       查看 java 进程                                                        |
|     jstat      |                                                  查看 JVM 内部 gc 相关信息                                                  |
|      jmap      |                                                 查看 heap 或类占用空间统计                                                  |
|     jstack     |                                                        查看线程信息                                                         |
|      jcmd      |                                              执行 JVM 相关分析命令（整合命令）                                              |
| jrunscript/jjs |                                                        执行 js 命令                                                         |

## JVM 命令行工具 -- jps/jinfo

```sh
jps
6539 Jps
23784 QuorumPeerMain
4262 Bootstrap
2640 Launcher

jps -help
usage: jps [-help]
       jps [-q] [-mlvV] [<hostid>]

Definitions:
    <hostid>:      <hostname>[:<port>]
```

## JVM 命令行工具 -- jstat\*

```sh
jstat -help
Usage: jstat --help|-options
       jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]

Definitions:
  <option>      可用的选项，查看详情请使用 -options
  <vmid>        虚拟机标识符。格式: <lvmid>[@<hostname>[:<port>]]
  <lines>       标题行间隔的频率。
  <interval>    采样周期，<n>["ms"|"s"],默认单位是毫秒"ms".
  <count>       采样总次数。
  -J<flag>      传给jstat底层JVM的<flag>参数。
```

```sh
jstat -options
-class 类加载（Class loader）信息统计
-compiler JIT 即时编译相关的统计信息。
-gc GC 相关的堆内存信息。用法：jstat -gc -h 10 -t 864 1s 20
-gccapacity 各个内存池分代空间的容量。
-gccause 看上次GC，本次GC (如果正在GC中) 的原因，其他输出和 -gcutil 选项一致。
-gcnew 年轻代的统计信息。（New = Young = Eden + S0 + S1）
-gcnewcapacity 年轻代空间大小统计。
-gcold 老年代和元数据区的行为统计。
-gcoldcapacity old空间大小统计。
-gcmetacapacity meta区大小统计。
-gcutil GC 相关区域的使用率（utilzation）统计。
-printcompilation 打印JVM编译统计信息。
```

演示
jstat -gc pid 1000 1000

- Timestamp 列： JVM 启动时间（秒）
- S0C: 0 号存活区的当前容量（capacity），单位 kB
- S1C: 1 号存活区的当前容量，单位 kB
- S0U: 0 号存活区的使用量（utilzation），单位 kB
- S1U: 1 号存活区的使用量，单位 kB
- EC: Eden 区，新生代的当前容量，单位 kB
- EU: Eden 区，新生代的使用量，单位 kB
- OC: Old 区，老年代的当前容量，单位 kB
- OU: Old 区，老年代的使用量，单位 kB (!需要关注)
- MC: 元数据去的容量，单位 kB
- MU: 元数据区的使用量，单位 kB
- CCSC: (非堆)压缩的 class 空间容量，单位 kB
- CCSU: (非堆)压缩的 class 空间使用量，单位 kB
- YGC: 年轻代 GC 的次数
- YGCT: 年轻代 GC 消耗的总时间。（!重点关注）
- FGC: Full GC 的次数
- FGCT: Full GC 消耗的时间 （!重点关注）
- GCT：垃圾收集消耗的总时间。

演示：
jstat -gcutil pid 1000 1000

-t 选项位置是固定的，不能在前也不能在后。可以看出是用于显示时间戳，即 JVM 启动到现在的秒数

简答分析一下：

- Timestamp 列： JVM 启动时间（秒）
- S0 就是 0 号存活区的百分比使用率。0%很正常，因为 S0 和 S1 随时有一个是空的。
- S1 就是 1 号 存活区的百分比使用率。
- E 就是 Eden 区，新生代的百分比使用率。
- O 就是 Old 区，老年代。百分比使用率。
- M 就是 Meta 区，元数据区百分比使用率。
- CCS，压缩 class 空间（Compressed class space）的百分比使用率。
- YGC（Young GC）,年轻代 GC 的次数。
- YGCT 年轻代 GC 消耗的总时间。
- FGC FullGC 的次数。
- FGCT FullGC 的总时间
- GCT 所有 GC 加起来消耗的总时间，即 YGCT + FGCT

可以看到，-gcutil 这个选项出来的信息不太好用，统计的结果是百分比，不太直观。
再看看 -gc 选项，GC 相关的堆内存信息。

## JVM 命令行工具 -- jmap

常用选项就 3 个

-heap 打印堆内存（内存池）的配置和使用信息。
-histo 看哪些类占用的空间最多，直方图
-dump:format=b,file=xxxx.hprof Dump 堆内存

演示：
jmap -heap pid
jmap -histo pid
jmap -dump:format=b,file=3826.hprof 3826

## JVM 命令行工具 -- jstack

-F 强制执行 thread dump. 可在 Java 进程卡死（hung 住）时使用，此选项可能需要系统权限。
-m 混合模式（mixed mode），将 Java 帧和 native 帧一起输出，比如持有的锁，等待的锁。

演示：
jstack -l pid

## JVM 命令行工具 -- jcmd\*

Jcmd 综合了前面的几个命令

示例：
jcmd pid VM.version
jcmd pid VM.flags
jcmd pid VM.command_line
jcmd pid VM.system_properties
jcmd pid Thread.print
jcmd pid GC.class_histogram
jcmd pid GC.heap_info

## JVM 命令行工具 -- jrunscript/jjs

当 curl 命令用
jrunscript -e "eat('http://www.baidu.com')"
执行 js 脚本片段
jrunscript -e "print('hello,kk.jvm'+1)"
执行 js 文件
jrunscript -l js -f /XXX/XXX/test.js

# 2. JDK 内置图形化工具

## JVM 图形化工具 -- jconsole

在命令行输入 jconsole 即可打开

本地 JVM 可以直接选择
远程 JVM 可以通过 JMX 方式连接

## JVM 图形化工具 -- jvisualvm

## JVM 图形化工具 -- VisualGC

## JVM 图形化工具 -- jmc

## JVM 工具总结

jps/jinfo
jstat
jmap
jstack
jcmd
jrunscript/jjs

jconsole
jvisualvm
visualGC
jmc

# 3. GC 的背景与一般原理

为什么会有 GC

本质上是内存资源的有限性

因此需要大家共享使用，手工申请，手动释放

下面我们举个例子

仓库与引用计数：计数为 0
简单粗暴，一般有效

实际情况复杂一点
仓库与仓库之间也有关系

导致形成一个环，大家的计数永远不为 0（跟线程、事务死锁一个原理）
这些仓库都没法再用：内存泄漏、内存溢出

怎么改进呢？引用计数 -> 引用跟踪

---

对象分配在新生代的 Eden 区
标记阶段 Eden 区存活的对象就会复制到存活区
注意：为什么是复制，不是移动？？？大家想想
两个存活区 from 和 to，互换角色。对象存活到一定周期会提升到老年代

由如下参数控制提升阈值
-XX: +MaxTenuringThreshold=15

老年代默认都是存活对象，采用移动方式：

1. 标记所有通过 GC roots 可达的对象；
2. 删除所有不可达对象；
3. 整理老年代空间中的内容，方法是将所有的存活对象复制，从老年代空间开始的地方依次存放。

(年轻代只有少量对象存活，多以复制效率高，老年代少量对象被干掉，所以移动效率高)

持久代/元数据区
1.8 之前 -XX: MaxPermSize=256m
1.8 之后 -XX: MaxMetaspaceSixe=256m

---

可以作为 GC Roots 对象

1. 当前正在执行的方法里的局部变量和出入参数
2. 活动线程（Active threads）
3. 所有类的静态字段（static field）
4. JNI 引用

此阶段暂停时间，与堆内存大小，对象的总数没有直接关系，而是由存活对象（alive objects）的数量来决定。所以增加堆内存的大小并不会直接影响标记阶段占用的时间。

# 4. 串行 GC/并行 GC （Serial GC/Parallel GC）

## 串行 GC(Serial GC) / ParNewGC

-XX：+UseSerialGC 配置串行 GC
串行 GC 对年轻代使用 mark-copy(标记-复制)算法，对老年代使用 mark-sweep-compact(标记-清除-整理）算法。

两者都是单线程的垃圾收集器，不能进行并行处理，所以都会触发全线暂停（STW），停止所有的应用线程。

因此这种 GC 算法不能充分利用多核 CPU。不管有多少 CPU 内核，JVM 在垃圾收集时都只能使用单个核心。

CPU 利用率高，暂停时间长。简单粗暴，就像老式的电脑，动不动就卡死。

该选项只适合几百 MB 堆内存的 JVM，而且是单核 CPU 时比较有用。
想想 why?

-XX: +UseParNewGC 改进版本的 Serial GC,可以配合 CMS 使用。

## 并行 GC（Parallel GC）

-XX: +UseParallelGC
-XX: +UseParallelOldGC
-XX: +UseParallelGC -XX: +UseParallelOldGC

年轻代和老年代的垃圾回收都会触发 STW 事件。
在年轻代使用标记-复制（mark-copy）算法，在老年代使用 标记-清除-整理（mark-sweep-compact）算法。

-XX: ParallelGCThreads=N 来指定 GC 线程数，其默认值为 CPU 核心数。

并行垃圾收集器适用于多核服务器，主要目标是增加吞吐量。因为对系统资源的有效使用，能达到更高的吞吐量：

- 在 GC 期间，所有 CPU 内核都在并行清理垃圾，所以总暂停时间更短。
- 在两次 GC 周期的间隔期，没有 GC 线程在运行，不会消耗任何系统资源。

# 5. CMS GC/G1 GC

## CMS GC（Mostly Concurrent Mark and Sweep Garbage Collector）

-XX: +UseConcMarkSweepGC
其对年轻代采用并行 STW 方式的 mark-copy（标记-复制）算法，对老年代主要使用并发 mark-sweep（标记-清除）算法。

CMS GC 的设计目标是避免在老年代垃圾收集时出现长时间的卡顿，主要通过两种手段来达成此目标：

1. 不对老年代进行整理，而是使用空闲列表（free-lists）来管理内存空间的回收。
2. 在 mark-and-sweep（标记-清除）阶段的大部分工作和应用线程一起并发执行。

也就是说，在这些阶段并没有明显的应用线程暂停。但值得注意的是，它仍然和应用线程争抢 CPU 时间。默认情况下，CMS 使用的并发线程数等于 CPU 的核心数的 1/4。

如果服务器时多核 CPU，并且主要调优目标是降低 GC 停顿导致的系统延迟，那么使用 CMS 时个很明智的选择。进行老年代的并发回收时，可能会伴随着多次年轻代的 minor GC。

思考：并行 Parallel 与并发 Concurrent 的区别。

阶段 1：Initial Mark (初始标记)
阶段 2：Concureent Mark (并发标记)
阶段 3：Concureent Preclean (并发预清理)
阶段 4：Final Remark (最终标记)
阶段 5：Concureent Sweep (并发清除)
阶段 6：Concurrent Reset (并发重置)

## CMS GC -- 六个阶段 1（STW）

阶段 1：Initial Mark (初始标记)

这个阶段伴随着 STW 暂停。初始标记的目标是标记所有的根对象，包括根对象直接引用的对象，以及被年轻代中所有存活对象所引用的对象（老年代单独回收）。

---

## CMS GC -- 六个阶段 2

阶段 2：Concureent Mark (并发标记)

在此阶段，CMS GC 遍历老年代，标记所有的存活对象，从前一阶段“Initial Mark”找到的根对象开始算起。“并发标记”阶段，就是与应用程序同时运行，不用暂停的阶段。

## CMS GC -- 六个阶段 3

阶段 3：Concureent Preclean (并发预清理)

此阶段同样是与应用线程并发执行的，不需要停止应用线程。因为前一阶段【并发标记】与程序并发运行，可能有一些引用关系已经发生了改变。如果在并发标记过程中引用关系发生了变化，JVM 会通过“Card（卡片）”的方式将发生了改变的区域记为“脏”区，这就是所谓的 卡片标记（Card Marking）。

---

## CMS GC -- 六个阶段 4（STW）

阶段 4：Final Remark (最终标记)

最终标记阶段是此次 GC 事件中的第二次（也是最后一次）STW 停顿。本阶段的目标是完成老年代中所有存活对象的标记。因为之前的预清理阶段是并发执行的，有可能 GC 线程跟不上应用程序的修改速度。所以需要一次 STW 暂停来处理各种复杂的情况。

通常 CMS 会尝试在年轻代尽可能空的情况下执行 Final Remark 阶段，以免连续触发多次 STW 事件。

---

## CMS GC -- 六个阶段 5

阶段 5：Concureent Sweep (并发清除)

此阶段与应用程序并发执行，不需要 STW 停顿。JVM 在此阶段删除不再使用的对象，并回收他们占用的内存空间。

## CMS GC -- 六个阶段 6

阶段 6：Concurrent Reset (并发重置)

此阶段与应用程序并发执行，重置 CMS 算法相关的内部数据，为下一次 GC 循环做准备。

CMS 垃圾收集器在减少停顿时间上做了很多复杂而有用的工作，用于垃圾回收的并发线程执行的同时，并不需要暂停应用线程。当然 CMS 也有一些缺点，其中最大的问题就是老年代内存碎片问题（因为不压缩），在某些情况下 GC 会造成不可预测的暂停时间，特别是堆内存较大的情况下。

# 演示：CMS GC, 常用参数以及其内存分配（注意跟 ParallelGC 有什么差异）

## G1 GC

G1 的全称是 Garbage-First, 意味垃圾优先，哪一块垃圾最多就优先清理它。

G1 GC 最主要的设计目标是：将 STW 停顿的事件和粉笔，变成可预期可配置的。

事实上，G1 GC 是一款软实时垃圾收集器，可以为其设置某项特定的性能指标。为了达成可预期停顿时间的指标，G1 GC 有一些独特的实现。

首先，堆不再分成年轻代和老年代，而是划分为多个（通常是 2048 个）可以存放对象的小块堆区域（smaller heap regions）。每个小块，可能一会被定义成 Eden 区，一会被指定为 Survivor 区或者 Old 区。在逻辑上，所有的 Eden 区和 Survivor 区合起来就是年轻代，所有的 Old 区拼在一起那就是年老代。

-XX：+UseG1GC -XX: MaxGCPauseMillis=50

---

这样划分之后，使得 G1 不必每次都去收集整个堆空间，而是以增量的方式来进行处理：每次只处理一部分内存块，称为此次 GC 的回收集（collection set）。每次 GC 暂停都会收集所有年轻代的内存块，但一般只包含部分老年代的内存块。

G1 的另一项创新是，在并发阶段估算每个小堆块存活的总数。构建回收集的原则是：垃圾最多的小块会被优先收集。这也是 G1 名称的由来。

## G1 GC -- 配置参数

-XX: +UseG1GC: 启用 G1 GC；
-XX: G1NewSizePrecent:初始年轻代占整个 Java Heap 的大小，默认值为 5%；
-XX: G1MaxNewSizePercent:最大年轻代占整个 Java Heap 的大小，默认值为 60%；
-XX: G1HeapRegionSize:设置每个 Region 的大小，单位 MB，需要为 1，2，4，8，16，32 中的某个值，默认是堆内存的 1/2000。如果这个值设置比较大，那么大对象就可以进入 Region 了。
-XX: ConcGCThreads:与 Java 应用一起执行的 GC 线程数量，默认是 Java 线程的 1/4，减少这个参数的数值可能会提升并行回收的效率，提高系统内部吞吐量。如果这个数值过低，参与回收垃圾的线程不足，也会导致并行回收机制耗时加长。
-XX: +InitiingHeapOccupancyPrecent(简称 IHOP)：G1 内部并行回收循环启动的阈值，默认为 Java Heap 的 45%。这个可以理解为老年代使用大于等于 45%的时候，JVM 会启动垃圾回收。这个值非常重要，它决定了在什么时间启动老年代的并行回收。
-XX: G1HeapWastePercent:G1 停止回收的最小内存大小，默认是堆大小的 5%，GC 会收集所有的 Region 中遗留少量的下次处理，这样也降低了单次消耗的时间。
-XX: G1MixedGCCountTarget:设置并行循环之后需要有多少个混合 GC 启动，默认值是 8 个。老年代 Regions 的回收时间通常比年轻代收集时间要长一些。所以如果混合收集器比较多，可以允许 G1 延长老年代的收集时间。

---

-XX: +G1PrintRegionLivenessInfo：这个参数需要和 -XX：+UnlockDiagnosticVMOptions 配合启动，打印 JVM 的调式信息，每个 Region 里的对象存活信息。
-XX: G1ReservePercent：G1 为了保留一些空间用于年代之间的提升，默认值是堆空间的 10%。因为大量执行回收的地方在年轻代（存活时间较短），所以如果你的应用里面有比较大的堆内存空间、比较多的大对象存活，这里需要保留一些内存。
-XX: +G1SummarizeRSetStats:这也是一个 VM 的调试信息。如果启用，会在 VM 退出的时候打印出 Rsets 的详细总结信息。如果启用 -XX：G1SummaryRSetStatsPeriod 参数，就会阶段性地打印 Rsets 信息。
-XX: +GCTimeRatio:这个参数就是计算花在 Java 应用线程上和花在 GC 线程上的时间比率，默认是 9，跟新生代内存的分配比例一致。这个参数主要的目的是让用户可以控制花在应用上的时间，G1 的计算公式是 100/（1+GCTimeRatio）。这样如果参数设置为 9，则最多 10%的时间会花在 GC 工作上面。Parallel GC 的默认值是 99,表示 1%的事件被用在 GC 上面，这是因为 Parallel GC 贯穿整个 GC，而 G1 则根据 Region 来进行划分，不需要全局性扫描整个内存堆。
-XX: +UseStringDeduplication:手动开启 Java String 对象的去重工作，这个是 JDK8u20 版本之后新增的参数，主要用于相同 String 避免重复申请内存，节约 Region 的使用。
-XX: MaxGCPauseMills:预期 G1 每次执行 GC 操作的暂停时间，单位是毫秒，默认值是 200 毫秒，G1 会尽量保证控制在这个范围内。

## G1 GC 的处理步骤 1

1、年轻代模式转移暂停（Evacuation Pause）
G1 GC 会通过前面一段时间的运行情况来不断的调整自己的回收策略和行为，以此来比较稳定地控制暂停时间。在应用程序刚启动时，G1 还没有采集到什么足够的信息，这时候就处于初始的 fully-young 模式。当年轻代空间用满后，应用线程会被暂停，年轻代内存块中的存活对象被拷贝到存活区。如果还没有存活区，则任意选择一部分空闲的内存块作为存活区。

拷贝的过程称为转移（Evacuation）, 这和前面介绍的其他年轻代收集器是一样的工作原理。

## G1 GC 的处理步骤 2

2、并发标记（Concurrent Marking）
同时我们也可以看到，G1 GC 的很多概念简历在 CMS 的基础上，所以下面的内容需要对 CMS 有一定的理解。

G1 并发标记的过程与 CMS 基本上是一样的。G1 的并发标记通过 Snapshot-At-The-Beginning（起始快照）的方式，在标记阶段开始时记下所有的存活对象。即使在标记的同时又有一些变成了垃圾。通过对象的存活信息，可以构建出每个小堆块的存活状态，以便回收集能高效地进行选择。

这些信息在接下来的阶段会用来执行老年代区域的垃圾收集。

有两种情况是可以完全并发执行的：
一、如果标记阶段确定某个小堆块中没有存活对象，只包含垃圾；
二、在 STW 转移暂停期间，同时包含垃圾和存活对象的老年代小堆块。

当堆内存的总体使用比例到一定数值，就会触发并发标记。这个默认比例是 45%，但也可以通过 JVM 参数 InitatingHeapOccupancyPrecent 来设置。和 CMS 一样，G1 的并发标记也是由多个阶段组成，其中一些阶段是完全并发的，还有一些阶段则会暂停应用线程。

---

阶段 1：Initial Mark(初始标记)
此阶段标记所有从 GC 根对象直接可达的对象。

阶段 2：Root Region Scan(Root 区扫描)
此阶段标记所有从“根区域”可达的存活对象。根区域包括：非空的区域，以及在标记过程中不得不收集的区域。

阶段 3：Concurrent Mark（并发标记）
此阶段和 CMS 的并发标记阶段非常类似：只遍历对象图，并在一个特殊的位图中标记能访问到的对象。

阶段 4：Remark（再次标记）
和 CMS 类似，这是一次 STW 停顿（因为不是并发的阶段），以完成标记过程。G1 收集器会短暂地停止应用线程，停止并发更新信息的写入，处理其中的少量信息，并标记所有在并发标记开始时未被标记的存活对象。

阶段 5：Cleanup (清理)
最后这个清理阶段为即将到来的转移阶段做准备，统计小堆块中所有存活的对象，并将小堆块进行排序，以提升 GC 的效率，维护并发标记的内部状态。所有不包含存活对象的小堆块在此阶段都被回收了。有一部分任务是并发的：例如空堆区的回收，还有大部分的存活率计算。此阶段也需要一个短暂的 STW 暂停。

## G1 GC 的处理步骤 3

3、转移暂停：混合模式（Evacuation Pause(mixed)）
并发标记完成之后，G1 将执行一次混合手机（mixed collection）,就是不只清理年轻代，还将一部分老年代区域也加入到回收集中。混合模式的转移暂停不一定紧跟并发标记阶段。有很多规则和历史数据会影响混合模式的启动时机。比如，假若在老年代中可以并发地腾出很多的小堆块，就没有必要启动混合模式。

因此，在并发标记与混合转移暂停之间，很可能会存在多次 yong 模式的转移暂停。

具体添加到回收集的老年代小堆块的大小及顺序，也是基于许多规则来判定的。其中包括指定的软实时性能指标，存活性，以及在并发标记期间收集的 GC 效率等数据，外加一些可配置的 JVM 选项。混合手机的过程，很大程度上和前面的 fully-yong gc 是一样的。
