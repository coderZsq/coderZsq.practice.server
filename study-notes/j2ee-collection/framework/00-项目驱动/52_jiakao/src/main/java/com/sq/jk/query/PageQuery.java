package com.sq.jk.query;

import lombok.Data;

import java.util.List;

@Data
public class PageQuery<T> {
    private static final int DEFAULT_SIZE = 10;

    private long page;
    private long size;

    /**
     * 总记录数
     */
    private long count;
    /**
     * 总页数
     */
    private long pages;

    private List<T> data;

    public long getPage() {
        return Math.max(1, page);
    }

    public long getSize() {
        return (size < 1) ? DEFAULT_SIZE : size;
    }
}
