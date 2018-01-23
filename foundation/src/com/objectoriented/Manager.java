package com.objectoriented;

import java.util.List;

/**
 * Created by zhushuangquan on 23/01/2018.
 */
public class Manager extends Employee {

    List<Employee> reporters;

    public Manager(String name, int salary) {
        super(name, salary);
    }

    @Override
    void getPaid(BankEndPoint bank) {
        super.getPaid(bank);
        getStocks();
    }

    @Override
    void doWork() {
        Employee worker = selectReporter();
        worker.doWork();
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    private Employee selectReporter() {
        return null;
    }

    private void getStocks() {

    }
}
