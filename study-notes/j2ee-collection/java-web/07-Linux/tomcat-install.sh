# 解压二进制文件到指定目录
tar -zxvf apache-tomcat-8.5.53.tar.gz -C /usr/local

# 配置防火墙 开放8080端口
firewall-cmd --zone=public --add-port=8080/tcp --permanent
firewall-cmd --reload

# 启动tomcat
/usr/local/apache-tomcat-8.5.53/bin/startup.sh

# 查看启动日志
tail -100f /usr/local/apache-tomcat-8.5.53/logs/catalina.out

# 查看启动端口
netstat -ntlp