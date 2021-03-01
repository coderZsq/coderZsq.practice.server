package cn.wolfcode.wolf2w.redis.service;

public interface ISecurityRedisService {
    /**
     * 是否允许访问
     * @param key
     * @return ture：允许访问， false：不允许访问
     */
     boolean isAllowBrush(String key);
}
