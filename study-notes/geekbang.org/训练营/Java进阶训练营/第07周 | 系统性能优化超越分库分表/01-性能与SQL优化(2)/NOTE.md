# 1. MySQL 事务与锁

## MySQL 事务

    事务可靠性模型 ACID:
    - Atomicity: 原子性, 一次事务中的操作要么全部成功, 要么全部失败。 
    - Consistency: 一致性, 跨表、跨行、跨事务, 数据库始终保持一致状态。 
    - Isolation: 隔离性, 可见性, 保护事务不会互相干扰, 包含4种隔离级别。 
    - Durability:, 持久性, 事务提交成功后,不会丢数据。如电源故障, 系统崩溃。

    性能 vs. 可靠性

    InnoDB:
    双写缓冲区、故障恢复、操作系统、fsync() 、磁盘存储、缓存、UPS、网络、备份策略 ......

---

    表级锁
    意向锁: 表明事务稍后要进行哪种类型的锁定 
    - 共享意向锁(IS): 打算在某些行上设置共享锁 
    - 排他意向锁(IX): 打算对某些行设置排他锁 
    - Insert 意向锁: Insert 操作设置的间隙锁 

    其他
    - 自增锁(AUTO-IN) 
    - LOCK TABLES/DDL

---

    行级锁(InnoDB)
    - 记录锁(Record): 始终锁定索引记录，注意隐藏的聚簇索引;
    - 间隙锁(Gap):
    - 临键锁(Next-Key): 记录锁+间隙锁的组合; 可“锁定”表中不存在记录 
    - 谓词锁(Predicat): 空间索引

---

    死锁:
    - 阻塞与互相等待
    - 增删改、锁定读
    - 死锁检测与自动回滚
    - 锁粒度与程序设计

---

    《SQL:1992标准》规定了四种事务隔离级别(Isolation): 
    - 读未提交: READ UNCOMMITTED
    - 读已提交: READ COMMITTED
    - 可重复读: REPEATABLE READ
    - 可串行化: SERIALIZABLE

    事务隔离是数据库的基础特征。
    MySQL:
    - 可以设置全局的默认隔离级别
    - 可以单独设置会话的隔离级别
    - InnoDB 实现与标准之间的差异

--- 

    读未提交: READ UNCOMMITTED
    - 很少使用
    - 不能保证一致性
    - 脏读(dirty read) : 使用到从未被确认的数据(例如: 早期版本、回滚)
    锁:
    - 以非锁定方式执行
    - 可能的问题: 脏读、幻读、不可重复读

---

    读已提交: READ COMMITTED
    - 每次查询都会设置和读取自己的新快照。
    - 仅支持基于行的 bin-log
    - UPDATE 优化: 半一致读(semi-consistent read)
    - 不可重复读: 不加锁的情况下, 其他事务 UPDATE 或 DELETE 会对查询结果有影响 
    - 幻读(Phantom): 加锁后, 不锁定间隙, 其他事务可以 INSERT。
    锁:
    - 锁定索引记录, 而不锁定记录之间的间隙 
    - 可能的问题: 幻读、不可重复读

---

    MySQL 事务 可重复读: REPEATABLE READ
    - InnoDB 的默认隔离级别 
    - 使用事务第一次读取时创建的快照 
    - 多版本技术
    锁:
    - 使用唯一索引的唯一查询条件时, 只锁定查找到的索引记录, 不锁定间隙。 
    - 其他查询条件, 会锁定扫描到的索引范围, 通过间隙锁或临键锁来阻止其他会话在这个
    范围中插入值。
    - 可能的问题: InnoDB 不能保证没有幻读, 需要加锁

---

    串行化: SERIALIZABLE
    最严格的级别，事务串行执行，资源消耗最大;

    问题回顾:
    - 脏读(dirty read) : 使用到从未被确认的数据(例如: 早期版本、回滚) 
    - 不可重复读: 不加锁的情况下, 其他事务 update 或 delete 会对结果集有影响 
    - 幻读(Phantom): 加锁之后, 相同的查询语句, 在不同的时间点执行时, 产生不同的结果集

    怎么解决?
    提高隔离级别、使用间隙锁或临键锁

---

    undo log: 撤消日志
    - 保证事务的原子性
    - 用处: 事务回滚, 一致性读、崩溃恢复。 
    - 记录事务回滚时所需的撤消操作
    - 一条 INSERT 语句，对应一条 DELETE 的 undo log
    - 每个 UPDATE 语句，对应一条相反 UPDATE 的 undo log

    保存位置:
    - system tablespace (MySQL 5.7默认)
    - undo tablespaces (MySQL 8.0默认)

    回滚段(rollback segment)

