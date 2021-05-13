package cn.wolfcode.netty.im.webserver.user.service;


import cn.wolfcode.netty.im.webserver.user.model.UserInfoEntity;
import cn.wolfcode.netty.im.webserver.user.model.UserVo;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表
 *
 * @author Leon
 */
public interface UserInfoService {

    void save(UserInfoEntity userInfo);

    int update(UserInfoEntity userInfo);

    int delete(Long id);

    List<UserVo> queryByIdList(List<String> idList);
}
