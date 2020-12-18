package com.sq.jk.pojo.query;

import lombok.Data;

import java.util.List;

@Data
public class PageQuery {
    private static final int DEFAULT_SIZE = 10;
    private long page;
    private long size;
    /**
     * 当前这一页的数据
     */
    private List<?> data;
    /**
     * 总数
     */
    private long count;
    /**
     * 总页数
     */
    private long pages;

    public long getPage() {
        return Math.max(page, 1);
    }

    public long getSize() {
        return size < 1 ? DEFAULT_SIZE : size;
    }
}
