package com.sq.jk.common.shiro;

import com.sq.jk.common.cache.Caches;
import com.sq.jk.common.util.Streams;
import com.sq.jk.pojo.po.SysUser;
import com.sq.jk.pojo.vo.list.SysResourceVo;
import com.sq.jk.pojo.vo.list.SysRoleVo;
import com.sq.jk.service.SysResourceService;
import com.sq.jk.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
public class TokenRealm extends AuthorizingRealm {

    @Autowired
    private SysRoleService roleService;
    private SysResourceService resourceService;

    public TokenRealm(TokenMatcher matcher) {
        super(matcher);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        log.debug("TokenRealm - supports - {}", token);
        return token instanceof Token;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 拿到当前登录用户的token
        String token = (String) principals.getPrimaryPrincipal();
        log.debug("TokenRealm - doGetAuthorizationInfo - {}", token);

        // 根据token查找用户的角色, 权限
        SysUser user = Caches.getToken(token);
        user.getId();

        // sys_role
        // sys_user_role
        List<SysRoleVo> roles = roleService.listByUserId(user.getId());
        if (CollectionUtils.isEmpty(roles)) return null;

        // 添加角色

        // sys_resource
        // sys_role_resource
        List<Short> roleIds = Streams.map(roles, SysRoleVo::getId);
        List<SysResourceVo> resources = resourceService.listByRoleIds(roleIds);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String tk = ((Token) token).getToken();
        log.debug("TokenRealm - doGetAuthenticationInfo - {}", tk);
        return new SimpleAuthenticationInfo(tk, tk, getName());
    }
}