--- 

    redo log: 重做日志
    - 确保事务的持久性，防止事务提交后数据未刷新到磁盘就掉电或崩 溃。
    - 事务执行过程中写入 redo log,记录事务对数据页做了哪些修改。 
    - 提升性能: WAL(Write-Ahead Logging) 技术, 先写日志, 再写磁盘。
    - 日志文件: ib_logfile0, ib_logfile1 
    - 日志缓冲: innodb_log_buffer_size 
    - 强刷: fsync()

---

    MVCC: 多版本并发控制

    - 使 InnoDB 支持一致性读: READ COMMITTED 和 REPEATABLE READ 。 
    - 让查询不被阻塞、无需等待被其他事务持有的锁，这种技术手段可以增加并发性能。 
    - InnoDB 保留被修改行的旧版本。 
    - 查询正在被其他事务更新的数据时，会读取更新之前的版本。
    - 每行数据都存在一个版本号, 每次更新时都更新该版本 
    - 这种技术在数据库领域的使用并不普遍。 某些数据库, 以及某些 MySQL 存储引擎都不支持。
    聚簇索引的更新 = 替换更新 二级索引的更新 = 删除+新建

---

    MVCC 实现机制
    - 隐藏列
    - 事务链表， 保存还未提交的事务，事务提交则会从链表中摘除
    - Read view: 每个 SQL 一个, 包括 rw_trx_ids, low_limit_id, up_limit_id, low_limit_no 等 
    - 回滚段: 通过 undo log 动态构建旧版本数据

# 2.DB 与 SQL 优化

## 从一个简单例子讲起

```sql
SELECT f_id, f_username, f_gender, f_idno, f_age, f_created_at, f_updated_at 
FROM dbsql.t_user_info 
WHERE f_id < 10 
ORDER BY IF (f_id < 5, -f_id, f_id)
```

## 说说 SQL 优化

    如何发现需要优化的 SQL? 
    你了解的 SQL 优化方法有哪些? 
    SQL 优化有哪些好处?

## 总结1:写入优化

    大批量写入的优化 
    PreparedStatement 减少 SQL 解析 
    Multiple Values/Add Batch 减少交互 
    Load Data，直接导入
    索引和约束问题

## 总结2:数据更新

    数据的范围更新
    注意 GAP Lock 的问题
    导致锁范围扩大

## 总结3:模糊查询

    Like 的问题 
    前缀匹配 
    否则不走索引 
    全文检索， 
    solr/ES

## 总结4:连接查询

    连接查询优化
    驱动表的选择问题
    避免笛卡尔积

## 总结5:索引失效

    索引失效的情况汇总 NULL，not，not in，函数等
    减少使用 or，可以用 union(注意 union all 的区别)，以及前面提 到的like
    大数据量下，放弃所有条件组合都走索引的幻想，出门左拐“全文检索”
    必要时可以使用 force index 来强制查询走某个索引

## 总结6:查询 SQL 到底怎么设计?
    查询数据量和查询次数的平衡
    避免不必须的大量重复数据传输
    避免使用临时文件排序或临时表
    分析类需求，可以用汇总表

# 3.常见场景分析

## 怎么实现主键 ID
    - 自增
    - sequence
    - 模拟 seq
    - UUID
    - 时间戳/随机数 - snowflake

## 高效分页

    - 分页:count/pageSize/pageNum, 带条件的查询语句
    - 常见实现-分页插件:使用查询 SQL，嵌套一个 count，性能的坑? 
    - 改进一下1，重写 count
    - 大数量级分页的问题，limit 100000,20
    - 改进一下2，反序
    - 继续改进3，技术向:带 id，
    - 继续改进4，需求向:非精确分页
    - 所有条件组合? 索引?

## 乐观锁与悲观锁

    select * from xxx for update 
    update xxx
    commit;
    意味着什么?

    select * from xxx
    update xxx where value=oldValue 
    为什么叫乐观锁

# 4.总结回顾与作业实践

## 第13节课作业实践

    1、(选做)用今天课上学习的知识，分析自己系统的 SQL 和表结构
    2、(必做)按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。
    3、(选做)按自己设计的表结构，插入1000万订单模拟数据，测试不同方式的插入效率。
    4、(选做)使用不同的索引或组合，测试不同方式查询效率。
    5、(选做)调整测试数据，使得数据尽量均匀，模拟1年时间内的交易，计算一年的销售报 表:销售总额，订单数，客单价，每月销售量，前十的商品等等(可以自己设计更多指标)。
    6、(选做)尝试自己做一个 ID 生成器(可以模拟 Seq 或 Snowflake)。 7、(选做)尝试实现或改造一个非精确分页的程序。