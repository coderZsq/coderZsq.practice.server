package com.coderZsq.freemarker.web;

import com.coderZsq.freemarker.domain.Department;
import com.coderZsq.freemarker.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class HelloController {
    /**
     * 共享数据:
     * 1 使用Model对象 类似于Map
     * 2 使用@ModelAttribute
     */
    @RequestMapping("hello")
    // public String hello(Model model) {
    public String hello(@ModelAttribute("user") String user, @ModelAttribute("url") String url, @ModelAttribute("name") String name) {
        System.out.println("HelloController.hello");
        // 1 找到对应的视图 ftl文件
        // 2 整合数据和ftl中的占位符
        // model.addAttribute("user", "test");
        // model.addAttribute("url", "http://www.baidu.com");
        // model.addAttribute("name", "拖拉机");
        return "hello"; // 视图名称
    }

    @RequestMapping("list")
    public String list(Model model) {
        System.out.println("HelloController.list");
        List<String> list = Arrays.asList("a", "b", "d", "c");
        model.addAttribute("names", list);
        return "list"; // 视图名称
    }

    @RequestMapping("map")
    public String map(Model model) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "小吴");
        hashMap.put("age", "18");
        hashMap.put("hobby", "篮球");
        model.addAttribute("map", hashMap);
        return "map"; // 视图名称
    }

    @RequestMapping("value")
    public String value(Model model) {
        Department dept = new Department();
        dept.setId(1L);
        dept.setName("人事部");
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("admin");
        employee.setDept(dept);
        model.addAttribute("emp", employee);
        return "value"; // 视图名称
    }
}
