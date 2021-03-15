package com.sq.dp.designpattern.composite;

public class Client {
    public static void main(String[] args) {
        // 数据初始化
        // ROOT
        ParentMenu parent1 = new ParentMenu(1L, "系统管理", "system");
        // ROOT 叶子节点
        Menu sub1 = new SubMenu(2L, "用户管理", "user", "/system/user");
        Menu sub2 = new SubMenu(3L, "部门管理", "dept", "/system/dept");
        // ROOT/树枝节点
        ParentMenu parent2 = new ParentMenu(4L, "访问控制", "system-security");
        // ROOT/树枝节点/叶子节点
        Menu sub3 = new SubMenu(5L, "菜单管理", "menu", "/system/menu");
        // ROOT/树枝节点/叶子节点
        Menu sub4 = new SubMenu(6L, "资源管理", "resource", "/system/resource");

        parent1.add(sub1);
        parent1.add(sub2);
        parent1.add(parent2);
        parent2.add(sub3);
        parent2.add(sub4);

        // 透明模式为用户提供一致性访问, 但是降低了程序安全性
        // 由于调用叶子节点时, 方法是空实现/抛异常, 所以有安全隐患
        parent1.show(); // 整体
        parent2.show(); // 部分
        sub1.show(); // 部分
    }
}
