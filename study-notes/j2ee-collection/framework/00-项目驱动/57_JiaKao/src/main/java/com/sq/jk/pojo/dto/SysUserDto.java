package com.sq.jk.pojo.dto;

import com.sq.jk.pojo.po.SysResource;
import com.sq.jk.pojo.po.SysRole;
import com.sq.jk.pojo.po.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserDto {
    private SysUser user;
    private List<SysRole> roles;
    private List<SysResource> resources;
}
