package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.Province;
import com.sq.jk.pojo.query.ProvinceQuery;

public interface ProvinceService extends IService<Province> {
    void list(ProvinceQuery query);
}