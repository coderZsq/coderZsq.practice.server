# maven 打包
mvn clean package -Dmaven.test.skip=true -Pprod

# 解压war包
unzip crm-1.0.0.war -d crm

# 配置tomcat路径
vi /usr/local/apache-tomcat-8.5.53/conf/server.xml
# <Context docBase="/root/apps/crm" path="/" />

# 重启tomcat
/usr/local/apache-tomcat-8.5.53/bin/startup.sh

# 查看启动日志
tail -100f /usr/local/apache-tomcat-8.5.53/logs/catalina.out