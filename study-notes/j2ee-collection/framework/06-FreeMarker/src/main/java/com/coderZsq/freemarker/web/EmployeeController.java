package com.coderZsq.freemarker.web;

import com.coderZsq.freemarker.domain.Department;
import com.coderZsq.freemarker.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private static List<Department> departments = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static Map<Long, Employee> ID_EMP_MAP = new HashMap<>();

    static {
        Department d1 = new Department(1L, "总经办", "General Deparment");
        Department d2 = new Department(2L, "人力资源部", "Human Resources Department");
        Department d3 = new Department(3L, "采购部", "Order Department");
        Department d4 = new Department(4L, "仓储部", "Technolog Department ");
        Department d5 = new Department(5L, "技术部", "Technolog Department ");
        Employee e1 = new Employee(1L, "admin", "1", "test@qq.com", 21, true, d1);
        Employee e2 = new Employee(2L, "赵一明", "1", "test@qq.com", 22, false, d2);
        Employee e3 = new Employee(3L, "钱总", "1", "test@qq.com", 23, false, d3);
        Employee e4 = new Employee(4L, "孙总", "1", "test@qq.com", 24, false, d4);
        Employee e5 = new Employee(5L, "周总", "1", "test@qq.com", 25, false, d5);

        departments.add(d1);
        departments.add(d2);
        departments.add(d3);
        departments.add(d4);
        departments.add(d5);

        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);

        ID_EMP_MAP.put(e1.getId(), e1);
        ID_EMP_MAP.put(e2.getId(), e2);
        ID_EMP_MAP.put(e3.getId(), e3);
        ID_EMP_MAP.put(e4.getId(), e4);
        ID_EMP_MAP.put(e5.getId(), e5);
    }

    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("emps", employees);
        model.addAttribute("depts", departments);
        return "employee/list"; // 视图名称
    }

    @RequestMapping("input")
    public String input(Model model, Long id) {
        if (id != null) { // 编辑
            model.addAttribute("emp", ID_EMP_MAP.get(id));
        }
        model.addAttribute("depts", departments);
        return "employee/input"; // 视图名称
    }
}
