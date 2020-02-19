-- 多行函数
-- 1. 查询所有员工每个月支付的平均工资及总工资
SELECT SUM(SAL) FROM emp;
SELECT SUM(SAL) / COUNT(EMPNO) FROM emp;
SELECT SUM(SAL), AVG(SAL) FROM emp;
-- 2. 查询月薪在2000以上的员工总人数
SELECT COUNT(EMPNO) FROM emp;
SELECT COUNT(EMPNO) FROM emp WHERE SAL >= 2000;
SELECT COUNT(*) FROM emp;
SELECT COUNT(1) FROM emp;
SELECT COUNT(EMPNO) FROM emp;
SELECT COUNT(COMM) FROM emp;
-- 3. 查询员工最高工资和最低工资差距
SELECT MAX(SAL), MIN(SAL) FROM emp;
SELECT MAX(SAL) - MIN(SAL) FROM emp;

-- 单行函数
-- 1. 把HelloWorld转换为全大写, 全小写
SELECT LOWER(ENAME) FROM emp;
SELECT UPPER(LOWER(ENAME)) FROM emp;
SELECT UPPER('HelloWorld');
SELECT UPPER('HelloWorld') FROM dual;
SELECT UPPER('HelloWorld'), LOWER('HelloWorld') FROM dual;
-- 2. 查询所有员工的全名
-- 3. 查询得到输出结果: 某某's的月薪是: xx
