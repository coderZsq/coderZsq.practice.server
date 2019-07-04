package com.coderZsq.example.query;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class PageResult {
    private List result; // 当前页的结果集: SQL查询
    private int totalCount; // 总数据条数: SQL查询

    private int currentPage = 1; // 当前页码: 用户传入
    private int pageSize = 3; // 每页最多显示条数: 用户传入

    private int prevPage; // 上一页: 计算出来
    private int nextPage; // 下一页: 计算出来
    private int totalPage; // 总一页: 计算出来

    public static PageResult empty(int pageSize) {
        return new PageResult(Collections.emptyList(), 0, 1, pageSize);
    }

    public PageResult(List result, int totalCount, int currentPage, int pageSize) {
        this.result = result;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        prevPage = currentPage - 1 >= 1 ? currentPage -1 : 1;
        nextPage = currentPage + 1 <= totalPage ? currentPage + 1 : totalPage;
    }
}
