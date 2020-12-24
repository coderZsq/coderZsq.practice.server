package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.util.Rs;
import com.sq.jk.pojo.po.DictType;
import com.sq.jk.pojo.query.DictTypeQuery;
import com.sq.jk.pojo.result.R;
import com.sq.jk.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictTypes")
public class DictTypeController extends BaseController<DictType> {
    @Autowired
    private DictTypeService service;

    @GetMapping
    public R list(DictTypeQuery query) {
        service.list(query);
        return Rs.ok(query);
    }

    @Override
    protected IService<DictType> getService() {
        return service;
    }
}