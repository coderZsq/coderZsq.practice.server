package com.sq.jk.enhance;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sq.jk.pojo.query.PageQuery;

public class MpPage<T> extends Page<T> {
    public MpPage(PageQuery query) {
        super(query.getPage(), query.getSize());
    }

    public void updateQuery(PageQuery query) {
        query.setCount(getTotal());
        query.setPages(getPages());
        query.setData(getRecords());
        query.setPage(getCurrent());
        query.setSize(getSize());
    }
}
