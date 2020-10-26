SELECT *
FROM employees
WHERE first_name = 'Parto';

-- 性能分析命令 sql语句的执行计划
-- EXPLAIN 只能用于分析select语句的性能
EXPLAIN
SELECT *
FROM employees
WHERE first_name = 'Parto';

-- 开启Profile命令
SELECT @@profiling;
SET profiling = 1;

SELECT *
FROM employees; -- 3s
SHOW PROFILES;
-- 优化之前和优化之后的时间对比 3s, 0.1s

-- select_type UNION
EXPLAIN
SELECT *
FROM employees
WHERE emp_no = '10001'
UNION
SELECT *
FROM employees
WHERE emp_no = '10002';

-- select_type = SUBQUERY
EXPLAIN
SELECT *
FROM employees
WHERE emp_no IN
      (
          SELECT emp_no
          FROM employees
          WHERE emp_no = '10001'
          UNION
          SELECT emp_no
          FROM employees
          WHERE emp_no = '10002'
      );

-- type = eq_ref
EXPLAIN
SELECT *
FROM employees a,
     employees b
WHERE a.emp_no = b.emp_no;

-- type = index_merge
EXPLAIN
SELECT *
FROM dept_manager
WHERE emp_no = 110022
   OR dept_no = 'd001';

-- type = index
EXPLAIN
SELECT COUNT(*)
FROM employees;

