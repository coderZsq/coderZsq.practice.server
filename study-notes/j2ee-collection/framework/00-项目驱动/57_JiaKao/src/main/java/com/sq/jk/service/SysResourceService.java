package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.SysResource;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.SysResourceTreeVo;
import com.sq.jk.pojo.vo.list.SysResourceVo;
import com.sq.jk.pojo.vo.req.page.SysResourcePageReqVo;

import java.util.List;

public interface SysResourceService extends IService<SysResource> {
    PageVo<SysResourceVo> list(SysResourcePageReqVo reqVo);

    List<SysResourceVo> listParents();

    List<SysResourceTreeVo> listTree();

    List<Short> listIds(Integer roleId);

    List<SysResource> listByRoleIds(List<Short> roleIds);
}