你好，我是孔令飞。今天我们来聊聊如何编写高质量的 Makefile。

我们在 第 10 讲 学习过，要写出一个优雅的 Go 项目，不仅仅是要开发一个优秀的 Go 应用，而且还要能够高效地管理项目。有效手段之一，就是通过 Makefile 来管理我们的项目，这就要求我们要为项目编写 Makefile 文件。

在和其他开发同学交流时，我发现大家都认可 Makefile 强大的项目管理能力，也会自己编写 Makefile。但是其中的一些人项目管理做得并不好，我和他们进一步交流后发现，这些同学在用 Makefile 简单的语法重复编写一些低质量 Makefile 文件，根本没有把 Makefile 的功能充分发挥出来。

下面给你举个例子，你就会理解低质量的 Makefile 文件是什么样的了。

```
build: clean vet
  @mkdir -p ./Role
  @export GOOS=linux && go build -v .

vet:
  go vet ./...

fmt:
  go fmt ./...

clean:
  rm -rf dashboard
```

上面这个 Makefile 存在不少问题。例如：功能简单，只能完成最基本的编译、格式化等操作，像构建镜像、自动生成代码等一些高阶的功能都没有；扩展性差，没法编译出可在 Mac 下运行的二进制文件；没有 Help 功能，使用难度高；单 Makefile 文件，结构单一，不适合添加一些复杂的管理功能。

所以，我们不光要编写 Makefile，还要编写高质量的 Makefile。那么如何编写一个高质量的 Makefile 呢？我觉得，可以通过以下 4 个方法来实现：

1. 打好基础，也就是熟练掌握 Makefile 的语法。
2. 做好准备工作，也就是提前规划 Makefile 要实现的功能。
3. 进行规划，设计一个合理的 Makefile 结构。
4. 掌握方法，用好 Makefile 的编写技巧。

那么接下来，我们就详细看看这些方法。

### 熟练掌握 Makefile 语法

工欲善其事，必先利其器。编写高质量 Makefile 的第一步，便是熟练掌握 Makefile 的核心语法。

因为 Makefile 的语法比较多，我把一些建议你重点掌握的语法放在了近期会更新的特别放送中，包括 Makefile 规则语法、伪目标、变量赋值、条件语句和 Makefile 常用函数等等。

如果你想更深入、全面地学习 Makefile 的语法，我推荐你学习陈皓老师编写的《跟我一起写 Makefile》 (PDF 重制版)。

