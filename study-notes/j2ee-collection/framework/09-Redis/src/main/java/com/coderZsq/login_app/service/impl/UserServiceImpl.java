package com.coderZsq.login_app.service.impl;

import com.coderZsq.login_app.domain.User;
import com.coderZsq.login_app.mapper.UserMapper;
import com.coderZsq.login_app.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User queryByName(String username) {
        System.out.println("从数据库查询结果:"+username);
        return userMapper.queryByName(username);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public User queryByNameByCache(String username) throws Exception {
        System.out.println("从redis缓存中查询查询结果:"+username);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get("user:" + username);//直接从本地内存(比较短的时间1min)查询, 对于redis的压力会小
        ObjectMapper objectMapper = new ObjectMapper();
        User user=null;
        //判断redis的返回结果
        if(StringUtils.isEmpty(value)){
            // 查询数据库
            user= queryByName(username);
            if(user!=null){//添加到缓存中
                ops.set("user:"+username,objectMapper.writeValueAsString(user),7,TimeUnit.DAYS);
            }else{
                ops.set("user:"+username,objectMapper.writeValueAsString(new User()),1,TimeUnit.MINUTES);
            }
        }else{
            user = objectMapper.readValue(value,User.class);
        }
        return user;
    }
}
