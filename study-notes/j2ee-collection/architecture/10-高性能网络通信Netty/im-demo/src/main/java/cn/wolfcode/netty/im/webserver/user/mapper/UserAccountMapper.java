package cn.wolfcode.netty.im.webserver.user.mapper;


import cn.wolfcode.netty.im.webserver.base.mapper.BaseMapper;
import cn.wolfcode.netty.im.webserver.user.model.UserAccountEntity;

import java.util.Map;

/**
 * 用户帐号
 *
 * @author Leon
 */
public interface UserAccountMapper extends BaseMapper<UserAccountEntity> {

    UserAccountEntity queryObjectByAccount(Map<String, Object> map);
}
