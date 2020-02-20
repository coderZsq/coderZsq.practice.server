-- 笛卡尔乘积
SELECT * FROM emp;
SELECT * FROM dept;
SELECT * FROM emp, dept;

-- 隐式连接 内连接
-- 需求: 查询员工编号, 员工名称, 员工所属部门的编号和名称
SELECT * FROM emp, dept WHERE emp.DEPTNO = dept.DEPTNO;
SELECT e.ENAME, e.SAL, d.DNAME FROM emp e, dept d WHERE e.DEPTNO = d.DEPTNO;
-- 查询员工的姓名, 工资, 所在部门的名称, 以及工资的等级
SELECT e.ENAME, e.SAL, d.DNAME, sg.GRADE FROM emp e, dept d, salgrade sg WHERE e.DEPTNO = d.DEPTNO AND e.SAL BETWEEN sg.LOSAL AND sg.HISAL;

-- 显式连接 内连接
-- 需求: 查询员工编号, 员工名称, 员工所属部门的编号和名称
SELECT e.ENAME, e.SAL, d.DNAME FROM emp e INNER JOIN dept d ON e.DEPTNO = d.DEPTNO;
SELECT e.ENAME, e.SAL, d.DNAME FROM emp e JOIN dept d ON e.DEPTNO = d.DEPTNO;
SELECT e.ENAME, e.SAL, d.DNAME FROM emp e JOIN dept d USING (DEPTNO);
-- 查询员工的姓名, 工资, 所在部门的名称, 以及工资的等级
SELECT e.ENAME, e.SAL, d.DNAME, SG.GRADE FROM emp e JOIN dept d ON e.DEPTNO = d.DEPTNO JOIN salgrade sg ON e.SAL BETWEEN sg.LOSAL AND sg.HISAL;

-- 显式连接 外连接
SELECT e.ENAME, e.SAL, d.DNAME FROM emp e LEFT JOIN dept d ON e.DEPTNO = d.DEPTNO;
SELECT e.ENAME, e.SAL, d.DNAME FROM dept d RIGHT JOIN emp e ON e.DEPTNO = d.DEPTNO;
-- 需求: 查询每一个部门的总人数
SELECT d.DNAME, COUNT(e.EMPNO) FROM emp e JOIN dept d ON e.DEPTNO = d.DEPTNO GROUP BY DNAME;
SELECT d.DNAME, COUNT(e.EMPNO) FROM emp e RIGHT JOIN dept d ON e.DEPTNO = d.DEPTNO GROUP BY DNAME;
-- 需求: 查询出至少有一个员工的所有部门编号, 名称, 并统计出这些部门的平均工资, 最低工资, 最高工资
SELECT d.DEPTNO, d.DNAME, AVG(e.SAL), MIN(e.SAL), MAX(e.SAL) FROM emp e JOIN dept d ON e.DEPTNO = d.DEPTNO GROUP BY d.DEPTNO, d.DNAME;
SELECT d.DEPTNO, d.DNAME, AVG(e.SAL), MIN(e.SAL), MAX(e.SAL) FROM emp e RIGHT JOIN dept d ON e.DEPTNO = d.DEPTNO GROUP BY d.DEPTNO, d.DNAME;

-- 显式连接 全外连接
SELECT e.ENAME, d.DNAME FROM emp e LEFT JOIN dept d ON e.DEPTNO = d.DEPTNO;
SELECT e.ENAME, d.DNAME FROM emp e RIGHT JOIN dept d ON e.DEPTNO = d.DEPTNO;
SELECT e.ENAME, d.DNAME FROM emp e LEFT JOIN dept d ON e.DEPTNO = d.DEPTNO UNION SELECT e.ENAME, d.DNAME FROM emp e RIGHT JOIN dept d ON e.DEPTNO = d.DEPTNO;

-- 显式连接 自连接
-- 需求: 查询员工名称和其对应主管的名称
SELECT e.ENAME, m.ENAME FROM emp e JOIN emp m ON e.MGR = m.EMPNO;