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
