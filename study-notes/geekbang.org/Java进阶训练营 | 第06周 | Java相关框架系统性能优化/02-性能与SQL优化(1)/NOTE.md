# 1. 再聊聊性能优化

## 复习一下什么是性能

- 吞吐与延迟 : 有些结论是反直觉的，指导我们关注什么
- 没有量化就没有改进:监控与度量指标，指导我们怎么去入手 
- 80/20原则:先优化性能瓶颈问题，指导我们如何去优化
- 过早的优化是万恶之源:指导我们要选择优化的时机
- 脱离场景谈性能都是耍流氓:指导我们对性能要求要符合实际

性能是一个综合性问题
 
## DB/SQL 优化是业务系统性能优化的核心 

业务系统的分类:计算密集型、数据密集型
业务处理本身无状态，数据状态最终要保存到数据库 
一般来说，DB/SQL 操作的消耗在一次处理中占比最大 
业务系统发展的不同阶段和时期，性能瓶颈要点不同，类似木桶装水

例如传统软件改成 SaaS 软件
 
# 2. 关系数据库 MySQL*

## 什么是关系数据库

- 1970年 Codd 提出关系模型，以关系代数理论为数学基础
《A Relational Model of Data for Large Shared Data Banks》

## 什么是关系数据库

数据库设计范式
第一范式(1NF):关系 R 属于第一范式，当且仅当R中的每一个属性A的值域只包含原 子项
第二范式(2NF):在满足 1NF 的基础上，消除非主属性对码的部分函数依赖 
第三范式(3NF):在满足 2NF 的基础上，消除非主属性对码的传递函数依赖
BC 范式(BCNF):在满足 3NF 的基础上，消除主属性对码的部分和传递函数依赖 
第四范式(4NF):消除非平凡的多值依赖 
第五范式(5NF):消除一些不合适的连接依赖

---

数据库设计范式 
1NF:消除重复数据，即每一列都是不可再分的基本数据项; 
每个列都是原子的。

---

数据库设计范式 
2NF:消除部分依赖，表中没有列只与主键的部分相关，即每一行都被主键唯一标识; 
每个列都有主键。

---

数据库设计范式
3NF:消除传递依赖，消除表中列不依赖主键，而是依赖表中的非主键列的情况，即没 有列是与主键不相关的。
从表只引用主表的主键，
即表中每列都和主键相关。

---

数据库设计范式

BCNF:Boyce-Codd Normal Form(巴斯-科德范式) 
3NF 的基础上消除主属性对于码的部分与传递函数依赖。

---

## 常见关系数据库

常见关系数据库 

开源:MySQL、PostgreSQL
商业:Oracle，DB2，SQL Server

内存数据库:Redis?，VoltDB
图数据库:Neo4j，Nebula
时序数据库:InfluxDB、openTSDB 
其他关系数据库:Access、Sqlite、H2、Derby、Sybase、Infomix 等
NoSQL 数据库:MongoDB、Hbase、Cassandra、CouchDB
NewSQL/ 分布式数据库:TiDB、CockroachDB、NuoDB、OpenGauss、OB、TDSQL

## SQL 语言

SQL 语言1974年由 Boyce 和 Chamberlin 提出，并首先在 IBM 公司研制的关系数据库
系统 SystemR 上实现。
1979年 ORACLE 公司首先提供商用的 SQL，IBM 公司在 DB2和SQL/DS 数据库系统中
也实现了 SQL。
1986年10月，美国 ANSI 采用 SQL 作为关系数据库管理系统的标准语言(ANSI X3.
135-1986)，后为国际标准化组织(ISO)采纳为国际标准。
1989年，美国 ANSI 采纳在 ANSI X3.135-1989 报告中定义的关系数据库管理系统的
SQL 标准语言，称为 ANSI SQL 89，该标准替代 ANSI X3.135-1986版本。

---

结构化查询语言包含 6 个部分:
1、数据查询语言(DQL: Data Query Language):其语句，也称为“数据检索语句”，用以从表 中获得数据，确定数据怎样在应用程序给出。保留字 SELECT 是 DQL(也是所有 SQL)用得最多 的动词，其他 DQL 常用的保留字有 WHERE，ORDER BY，GROUP BY 和 HAVING。这些 DQL 保 留字常与其它类型的 SQL 语句一起使用。
2、数据操作语言(DML:Data Manipulation Language):其语句包括动词 INSERT、 UPDATE 和 DELETE。它们分别用于添加、修改和删除。
3、事务控制语言(TCL):它的语句能确保被 DML 语句影响的表的所有行及时得以更新。包括 COMMIT(提交)命令、SAVEPOINT(保存点)命令、ROLLBACK(回滚)命令。
4、数据控制语言(DCL):它的语句通过 GRANT 或 REVOKE 实现权限控制，确定单个用户和用 户组对数据库对象的访问。某些 RDBMS 可用 GRANT 或 REVOKE 控制对表单个列的访问。
5、数据定义语言(DDL):其语句包括动词 CREATE,ALTER 和 DROP。在数据库中创建新表或修 改、删除表(CREAT TABLE 或 DROP TABLE);为表加入索引等。
6、指针控制语言(CCL):它的语句，像 DECLARE CURSOR，FETCH INTO 和 UPDATE WHERE CURRENT 用于对一个或多个表单独行的操作。

---

