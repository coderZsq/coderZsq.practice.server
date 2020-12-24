package com.sq.jk.common.enhance;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sq.jk.pojo.query.PageQuery;

public class MpPage<T> extends Page<T> {
    private PageQuery query;

    public MpPage(PageQuery query) {
        super(query.getPage(), query.getSize());
        this.query = query;
    }

    public void updateQuery() {
        query.setCount(getTotal());
        query.setPages(getPages());
        query.setData(getRecords());
        query.setPage(getCurrent());
        query.setSize(getSize());
    }
}
