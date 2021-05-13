package cn.wolfcode.netty.im.webserver.user.service.impl;

import cn.wolfcode.netty.im.webserver.user.mapper.UserInfoMapper;
import cn.wolfcode.netty.im.webserver.user.model.UserInfoEntity;
import cn.wolfcode.netty.im.webserver.user.model.UserVo;
import cn.wolfcode.netty.im.webserver.user.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Leon
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoMapper userInfoMapper;

    public UserInfoServiceImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public void save(UserInfoEntity userInfo) {
        userInfoMapper.save(userInfo);
    }

    @Override
    public int update(UserInfoEntity userInfo) {
        return userInfoMapper.update(userInfo);
    }

    @Override
    public int delete(Long id) {
        return userInfoMapper.delete(id);
    }

    @Override
    public List<UserVo> queryByIdList(List<String> idList) {
        return userInfoMapper.queryByIdList(idList);
    }

}
