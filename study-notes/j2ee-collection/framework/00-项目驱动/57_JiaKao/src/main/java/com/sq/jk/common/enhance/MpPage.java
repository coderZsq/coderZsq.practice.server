package com.sq.jk.common.enhance;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sq.jk.common.util.Streams;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.req.page.PageReqVo;

import java.util.function.Function;

public class MpPage<T> extends Page<T> {
    private final PageReqVo reqVo;

    public MpPage(PageReqVo reqVo) {
        super(reqVo.getPage(), reqVo.getSize());
        this.reqVo = reqVo;
    }

    public <R> PageVo<R> buildVo(Function<T, R> function) {
        reqVo.setPage(getCurrent());
        reqVo.setSize(getSize());

        PageVo<R> pageVo = new PageVo<>();
        pageVo.setCount(getTotal());
        pageVo.setPages(getPages());
        pageVo.setData(Streams.map(getRecords(), function));
        return pageVo;
    }
}
