SELECT COUNT(*) -- 443308
FROM employees.titles;

SELECT COUNT(DISTINCT (title)) -- 7
FROM employees.titles;

-- 数据区分度: 90%以上适合创建索引
SELECT COUNT(DISTINCT (title)) / COUNT(*) AS Selectivity -- 0.0000
FROM employees.titles;

-- 考虑创建索引
EXPLAIN
SELECT *
FROM employees.employees
WHERE first_name = 'Eric'
  AND last_name = 'Anido';
-- 1 根据first_name
SELECT COUNT(DISTINCT (first_name)) / COUNT(*) -- 0.0042
FROM employees.employees;
-- 2 创建last_name
SELECT COUNT(DISTINCT (last_name)) / COUNT(*) -- 0.0055
FROM employees.employees;
-- 3 创建 联合索引 first_name + last_name
SELECT COUNT(DISTINCT (CONCAT(first_name, last_name))) / COUNT(*) AS Selectivity -- 0.9313 key_len 34
FROM employees.employees;
SELECT COUNT(DISTINCT (CONCAT(first_name, LEFT(last_name, 4)))) / COUNT(*) AS Selectivity -- 0.9007 key_len 22
FROM employees.employees;