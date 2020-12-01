package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.DictType;
import com.sq.jk.query.DictTypeQuery;

import java.util.List;

public interface DictTypeService extends IService<DictType> {
    List<DictType> list(DictTypeQuery query);
}
