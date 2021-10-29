你好，我是孔令飞。今天，我们更新一期特别放送作为“加餐”，希望日常催更的朋友们食用愉快。

在第 14 讲 里，我强调了熟练掌握 Makefile 语法的重要性，还推荐你去学习陈皓老师编写的《跟我一起写 Makefile》 (PDF 重制版)。也许你已经点开了链接，看到那么多 Makefile 语法，是不是有点被“劝退”的感觉？

其实在我看来，虽然 Makefile 有很多语法，但不是所有的语法都需要你熟练掌握，有些语法在 Go 项目中是很少用到的。要编写一个高质量的 Makefile，首先应该掌握一些核心的、最常用的语法知识。这一讲我就来具体介绍下 Go 项目中常用的 Makefile 语法和规则，帮助你快速打好最重要的基础。

Makefile 文件由三个部分组成，分别是 Makefile 规则、Makefile 语法和 Makefile 命令（这些命令可以是 Linux 命令，也可以是可执行的脚本文件）。在这一讲里，我会介绍下 Makefile 规则和 Makefile 语法里的一些核心语法知识。在介绍这些语法知识之前，我们先来看下如何使用 Makefile 脚本。

### Makefile 的使用方法

在实际使用过程中，我们一般是先编写一个 Makefile 文件，指定整个项目的编译规则，然后通过 Linux make 命令来解析该 Makefile 文件，实现项目编译、管理的自动化。

默认情况下，make 命令会在当前目录下，按照 GNUmakefile、makefile、Makefile 文件的顺序查找 Makefile 文件，一旦找到，就开始读取这个文件并执行。

大多数的 make 都支持“makefile”和“Makefile”这两种文件名，但我建议使用“Makefile”。因为这个文件名第一个字符大写，会很明显，容易辨别。make 也支持 -f 和 --file 参数来指定其他文件名，比如 make -f golang.mk 或者 make --file golang.mk 。

### Makefile 规则介绍

学习 Makefile，最核心的就是学习 Makefile 的规则。规则是 Makefile 中的重要概念，它一般由目标、依赖和命令组成，用来指定源文件编译的先后顺序。Makefile 之所以受欢迎，核心原因就是 Makefile 规则，因为 Makefile 规则可以自动判断是否需要重新编译某个目标，从而确保目标仅在需要时编译。

这一讲我们主要来看 Makefile 规则里的规则语法、伪目标和 order-only 依赖。

### 规则语法

Makefile 的规则语法，主要包括 target、prerequisites 和 command，示例如下：

```makefile
target ...: prerequisites ...
    command
    ...
    ...
```

target，可以是一个 object file（目标文件），也可以是一个执行文件，还可以是一个标签（label）。target 可使用通配符，当有多个目标时，目标之间用空格分隔。

prerequisites，代表生成该 target 所需要的依赖项。当有多个依赖项时，依赖项之间用空格分隔。

command，代表该 target 要执行的命令（可以是任意的 shell 命令）。

- 在执行 command 之前，默认会先打印出该命令，然后再输出命令的结果；如果不想打印出命令，可在各个 command 前加上@。
- command 可以为多条，也可以分行写，但每行都要以 tab 键开始。另外，如果后一条命令依赖前一条命令，则这两条命令需要写在同一行，并用分号进行分隔。
- 如果要忽略命令的出错，需要在各个 command 之前加上减号-。

只要 targets 不存在，或 prerequisites 中有一个以上的文件比 targets 文件新，那么 command 所定义的命令就会被执行，从而产生我们需要的文件，或执行我们期望的操作。

我们直接通过一个例子来理解下 Makefile 的规则吧。

第一步，先编写一个 hello.c 文件。

```c
#include <stdio.h>
int main()
{
  printf("Hello World!\n");
  return 0;
}
```

第二步，在当前目录下，编写 Makefile 文件。

```makefile
hello: hello.o
  gcc -o hello hello.o

hello.o: hello.c
  gcc -c hello.c

clean:
  rm hello.o
```

第三步，执行 make，产生可执行文件。

```shell
$ make
gcc -c hello.c
gcc -o hello hello.o
$ ls
hello  hello.c  hello.o  Makefile
```

上面的示例 Makefile 文件有两个 target，分别是 hello 和 hello.o，每个 target 都指定了构建 command。当执行 make 命令时，发现 hello、hello.o 文件不存在，就会执行 command 命令生成 target。

第四步，不更新任何文件，再次执行 make。

```shell
$ make
make: 'hello' is up to date.
```

当 target 存在，并且 prerequisites 都不比 target 新时，不会执行对应的 command。

第五步，更新 hello.c，并再次执行 make。

