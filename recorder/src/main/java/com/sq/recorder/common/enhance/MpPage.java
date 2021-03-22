package com.sq.recorder.common.enhance;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sq.recorder.common.util.Streams;
import com.sq.recorder.pojo.vo.PageVo;
import com.sq.recorder.pojo.vo.req.page.PageReqVo;

import java.util.List;
import java.util.function.Function;

public class MpPage<T> extends Page<T> {
    private final PageReqVo reqVo;

    public MpPage(PageReqVo reqVo) {
        super(reqVo.getPage(), reqVo.getSize());
        this.reqVo = reqVo;
    }

    private <N> PageVo<N> commonBuildVo(List<N> data) {
        reqVo.setPage(getCurrent());
        reqVo.setSize(getSize());

        PageVo<N> pageVo = new PageVo<>();
        pageVo.setCount(getTotal());
        pageVo.setPages(getPages());
        pageVo.setData(data);
        return pageVo;
    }

    public PageVo<T> buildVo() {
        return commonBuildVo(getRecords());
    }

    public <R> PageVo<R> buildVo(Function<T, R> function) {
        return commonBuildVo(Streams.map(getRecords(), function));
    }
}
