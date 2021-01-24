package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.DictItem;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.DictItemVo;
import com.sq.jk.pojo.vo.req.page.DictItemPageReqVo;

public interface DictItemService extends IService<DictItem> {
    PageVo<DictItemVo> list(DictItemPageReqVo query);
}