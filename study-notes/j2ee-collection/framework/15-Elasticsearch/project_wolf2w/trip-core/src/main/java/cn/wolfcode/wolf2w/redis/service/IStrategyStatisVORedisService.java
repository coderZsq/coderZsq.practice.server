package cn.wolfcode.wolf2w.redis.service;

import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;

import java.util.List;

/**
 * 攻略统计对象缓存服务接口
 */
public interface IStrategyStatisVORedisService {

    /**
     * 阅读数+1
     * @param sid
     */
    void viewnumIncrease(Long sid);

    /**
     * 获取vo对象
     * @param sid
     * @return
     */
    StrategyStatisVO getStrategyStatisVO(Long sid);


    /**
     * 设置vo对象
     * @param vo
     */
    void setStrategyStatisVO(StrategyStatisVO vo);


    /**
     * 评论数+1
     * @param sid
     */
    void replynumIncrease(Long sid);

    /**
     * 攻略收藏
     * @param sid
     * @param uid
     * @return true：收藏成功， false：取消收藏
     */
    boolean favor(Long sid, Long uid);

    /**
     * 获取指定用户的攻略收藏id集合
     * @param uid
     * @return
     */
    List<Long> getStrategyIdList(Long uid);
    /**
     * 攻略点赞
     * @param sid
     * @param uid
     * @return true：点赞成功， false：今天已经顶过
     */
    boolean strategyThumbup(Long sid, Long uid);

    /**
     * 指定攻略id的vo对象是否存在
     * @param sid
     * @return  true：存在， false：不存在
     */
    boolean isVoExists(Long sid);

    /**
     * 查询所有vo，通过匹配规则
     * @param pattern
     * @return
     */
    List<StrategyStatisVO> queryByPattern(String pattern);
}
