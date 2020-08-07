package com.seemygo.shop.cloud.service;

import com.seemygo.shop.cloud.domain.User;
import com.seemygo.shop.cloud.vo.LoginVo;

public interface IUserService {

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 用户登录操作
     * @param vo
     */
    String login(LoginVo vo);

    /**
     * 根据token从redis中查询用户信息
     *
     * @param token
     * @return
     */
    User findByToken(String token);

    /**
     * 刷新redis中的token
     *
     * @param token
     * @return
     */
    boolean refreshToken(String token);
}
