package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.DictItem;
import com.sq.jk.pojo.query.DictItemQuery;

public interface DictItemService extends IService<DictItem> {
    void list(DictItemQuery query);
}