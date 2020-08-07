package com.seemygo.shop.cloud.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seemygo.shop.cloud.domain.User;
import com.seemygo.shop.cloud.exception.BusinessException;
import com.seemygo.shop.cloud.mapper.UserMapper;
import com.seemygo.shop.cloud.redis.MemberRedisKey;
import com.seemygo.shop.cloud.resp.MemberServerMsg;
import com.seemygo.shop.cloud.service.IUserService;
import com.seemygo.shop.cloud.util.JSONUtil;
import com.seemygo.shop.cloud.util.MD5Util;
import com.seemygo.shop.cloud.vo.LoginVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;
    private final StringRedisTemplate redisTemplate;

    public UserServiceImpl(UserMapper userMapper, StringRedisTemplate redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public String login(LoginVo loginVo) {
        if (StringUtils.isEmpty(loginVo.getUsername())) {
            throw new BusinessException(MemberServerMsg.PARAM_ERROR);
        }

        User user = this.findById(Long.parseLong(loginVo.getUsername()));
        if (user == null) {
            throw new BusinessException(MemberServerMsg.USERNAME_OR_PASSWORD_ERROR);
        }
        String inputPassword = MD5Util.formPass2DbPass(loginVo.getPassword(), user.getSalt());
        if (!inputPassword.equals(user.getPassword())) {
            throw new BusinessException(MemberServerMsg.USERNAME_OR_PASSWORD_ERROR);
        }
        // 生成 token
        return createToken(user);
    }

    private String createToken(User user) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            // 保存到 redis
            String userStr = new ObjectMapper().writeValueAsString(user);
            redisTemplate.opsForValue().set(
                    MemberRedisKey.USER_TOKEN_KEY.join(token), // 真实key
                    userStr,  // 用户对象转成 json 字符串
                    MemberRedisKey.USER_TOKEN_KEY.getExpireTime(), // token 的过期时间
                    MemberRedisKey.USER_TOKEN_KEY.getUnit()); // 过期时间对应的单位
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public User findByToken(String token) {
        String json = redisTemplate.opsForValue().get(MemberRedisKey.USER_TOKEN_KEY.join(token));
        if (!org.springframework.util.StringUtils.isEmpty(json)) {
            return JSONUtil.parseObject(json, User.class);
        }
        return null;
    }

    @Override
    public boolean refreshToken(String token) {
        // redis 刷新 token 有效时间
        Boolean expire = redisTemplate.expire(
                MemberRedisKey.USER_TOKEN_KEY.join(token),
                MemberRedisKey.USER_TOKEN_KEY.getExpireTime(),
                MemberRedisKey.USER_TOKEN_KEY.getUnit());
        return expire != null && expire;
    }
}
