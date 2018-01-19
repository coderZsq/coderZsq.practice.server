package com.objectoriented;

/**
 * Created by zhushuangquan on 19/01/2018.
 */
public class Accounting {
    BankEndPoint bank;

    void payAll() {
        Employee.loadAllEmployees();
        for (Employee employee: Employee.allEmployees) {
            employee.getPaid(bank);
        }
    }
}