SQL 的各个版本:
1986年，ANSI X3.135-1986，ISO/IEC 9075:1986，SQL-86 1989年，ANSI X3.135-1989，ISO/IEC 9075:1989，SQL-89 1992年，ANSI X3.135-1992，ISO/IEC 9075:1992，SQL-92(SQL2) 1999年，ISO/IEC 9075:1999，SQL:1999(SQL3)
2003年，ISO/IEC 9075:2003，SQL:2003
2008年，ISO/IEC 9075:2008，SQL:2008
2011年，ISO/IEC 9075:2011，SQL:2011

## MySQL 数据库

瑞典的 MySQL AB 创立于1995年。

2008年1月16日 MySQL AB被 Sun Microsystems 收购。 

2009年4月20日，甲骨文(Oracle)收购 Sun Microsystems 公司。 

其后分离成两个版本:MariaDB 和 MySQL

---

MySQL 的版本

- 4.0支持 InnoDB，事务
- 2003年，5.0
- 5.6 ==> 历史使用最多的版本
- 5.7 ==> 近期使用最多的版本
- 8.0 ==>最新和功能完善的版本

---

- 5.6/5.7的差异

5.7支持:
- 多主
- MGR 高可用 - 分区表
- json
- 性能
- 修复 XA 等

---

- 5.7/8.0的差异

- 通用表达式
- 窗口函数
- 持久化参数
- 自增列持久化
- 默认编码 utf8mb4 - DDL 原子性
- JSON 增强
- 不再对 group by 进行隐式排序??==> 坑

# 3.深入数据库原理*

## MySQL 存储

独占模式
1)、日志组文件:ib_logfile0和ib_logfile1，默认均为5M 
2)、表结构文件:*.frm
3)、独占表空间文件:*.ibd
4)、字符集和排序规则文件:db.opt
5)、binlog 二进制日志文件:记录主数据库服务器的 DDL 和 DML 操作 
6)、二进制日志索引文件:master-bin.index
共享模式 innodb_file_per_table=1
1)、数据都在 ibdata1

## MySQL 对 SQL 执行顺序

实际上这个过程也并不是绝对这样的， 中间 mysql 会有部分的优化以达到最佳 的优化效果，比如在 select 筛选出找到 的数据集

## MySQL 索引原理

数据是按页来分块的，当一个数据被用
到时，其附近的数据也通常会马上被使
用。

InnoDB 使用 B+ 树实现聚集索引，

 为什么一般单表数据不超过2000万?

## MySQL 数据库操作演示 操作示例

- 安装的几种方式，安装文件或命令，docker
- 操作工具，mysql-cli 或 IDE(DataGrip,MySQL-WorkBench,MySQL-Front,Navicat 等) 
- MySQL 库结构，操作语句与命令
- MySQL SQL 语法演示

5.6/5.7/8.0
 
## 4. MySQL 配置优化*

## 参数配置优化-1

1)连接请求的变量 
1、max_connections
2、back_log 
3、wait_timeout和interative_timeout

## 参数配置优化

查看参数配置
- show variables like xxx my.cnf 文件

## 参数配置优化-2

2)缓冲区变量
4、key_buffer_size 
5、query_cache_size(查询缓存简称 QC) 
6、max_connect_errors: 
7、sort_buffer_size: 
8、max_allowed_packet=32M 
9、join_buffer_size=2M 
10、thread_cache_size=300

## 参数配置优化-3

3)配置 Innodb 的几个变量 
11、innodb_buffer_pool_size 
12、innodb_flush_log_at_trx_commit 
13、innodb_thread_concurrency=0 
14、innodb_log_buffer_size 
15、innodb_log_file_size=50M 
16、innodb_log_files_in_group=3 
17、read_buffer_size=1M 
18、read_rnd_buffer_size=16M 
19、bulk_insert_buffer_size=64M 
20、binary log

# 5.数据库设计优化*

## MySQL 数据库设计优化-最佳实践

- 如何恰当选择引擎?
- 库表如何命名?
- 如何合理拆分宽表?
- 如何选择恰当数据类型:明确、尽量小
- char、varchar 的选择
- (text/blob/clob)的使用问题? - 文件、图片是否要存入到数据库? - 时间日期的存储问题?
- 数值的精度问题?
- 是否使用外键、触发器?

 还有哪些常见问题?

## MySQL 数据库设计优化

- 唯一约束和索引的关系?
- 是否可以冗余字段?
- 是否使用游标、变量、视图、自定义函数、存储过程? - 自增主键的使用问题?
- 能够在线修改表结构(DDL 操作)?
- 逻辑删除还是物理删除?
- 要不要加 create_time,update_time 时间戳?
- 数据库碎片问题?
- 如何快速导入导出、备份数据?

性能是一个综合性问题
 
# 6.总结回顾与作业实践

## 第12节课作业实践 

1、(选做):基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统
进行数据库设计或是数据库服务器方面的优化。 
2、(必做):基于电商交易场景(用户、商品、订单)，设计一套简单的表结构，提交
DDL 的 SQL 文件到 Github(后面2周的作业依然要是用到这个表结构)。 
3、(选做):尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的
SQL 测试简单的增删改查。
4、(选做):基于上一题，尝试对各个数据库测试100万订单数据的增删改查性能。
5、(选做):尝试对 MySQL 不同引擎下测试100万订单数据的增删改查性能。
6、(选做):模拟1000万订单数据，测试不同方式下导入导出(数据备份还原) MySQL 的速度，包括 jdbc 程序处理和命令行处理。思考和实践，如何提升处理效率。
7、(选做):对 MySQL 配置不同的数据库连接池(DBCP、C3P0、Druid、Hikari)， 测试增删改查100万次，对比性能，生成报告。