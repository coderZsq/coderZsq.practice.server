package cn.wolfcode.wolf2w.redis.service;

import cn.wolfcode.wolf2w.domain.UserInfo;

/**
 * 用户缓存服务接口
 */
public interface IUserInfoRedisService {
    /**
     * 将验证码缓存到redis中
     * @param phone
     * @param code
     */
    void setVerifyCode(String phone, String code);

    /**
     * 从redis中获取验证码
     * @param phone
     * @return
     */
    String getVerifyCode(String phone);

    /**
     * 创建并设置token
     * @param user
     * @return
     */
    String setToken(UserInfo user);

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    UserInfo getUserByToken(String token);
}
