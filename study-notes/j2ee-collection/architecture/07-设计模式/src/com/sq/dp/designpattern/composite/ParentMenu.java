package com.sq.dp.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现角色(中间节点): 父菜单
 */
public class ParentMenu implements Menu {
    private Long id;
    private String name;
    private String icon;
    private String url;
    private boolean parent = true;
    private List<Menu> children = new ArrayList<>();

    public ParentMenu(Long id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public void add(Menu menu) {
        children.add(menu);
    }

    public void remove(Menu menu) {
        children.remove(menu);
    }

    public Menu getChild(int index) {
        return children.get(index);
    }

    @Override
    public void show() {
        System.out.println("父菜单: id=" + id + " name" + name + " icon=" + icon + " url=" + url + "parent=" + parent);
        System.out.println("------- 子菜单数量: " + children.size() + "-------");
        System.out.println("------- 正在展示子菜单 ------");
        for (Menu menu: children) {
            menu.show();
        }
        System.out.println("------- 子菜单展示结束 ------");
    }
}
