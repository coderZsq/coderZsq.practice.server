package com.hesj.rbac.qo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResult<T> {
    private int currentPage;
    private int pageSize;

    private int totalCount;
    private List<T> list;

    private int totalPage;
    private int prevPage;
    private int nextPage;

    public PageResult(int currentPage, int pageSize, int totalCount, List<T> list) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.list = list;

        if(totalCount <= pageSize){
            this.prevPage = 1;
            this.nextPage = 1;
            this.totalPage = 1;
            return;
        }

        this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
        this.prevPage = this.currentPage - 1 <= 1 ? 1 : this.currentPage - 1;
        this.nextPage = this.currentPage + 1 >= this.totalPage ? this.totalPage : this.currentPage + 1;
    }
}
