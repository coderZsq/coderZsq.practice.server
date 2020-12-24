package com.sq.jk.controller;

import com.sq.jk.common.exception.CommonException;
import com.sq.jk.common.util.Rs;
import com.sq.jk.pojo.po.DictType;
import com.sq.jk.pojo.query.DictTypeQuery;
import com.sq.jk.pojo.result.R;
import com.sq.jk.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/dictTypes")
public class DictTypeController {
    @Autowired
    private DictTypeService service;

    @GetMapping
    public R list(DictTypeQuery query) {
        service.list(query);
        return Rs.ok(query);
    }

    @PostMapping("/remove")
    public R remove(String id) {
        if (service.removeByIds(Arrays.asList(id.split(",")))) {
            return Rs.ok("删除成功");
        } else {
            throw new CommonException("删除失败");
        }
    }

    @PostMapping("/save")
    public R save(DictType dictType) {
        if (service.saveOrUpdate(dictType)) {
            return Rs.ok("保存成功");
        } else {
            throw new CommonException("保存失败");
        }
    }
}