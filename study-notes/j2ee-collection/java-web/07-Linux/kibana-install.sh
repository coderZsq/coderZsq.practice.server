$ tar -zxvf kibana-6.5.4-linux-x86_64.tar.gz -C /usr/local/

$  cd /usr/local/kibana-6.5.4-linux-x86_64/
bin  config  data  LICENSE.txt  node  node_modules  NOTICE.txt  optimize  package.json  plugins  README.txt  src  webpackShims

$ vi /config/kibana.yml
server.host: "0.0.0.0"

$ nohup ./bin/kibana &

$ netstat -ntlp
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      1218/sshd
tcp        0      0 0.0.0.0:5601            0.0.0.0:*               LISTEN      4710/./bin/../node/
tcp6       0      0 :::33060                :::*                    LISTEN      1004/mysqld
tcp6       0      0 :::3306                 :::*                    LISTEN      1004/mysqld