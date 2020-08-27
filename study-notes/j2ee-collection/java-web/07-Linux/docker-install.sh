https://cr.console.aliyun.com/cn-shanghai/instances/mirrors?accounttraceid=4fb7917218054f2ba56e377a366ee44fkkld

sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "register-mirrors": ["https://n5jclonh.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker

# 查找镜像
$ docker search centos
# NAME                               DESCRIPTION                                     STARS               OFFICIAL            AUTOMATED
# centos                             The official build of CentOS.                   6151                [OK]
# ansible/centos7-ansible            Ansible on Centos7                              132                                     [OK]
# consol/centos-xfce-vnc             Centos container with "headless" VNC session…   119                                     [OK]
# jdeathe/centos-ssh                 OpenSSH / Supervisor / EPEL/IUS/SCL Repos - …   115                                     [OK]
# centos/systemd                     systemd enabled base container.                 87                                      [OK]
# centos/mysql-57-centos7            MySQL 5.7 SQL database server                   81
# imagine10255/centos6-lnmp-php56    centos6-lnmp-php56                              58                                      [OK]
# tutum/centos                       Simple CentOS docker image with SSH access      47
# centos/postgresql-96-centos7       PostgreSQL is an advanced Object-Relational …   46
# kinogmt/centos-ssh                 CentOS with SSH                                 29                                      [OK]
# pivotaldata/centos-gpdb-dev        CentOS image for GPDB development. Tag names…   12
# guyton/centos6                     From official centos6 container with full up…   10                                      [OK]
# centos/tools                       Docker image that has systems administration…   6                                       [OK]
# drecom/centos-ruby                 centos ruby                                     6                                       [OK]
# pivotaldata/centos                 Base centos, freshened up a little with a Do…   5
# darksheer/centos                   Base Centos Image -- Updated hourly             3                                       [OK]
# pivotaldata/centos-mingw           Using the mingw toolchain to cross-compile t…   3
# pivotaldata/centos-gcc-toolchain   CentOS with a toolchain, but unaffiliated wi…   3
# miko2u/centos6                     CentOS6 日本語環境                                   2                                       [OK]
# indigo/centos-maven                Vanilla CentOS 7 with Oracle Java Developmen…   1                                       [OK]
# mcnaughton/centos-base             centos base image                               1                                       [OK]
# blacklabelops/centos               CentOS Base Image! Built and Updates Daily!     1                                       [OK]
# smartentry/centos                  centos with smartentry                          0                                       [OK]
# pivotaldata/centos6.8-dev          CentosOS 6.8 image for GPDB development         0
# pivotaldata/centos7-dev            CentosOS 7 image for GPDB development           0

# 下载镜像
$ docker pull centos:7
# 7: Pulling from library/centos
# 75f829a71a1c: Pull complete
# Digest: sha256:19a79828ca2e505eaee0ff38c2f3fd9901f4826737295157cc5212b7a372cd2b
# Status: Downloaded newer image for centos:7
# docker.io/library/centos:7

# 查看本地镜像
$ docker images
# REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
# centos              7                   7e6257c9f8d8        2 weeks ago         203MB

# 删除镜像
$ docker rmi centos:7

# 创建且运行一个容器
$ docker run hello-world
# Unable to find image 'hello-world:latest' locally
# latest: Pulling from library/hello-world
# 0e03bdcc26d7: Pull complete
# Digest: sha256:7f0a9f93b4aa3022c3a4c147a449bf11e0941a1fd0bf4a8e6c9408b2600777c5
# Status: Downloaded newer image for hello-world:latest

# Hello from Docker!
# This message shows that your installation appears to be working correctly.

# To generate this message, Docker took the following steps:
#  1. The Docker client contacted the Docker daemon.
#  2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
#     (amd64)
#  3. The Docker daemon created a new container from that image which runs the
#     executable that produces the output you are currently reading.
#  4. The Docker daemon streamed that output to the Docker client, which sent it
#     to your terminal.

# To try something more ambitious, you can run an Ubuntu container with:
#  $ docker run -it ubuntu bash

# Share images, automate workflows, and more with a free Docker ID:
#  https://hub.docker.com/

# For more examples and ideas, visit:
#  https://docs.docker.com/get-started/

# 查看容器
$ docker ps -a
# CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                     PORTS               NAMES
# e9fbd94bc862        hello-world         "/hello"            2 minutes ago       Exited (0) 2 minutes ago                       sweet_jones

# 启动容器
$ docker start e9fbd94bc862

# 删除容器
$ docker rm e9fbd94bc862

# 停止容器
$ docker stop e9fbd94bc862

# 进入容器内部
$ docker run --name centos-demo centos:7 ping localhost
$ docker logs centos-demo # 查看日志
$ docker exec -it centos-demo /bin/bash