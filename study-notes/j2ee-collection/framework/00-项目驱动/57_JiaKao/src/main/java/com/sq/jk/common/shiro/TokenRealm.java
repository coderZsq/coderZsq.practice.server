package com.sq.jk.common.shiro;

import com.sq.jk.common.cache.Caches;
import com.sq.jk.pojo.dto.SysUserDto;
import com.sq.jk.pojo.po.SysResource;
import com.sq.jk.pojo.po.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
public class TokenRealm extends AuthorizingRealm {
    // @Autowired
    // private SysRoleService roleService;
    // @Autowired
    // private SysResourceService resourceService;

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
        SysUserDto user = Caches.getToken(token);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<SysRole> roles = user.getRoles();
        if (CollectionUtils.isEmpty(roles)) return info;

        // 添加角色
        for (SysRole role : roles) {
            info.addRole(role.getName());
        }

        List<SysResource> resources = user.getResources();
        if (CollectionUtils.isEmpty(resources)) return info;
        // 添加权限
        for (SysResource resource : resources) {
            info.addStringPermission(resource.getPermission());
        }
        return info;

        // SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //
        // // sys_role
        // // sys_user_role
        // List<SysRole> roles = roleService.listByUserId(user.getId());
        // if (CollectionUtils.isEmpty(roles)) return info;
        //
        // // 添加角色
        // for (SysRole role : roles) {
        //     info.addRole(role.getName());
        // }
        //
        // // sys_resource
        // // sys_role_resource
        // List<Short> roleIds = Streams.map(roles, SysRole::getId);
        // List<SysResource> resources = resourceService.listByRoleIds(roleIds);
        // if (CollectionUtils.isEmpty(resources)) return info;
        //
        // // 添加权限
        // for (SysResource resource : resources) {
        //     info.addStringPermission(resource.getPermission());
        // }
        // return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String tk = ((Token) token).getToken();
        log.debug("TokenRealm - doGetAuthenticationInfo - {}", tk);
        return new SimpleAuthenticationInfo(tk, tk, getName());
    }
}
