package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.DictType;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.DictTypeVo;
import com.sq.jk.pojo.vo.req.page.DictTypePageReqVo;

public interface DictTypeService extends IService<DictType> {
    PageVo<DictTypeVo> list(DictTypePageReqVo query);
}