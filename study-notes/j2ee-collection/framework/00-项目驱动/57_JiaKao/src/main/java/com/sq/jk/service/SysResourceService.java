package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.SysResource;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.SysResourceVo;
import com.sq.jk.pojo.vo.req.page.SysResourcePageReqVo;

public interface SysResourceService extends IService<SysResource> {
    PageVo<SysResourceVo> list(SysResourcePageReqVo reqVo);
}