package com.objectoriented;

import java.util.List;

/**
 * Created by zhushuangquan on 19/01/2018.
 */
public class Employee {

    static List<Employee> allEmployees;
    String name;
    int salary;
    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    public Employee(String name) {
        this(name, 0);
    }
    void doWork() {

    }
    void getPaid(BankEndPoint bank) {
        bank.payment(name, salary);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + salary;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee)obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (salary != other.salary) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    static void loadAllEmployees() {
        // Load all employees from database.
    }
}
