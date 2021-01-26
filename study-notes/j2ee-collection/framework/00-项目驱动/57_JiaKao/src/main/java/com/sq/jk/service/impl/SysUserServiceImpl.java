package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpLambdaQueryWrapper;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.Constants;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.common.util.Strings;
import com.sq.jk.mapper.SysUserMapper;
import com.sq.jk.pojo.po.SysUser;
import com.sq.jk.pojo.po.SysUserRole;
import com.sq.jk.pojo.result.CodeMsg;
import com.sq.jk.pojo.vo.JsonVo;
import com.sq.jk.pojo.vo.LoginVo;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.SysUserVo;
import com.sq.jk.pojo.vo.req.LoginReqVo;
import com.sq.jk.pojo.vo.req.page.SysUserPageReqVo;
import com.sq.jk.pojo.vo.req.save.SysUserReqVo;
import com.sq.jk.service.SysUserRoleService;
import com.sq.jk.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService userRoleService;

    @Override
    @Transactional(readOnly = true)
    public PageVo<SysUserVo> list(SysUserPageReqVo reqVo) {
        MpLambdaQueryWrapper<SysUser> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(), SysUser::getNickname, SysUser::getUsername);
        wrapper.orderByDesc(SysUser::getId);
        return baseMapper
                .selectPage(new MpPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    public boolean saveOrUpdate(SysUserReqVo reqVo) {
        // 转成PO
        SysUser po = MapStructs.INSTANCE.reqVo2po(reqVo);

        // 保存用户信息
        if (!saveOrUpdate(po)) return false;

        // 删除当前用户的所有角色信息
        userRoleService.removeByUserId(reqVo.getId());

        // 保存角色信息
        String roleIdsStr = reqVo.getRoleIds();
        if (Strings.isEmpty(roleIdsStr)) return true;

        String[] roleIds = reqVo.getRoleIds().split(",");
        List<SysUserRole> userRoles = new ArrayList<>();
        Integer userId = po.getId();
        for (String roleId : roleIds) { // 构建SysUserRole对象
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(Short.parseShort(roleId));
            userRoles.add(userRole);
        }
        return userRoleService.saveBatch(userRoles);
    }

    @Override
    public LoginVo login(LoginReqVo reqVo) {
        // 根据用户名查询用户
        MpLambdaQueryWrapper<SysUser> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, reqVo.getUsername());
        SysUser po = baseMapper.selectOne(wrapper);

        // 用户名不存在
        if (po == null) {
            return JsonVos.raise(CodeMsg.WRONG_USERNAME);
        }

        // 密码不正确
        if (!po.getPassword().equals(reqVo.getPassword())) {
            return JsonVos.raise(CodeMsg.WRONG_PASSWORD);
        }

        // 账号锁定
        if (po.getStatus() == Constants.SysUserStatus.LOCKED) {
            return JsonVos.raise(CodeMsg.USER_LOCKED);
        }

        // 更新登录时间
        po.setLoginTime(new Date());
        baseMapper.updateById(po);

        return MapStructs.INSTANCE.po2loginVo(po);
    }
}