package com.sq.dp.designpattern.composite;

/**
 * 叶子节点: 子菜单
 */
public class SubMenu implements Menu {
    private Long id;
    private String name;
    private String icon;
    private String url;
    private boolean parent = false;

    public SubMenu(Long id, String name, String icon, String url) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.url = url;
    }

    @Override
    public void show() {
        System.out.println("子菜单: id=" + id + " name=" + name + " icon=" + icon + " url=" + url + " parent=" + parent);
    }
}