```shell
$ touch hello.c
$ make
gcc -c hello.c
gcc -o hello hello.o
```

当 target 存在，但 prerequisites 比 target 新时，会重新执行对应的 command。

第六步，清理编译中间文件。

Makefile 一般都会有一个 clean 伪目标，用来清理编译中间产物，或者对源码目录做一些定制化的清理：

```shell
$ make clean
rm hello.o
```

我们可以在规则中使用通配符，make 支持三个通配符：\*，? 和~，例如：

```makefile
objects = *.o
print: *.c
    rm *.c
```

### 伪目标

接下来我们介绍下 Makefile 中的伪目标。Makefile 的管理能力基本上都是通过伪目标来实现的。

在上面的 Makefile 示例中，我们定义了一个 clean 目标，这其实是一个伪目标，也就是说我们不会为该目标生成任何文件。因为伪目标不是文件，make 无法生成它的依赖关系，也无法决定是否要执行它。

通常情况下，我们需要显式地标识这个目标为伪目标。在 Makefile 中可以使用.PHONY 来标识一个目标为伪目标：

通常情况下，我们需要显式地标识这个目标为伪目标。在 Makefile 中可以使用.PHONY 来标识一个目标为伪目标：

```makefile
.PHONY: clean
clean:
    rm hello.o
```

伪目标可以有依赖文件，也可以作为“默认目标”，例如：

```makefile
.PHONY: all
all: lint test build
```

因为伪目标总是会被执行，所以其依赖总是会被决议。通过这种方式，可以达到同时执行所有依赖项的目的。

### order-only 依赖

在上面介绍的规则中，只要 prerequisites 中有任何文件发生改变，就会重新构造 target。但是有时候，我们希望只有当 prerequisites 中的部分文件改变时，才重新构造 target。这时，你可以通过 order-only prerequisites 实现。

order-only prerequisites 的形式如下：

```makefile
targets : normal-prerequisites | order-only-prerequisites
    command
    ...
    ...
```

在上面的规则中，只有第一次构造 targets 时，才会使用 order-only-prerequisites。后面即使 order-only-prerequisites 发生改变，也不会重新构造 targets。

只有 normal-prerequisites 中的文件发生改变时，才会重新构造 targets。这里，符号“ | ”后面的 prerequisites 就是 order-only-prerequisites。

到这里，我们就介绍了 Makefile 的规则。接下来，我们再来看下 Makefile 中的一些核心语法知识。

### Makefile 语法概览

因为 Makefile 的语法比较多，这一讲只介绍 Makefile 的核心语法，以及 IAM 项目的 Makefile 用到的语法，包括命令、变量、条件语句和函数。因为 Makefile 没有太多复杂的语法，你掌握了这些知识点之后，再在实践中多加运用，融会贯通，就可以写出非常复杂、功能强大的 Makefile 文件了。

### 命令

Makefile 支持 Linux 命令，调用方式跟在 Linux 系统下调用命令的方式基本一致。默认情况下，make 会把正在执行的命令输出到当前屏幕上。但我们可以通过在命令前加@符号的方式，禁止 make 输出当前正在执行的命令。

我们看一个例子。现在有这么一个 Makefile：

```makefile
.PHONY: test
test:
    echo "hello world"
```

执行 make 命令：

```shell
$ make test
echo "hello world"
hello world
```

可以看到，make 输出了执行的命令。很多时候，我们不需要这样的提示，因为我们更想看的是命令产生的日志，而不是执行的命令。这时就可以在命令行前加@，禁止 make 输出所执行的命令：

```makefile
.PHONY: test
test:
    @echo "hello world"
```

再次执行 make 命令：

```shell
$ make test
hello world
```

可以看到，make 只是执行了命令，而没有打印命令本身。这样 make 输出就清晰了很多。

这里，我建议在命令前都加@符号，禁止打印命令本身，以保证你的 Makefile 输出易于阅读的、有用的信息。

默认情况下，每条命令执行完 make 就会检查其返回码。如果返回成功（返回码为 0），make 就执行下一条指令；如果返回失败（返回码非 0），make 就会终止当前命令。很多时候，命令出错（比如删除了一个不存在的文件）时，我们并不想终止，这时就可以在命令行前加 - 符号，来让 make 忽略命令的出错，以继续执行下一条命令，比如：

```makefile
clean:
    -rm hello.o
```

### 变量

变量，可能是 Makefile 中使用最频繁的语法了，Makefile 支持变量赋值、多行变量和环境变量。另外，Makefile 还内置了一些特殊变量和自动化变量。

我们先来看下最基本的变量赋值功能。

Makefile 也可以像其他语言一样支持变量。在使用变量时，会像 shell 变量一样原地展开，然后再执行替换后的内容。

