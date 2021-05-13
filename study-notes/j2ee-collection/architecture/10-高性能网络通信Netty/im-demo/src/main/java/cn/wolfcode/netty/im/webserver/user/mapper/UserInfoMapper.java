package cn.wolfcode.netty.im.webserver.user.mapper;

import cn.wolfcode.netty.im.webserver.base.mapper.BaseMapper;
import cn.wolfcode.netty.im.webserver.user.model.UserInfoEntity;
import cn.wolfcode.netty.im.webserver.user.model.UserVo;

import java.util.List;

/**
 * 用户信息表
 *
 * @author Leon
 */
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {

    List<UserVo> queryByIdList(List<String> idList);
}
