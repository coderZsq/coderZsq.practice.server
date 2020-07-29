package com.coderZsq.test;

import com.coderZsq.bean.Customer;
import com.coderZsq.dao.CustomerDao;
import org.junit.*;

import java.util.List;

public class CustomerDaoTest {
    private static CustomerDao dao;

    @Before
    public void before() {
        System.out.println("before");
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @BeforeClass
    public static void beforeClass() {
        dao = new CustomerDao();
        System.out.println("beforeClass");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");
    }

    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setName("张武");
        customer.setRealAge(20);
        customer.setHeight(1.89);

        Assert.assertTrue(dao.save(customer));
    }

    @Test
    public void testList() {
        List<Customer> customers = dao.list();
        Assert.assertTrue(customers.size() > 0);
    }
}
