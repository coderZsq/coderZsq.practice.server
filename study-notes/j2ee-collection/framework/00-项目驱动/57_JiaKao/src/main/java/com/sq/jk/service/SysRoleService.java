package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.SysRole;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.SysRoleVo;
import com.sq.jk.pojo.vo.req.page.SysRolePageReqVo;

public interface SysRoleService extends IService<SysRole> {
    PageVo<SysRoleVo> list(SysRolePageReqVo reqVo);
}