package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.exception.CommonException;
import com.sq.jk.common.util.Rs;
import com.sq.jk.pojo.result.R;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

public abstract class BaseController<T> {
    protected abstract IService<T> getService();

    @PostMapping("/remove")
    public R remove(String id) {
        if (getService().removeByIds(Arrays.asList(id.split(",")))) {
            return Rs.ok("删除成功");
        } else {
            throw new CommonException("删除失败");
        }
    }

    @PostMapping("/save")
    public R save(T entity) {
        if (getService().saveOrUpdate(entity)) {
            return Rs.ok("保存成功");
        } else {
            throw new CommonException("保存失败");
        }
    }
}
