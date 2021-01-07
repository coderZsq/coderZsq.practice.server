package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.util.Rs;
import com.sq.jk.pojo.result.CodeMsg;
import com.sq.jk.pojo.result.R;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;

public abstract class BaseController<T> {
    protected abstract IService<T> getService();

    @PostMapping("/remove")
    public R remove(String id) {
        if (getService().removeByIds(Arrays.asList(id.split(",")))) {
            return Rs.ok(CodeMsg.REMOVE_SUCCESS);
        } else {
            return Rs.raise(CodeMsg.REMOVE_ERROR);
        }
    }

    @PostMapping("/save")
    public R save(@Valid T entity) {
        if (getService().saveOrUpdate(entity)) {
            return Rs.ok(CodeMsg.SAVE_SUCCESS);
        } else {
            return Rs.raise(CodeMsg.SAVE_ERROR);
        }
    }
}
