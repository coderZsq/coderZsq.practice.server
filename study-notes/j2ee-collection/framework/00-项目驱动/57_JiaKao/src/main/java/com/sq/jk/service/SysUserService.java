package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.SysUser;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.SysUserVo;
import com.sq.jk.pojo.vo.req.page.SysUserPageReqVo;

public interface SysUserService extends IService<SysUser> {

    PageVo<SysUserVo> list(SysUserPageReqVo reqVo);
}