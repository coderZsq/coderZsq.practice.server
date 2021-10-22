你好，我是孔令飞。

上一讲，我们一起安装和配置了一个基本的 Go 开发环境。这一讲，我就来教你怎么在它的基础上，快速部署好 IAM 系统。

因为我们要通过一个 IAM 项目来讲解怎么开发企业级 Go 项目，所以你要对 IAM 项目有比较好的了解，了解 IAM 项目一个最直接有效的方式就是去部署和使用它。

这不仅能让你了解到 IAM 系统中各个组件功能之间的联系，加深你对 IAM 系统的理解，还可以协助你排障，尤其是跟部署相关的故障。此外，部署好 IAM 系统也能给你后面的学习准备好实验环境，边学、边练，从而提高你的学习效率。

所以，今天我们专门花一讲的时间来说说怎么部署和使用 IAM 系统。同时，因为 IAM 系统是一个企业级的项目，有一定的复杂度，我建议你严格按照我说的步骤去操作，否则可能会安装失败。

总的来说，我把部署过程分成 2 大步。

1. 安装和配置数据库：我们需要安装和配置 MariaDB、Redis 和 MongoDB。
2. 安装和配置 IAM 服务：我们需要安装和配置 iam-apiserver、iam-authz-server、iam-pump、iamctl 和 man 文件。

当然啦，如果你实在不想这么麻烦地去安装，我也在这一讲的最后给出了一键部署 IAM 系统的方法，但我还是希望你能按照我今天讲的详细步骤来操作。

那话不多说，我们直接开始操作吧！

### 下载 iam 项目代码

因为 IAM 的安装脚本存放在 iam 代码仓库中，安装需要的二进制文件也需要通过 iam 代码构建，所以在安装之前，我们需要先下载 iam 代码：

```shell
$ mkdir -p $WORKSPACE/golang/src/github.com/marmotedu
$ cd $WORKSPACE/golang/src/github.com/marmotedu
$ git clone --depth=1 https://github.com/marmotedu/iam
```

其中，marmotedu 和 marmotedu/iam 目录存放了本实战项目的代码，在学习过程中，你需要频繁访问这 2 个目录，为了访问方便，我们可以追加如下 2 个环境变量和 2 个 alias 到$HOME/.bashrc 文件中：

```shell
$ tee -a $HOME/.bashrc << 'EOF'
# Alias for quick access
export GOWORK="$WORKSPACE/golang/src"
export IAM_ROOT="$GOWORK/github.com/marmotedu/iam"
alias mm="cd $GOWORK/github.com/marmotedu"
alias i="cd $GOWORK/github.com/marmotedu/iam"
EOF
$ bash
```

之后，我们可以先通过执行 alias 命令 mm 访问 $GOWORK/github.com/marmotedu 目录，再通过执行 alias 命令 i 访问 $GOWORK/github.com/marmotedu/iam 目录。

这里我也建议你善用 alias，将常用操作配置成 alias，方便以后操作。

在安装配置之前需要执行以下命令 export going 用户的密码，这里假设密码是 iam59!z$：

```
export LINUX_PASSWORD='iam59!z$'
```

### 安装和配置数据库

因为 IAM 系统用到了 MariaDB、Redis、MongoDB 数据库来存储数据，而 IAM 服务在启动时会先尝试连接这些数据库，所以为了避免启动时连接数据库失败，这里我们先来安装需要的数据库。

### 安装和配置 MariaDB

IAM 会把 REST 资源的定义信息存储在关系型数据库中，关系型数据库我选择了 MariaDB。为啥选择 MariaDB，而不是 MySQL 呢？。选择 MariaDB 一方面是因为它是发展最快的 MySQL 分支，相比 MySQL，它加入了很多新的特性，并且它能够完全兼容 MySQL，包括 API 和命令行。另一方面是因为 MariaDB 是开源的，而且迭代速度很快。

首先，我们可以通过以下命令安装和配置 MariaDB，并将 Root 密码设置为 iam59!z$：

```shell
$ cd $IAM_ROOT
$ ./scripts/install/mariadb.sh iam::mariadb::install
```

然后，我们可以通过以下命令，来测试 MariaDB 是否安装成功：

```shell
$ mysql -h127.0.0.1 -uroot -p'iam59!z$'
MariaDB [(none)]>
```
