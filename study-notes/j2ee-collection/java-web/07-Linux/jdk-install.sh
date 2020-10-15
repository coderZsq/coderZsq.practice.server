# 解压jdk
tar -zxvf jdk-8u161-linux-x64.tar.gz -C /usr/local/

# 进入用户目录
cd /usr/local/

# 进入jdk目录
cd jdk1.8.0_161/

# 编辑jdk配置
vi /etc/profile.d/jdk.sh
# #! /bin/bash
# export JAVA_HOME=/usr/local/jdk1.8
# export PATH=$JAVA_HOME/bin:$PATH

# 执行配置
source /etc/profile

# 查看版本
java -version