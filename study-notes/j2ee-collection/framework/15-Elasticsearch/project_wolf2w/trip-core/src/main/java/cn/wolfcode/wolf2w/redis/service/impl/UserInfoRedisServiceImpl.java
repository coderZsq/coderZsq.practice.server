package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.service.IUserInfoRedisService;
import cn.wolfcode.wolf2w.redis.util.RedisKeys;
import cn.wolfcode.wolf2w.util.Consts;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoRedisServiceImpl implements IUserInfoRedisService {

    @Autowired
    private StringRedisTemplate template;

    @Override
    public void setVerifyCode(String phone, String code) {
        //key: 唯一性， 可读性， 灵活性， 时效性
        String key = RedisKeys.REGIST_VERIFY_CODE.join(phone);
        template.opsForValue().set(key, code, RedisKeys.REGIST_VERIFY_CODE.getTime(), TimeUnit.SECONDS);
    }

    @Override
    public String getVerifyCode(String phone) {
        String key = RedisKeys.REGIST_VERIFY_CODE.join(phone);
        return  template.opsForValue().get(key);
    }

    @Override
    public String setToken(UserInfo user) {
        //1:创建token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //2:设置token
        String key = RedisKeys.USER_LOGIN_TOKEN.join(token);
        String value = JSON.toJSONString(user);
        template.opsForValue().set(key, value, RedisKeys.USER_LOGIN_TOKEN.getTime(), TimeUnit.SECONDS);
        return token;
    }

    @Override
    public UserInfo getUserByToken(String token) {
        if(!StringUtils.hasLength(token)){
            return null;
        }
        String key = RedisKeys.USER_LOGIN_TOKEN.join(token);
        if(template.hasKey(key)){
            String userStr = template.opsForValue().get(key);
            UserInfo user = JSON.parseObject(userStr, UserInfo.class);
            //重置token有效时间
            template.expire(key, RedisKeys.USER_LOGIN_TOKEN.getTime(), TimeUnit.SECONDS);
            return user;
        }
        return null;
    }
}
