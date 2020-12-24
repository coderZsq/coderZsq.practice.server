package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.exception.CommonException;
import com.sq.jk.common.util.Rs;
import com.sq.jk.pojo.po.DictItem;
import com.sq.jk.pojo.query.DictItemQuery;
import com.sq.jk.pojo.result.R;
import com.sq.jk.service.DictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/dictItems")
public class DictItemController extends BaseController<DictItem> {
    @Autowired
    private DictItemService service;

    @GetMapping
    public R list(DictItemQuery query) {
        service.list(query);
        return Rs.ok(query);
    }

    @Override
    protected IService<DictItem> getService() {
        return service;
    }
}