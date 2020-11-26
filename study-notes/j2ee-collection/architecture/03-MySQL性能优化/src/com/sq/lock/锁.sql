# 全局锁
FLUSH TABLE WITH READ LOCK;

SHOW PROCESSLIST;

UNLOCK TABLES;

# 表锁
LOCK TABLE department READ;

UNLOCK TABLES;

# 行锁
# 超时时间
SHOW VARIABLES LIKE 'innodb_lock_wait_timeout';
# 自动检测
SHOW VARIABLES LIKE '%deadlock%'