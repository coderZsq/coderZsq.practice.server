package com.sq.jk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.DictType;

import java.util.List;

public interface DictTypeService extends IService<DictType> {
    IPage<DictType> list(long page, long limit, String keyword);
}