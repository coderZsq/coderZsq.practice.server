package cn.wolfcode.wolf2w.listener;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisVORedisService;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import cn.wolfcode.wolf2w.service.IStrategyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis缓存数据监听器
 */
//@Component
public class RedisDataInitListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private IStrategyStatisVORedisService strategyStatisVORedisService;
    //容器启动并初始化之后执行
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("--------------------vo对象初始化--begin-----------------------------------");
        //1:从mysql中获取所有攻略信息
        List<Strategy> list = strategyService.list();
        /**
         * 假设：攻略sid1 第一次初始化到vo对象， viewnum = 100
         *      接着其他用户访问攻略sid1， 假设一段时间后， vo中viewnum=200
         *      mgrsite需要进行更新，重启了， 那么攻略sid的vo对象此时的viewnum应该为多少
         *      按照现有逻辑， 之前200被覆盖， 此时需要对已经存在vo对象做跳过。
         */
        //2：遍历攻略封装成vo对象， 缓存到redis中
        for (Strategy s : list) {
            if(strategyStatisVORedisService.isVoExists(s.getId())){
                continue;
            }
            StrategyStatisVO vo  = new StrategyStatisVO();
            BeanUtils.copyProperties(s, vo);
            vo.setStrategyId(s.getId());
            strategyStatisVORedisService.setStrategyStatisVO(vo);
        }
        System.out.println("--------------------vo对象初始化--end-----------------------------------");
    }
}
