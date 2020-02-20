-- 全列 / 投影查询
-- 1. 查询所有员工信息
SELECT * FROM emp;
-- 2. 查询每个员工的编号, 姓名, 职位
SELECT EMPNO, ENAME, JOB FROM emp;
-- 3. 查询所有部门信息
DESC dept;
SELECT DEPTNO, DNAME, LOC FROM dept;

-- 消除重复
-- DISTINCT关键字可以用于一列, 也可以用于多列
-- 1. 查询所有有有员工的部门编号
SELECT DEPTNO FROM emp;
SELECT DISTINCT DEPTNO FROM emp;
-- 2. 查询哪些部门和职位有员工
SELECT DEPTNO, JOB FROM emp;
SELECT DISTINCT DEPTNO, JOB FROM emp;

-- 算数运算符
-- 1. 查询所有员工的年薪
SELECT SAL, SAL * 12 FROM emp;
-- 2. 查询所有员工的年薪(使用别名)
SELECT SAL, SAL * 12 year_income FROM emp;
-- 3. 查询每月都有500元的餐补和200元交通补助并且年底多发一个月工资的年薪
SELECT (SAL + 500 + 200) * (12 + 1) year_income FROM emp;
-- 4. 演示date类型数据的运算: 查询员工的雇佣日期加上10
SELECT HIREDATE, HIREDATE + 10 FROM emp;

-- 空值判断
-- 1. 查询所有员工的年终奖((月薪 + 奖金) * 12)
SELECT SAL, COMM, (SAL + COMM) * 12 FROM  emp;
SELECT SAL, IFNULL(COMM, 0), (SAL + COMM) * 12 FROM emp;
SELECT SAL, IFNULL(COMM, 0), (SAL + IFNULL(COMM, 0)) * 12 FROM emp;
-- 2. 查询有奖金的员工信息
SELECT * FROM emp WHERE COMM IS NULL;
SELECT * FROM emp WHERE COMM IS NOT NULL;

-- 限定查询
-- 1. 要求查询出基本工资高于1500的所有员工信息
SELECT * FROM emp WHERE SAL >= 1500;
-- 2. 查询名字叫SCOTT的员工所从事的工作
SELECT * FROM emp WHERE ENAME = 'scott';
SELECT * FROM emp WHERE BINARY ENAME = 'scott';

-- 比较运算符
-- 使用比较运算符
-- 1. 查询1981年之后入职的员工信息
SELECT * FROM emp WHERE HIREDATE >= '1982-01-01';
-- 2. 查询年薪小于3W的员工
SELECT *, (SAL * 12) FROM emp WHERE (SAL * 12) < 30000;
-- 3. 查询所有不是销售人员的员工信息
SELECT * FROM emp WHERE JOB != 'SALESMAN';
-- 使用BETWEEN运算符
-- 1. 查询工资在2000-3000之间的员工信息
SELECT * FROM emp WHERE SAL >= 2000 AND SAL <= 3000;
SELECT * FROM emp WHERE SAL BETWEEN 2000 AND 3000;
-- 2. 查询工资不在2000-3000之间的员工信息
SELECT * FROM emp WHERE SAL NOT BETWEEN 2000 AND 3000;
-- 3. 查询1981年入职的员工
SELECT * FROM emp WHERE HIREDATE BETWEEN '1981-01-01' AND '1981-12-31';
-- 使用IN运算符
-- 1. 查询工资为800或1600或3000的员工
SELECT * FROM emp WHERE SAL = 800 OR SAL = 1600 OR SAL = 3000;
SELECT * FROM emp WHERE SAL IN (800, 1600, 3000);
-- 2. 查询工资不为800或1600或3000的员工
SELECT * FROM emp WHERE SAL NOT IN (800, 1600, 3000);
-- 使用LIKE运算符
-- 1. 查询出所有雇员姓名以A开头的全部雇员信息
SELECT * FROM emp WHERE ENAME LIKE 'A%';
-- 2. 查询出雇员姓名第二个字母是M的全部雇员信息
SELECT * FROM emp WHERE ENAME LIKE '_M%';
-- 3. 查询出雇员姓名任意位置上包含字母A的全部雇员信息
SELECT * FROM emp WHERE ENAME LIKE '%A%';

-- 逻辑运算符
-- 1. 查询姓名中有e或者a的员工姓名
SELECT * FROM emp WHERE ENAME LIKE '%e%' OR ENAME LIKE '%a%';
-- 2. 查询工资在1500-3000之间的全部员工信息
SELECT * FROM emp WHERE SAL >= 1500 AND SAL <= 3000;
-- 3. 查询出职位是办事员(CLERK)或者是销售人员(SALESMAN)的全部信息, 并且工资在1000以上
SELECT * FROM emp WHERE (JOB = 'clerk' OR JOB = 'salesman') AND SAL >= 1000;

-- 结果排序
-- 1. 查询所有员工信息, 按照工资排序
SELECT SAL FROM emp ORDER BY SAL;
SELECT SAL FROM emp ORDER BY SAL ASC;
SELECT SAL FROM emp ORDER BY SAL DESC;
-- 2. 查询所有员工信息, 按照年薪降序排序
SELECT (SAL * 12) AS income FROM emp ORDER BY income DESC;
-- 3. 查询所有员工信息, 按照部门和年薪降序排序
SELECT DEPTNO, (SAL * 12) income FROM emp ORDER BY DEPTNO, income;
SELECT DEPTNO, (SAL * 12) income FROM emp ORDER BY DEPTNO ASC, income DESC ;