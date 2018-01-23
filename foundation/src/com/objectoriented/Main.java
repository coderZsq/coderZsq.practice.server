package com.objectoriented;

import java.util.LinkedList;

/**
 * Created by zhushuangquan on 19/01/2018.
 */
public class Main {

    public static void main(String[] args) {
        Employee employee1 = new Employee("John", 10000);
        Employee employee2 = new Employee("Mary", 20000);
        Employee employee3 = new Employee("John");
        employee3.salary = 10000;

        System.out.println("employee1 == employee3 ? " + (employee1 == employee3));
        System.out.println("employee1.equals(employee3) ? " + employee1.equals(employee3));
        System.out.println("employee2.equals(employee3) ? " + employee2.equals(employee3));

        System.out.println(employee2);

        LinkedList<Employee> employees = new LinkedList<Employee>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        System.out.println("Print using for each");
        for (Employee employee: employees) {
            System.out.println(employee);
        }

        com.objectoriented.LinkedList list = new com.objectoriented.LinkedList();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        for (Integer value: list) {
            System.out.println(value);
        }

        Employee manager = new Manager("Tony", 100000);
        employees.add(manager);
        for (Employee employee: employees) {
            System.out.println(employee);
        }

    }
}
