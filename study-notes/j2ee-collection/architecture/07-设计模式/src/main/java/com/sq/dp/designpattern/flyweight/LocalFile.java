package com.sq.dp.designpattern.flyweight;

/**
 * 本地文件
 */
public class LocalFile {
    private String name;
    private String size;

    public LocalFile(String name, String size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
