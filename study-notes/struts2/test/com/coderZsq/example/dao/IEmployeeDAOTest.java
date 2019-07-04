package com.coderZsq.example.dao;

import com.coderZsq.example.dao.impl.EmployeeDAOImpl;
import com.coderZsq.example.domain.Employee;
import com.coderZsq.example.query.EmployeeQueryObject;
import com.coderZsq.example.query.PageResult;
import com.coderZsq.example.util.MD5;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IEmployeeDAOTest {
    private IEmployeeDAO dao = new EmployeeDAOImpl();

    @Test
    public void testMD5() {
        String str = MD5.encode("1");
        System.out.println("str = " + str);
    }

    @Test
    public void query() throws Exception {
        EmployeeQueryObject queryObject = new EmployeeQueryObject();
        queryObject.setCurrentPage(4);
        queryObject.setPageSize(5);
        PageResult pageResult = dao.query(queryObject);
        System.out.println("totalCount = " + pageResult.getTotalCount());
        for (Object employee: pageResult.getResult()) {
            System.out.println(employee);
        }
    }

    @Test
    public void query1() throws Exception {
        EmployeeQueryObject queryObject = new EmployeeQueryObject();
        queryObject.setName("Castie!");
        queryObject.setMinSalary(new BigDecimal("1000"));
        queryObject.setMaxSalary(new BigDecimal("1500"));
        List<Employee> list = dao.query1(queryObject);
        for (Employee employee: list) {
            System.out.println(employee);
        }
    }

    @Test
    public void save() throws Exception {
        Employee employee = new Employee();
        for (int i = 1; i < 35; i++) {
            employee.setName("Castie!" + i);
            employee.setPassword("sha-256");
            employee.setSalary(new BigDecimal(1000 + ThreadLocalRandom.current().nextInt(0, 1200)));
            dao.save(employee);
        }
    }

    @Test
    public void update() throws Exception {
        Employee employee = new Employee();
        employee.setName("coderZsq");
        employee.setPassword("ssh-keygen");
        employee.setSalary(new BigDecimal("0"));
        employee.setId(1L);
        dao.update(employee);
    }

    @Test
    public void delete() throws Exception {
        Employee employee = new Employee();
        employee.setId(2L);
        dao.delete(employee);
    }

    @Test
    public void get() throws Exception {
        Employee employee = dao.get(1L);
        System.out.println("employee = " + employee);
    }

    @Test
    public void listAll() throws Exception {
        List<Employee> list = dao.listAll();
        for (Employee employee : list) {
            System.out.println("employee = " + employee);
        }
    }
}