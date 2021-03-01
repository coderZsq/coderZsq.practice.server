package cn.wolfcode.wolf2w.job;

import cn.wolfcode.wolf2w.controller.StrategyController;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisVORedisService;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import cn.wolfcode.wolf2w.service.IStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis数据持久化定时任务
 */
//@Component
public class RedisDataPersistenceJob {

    @Autowired
    private IStrategyStatisVORedisService strategyStatisVORedisService;

    @Autowired
    private IStrategyService strategyService;


    @Scheduled(cron = "0/10 * * * * ? ")
    public void doWork(){
        System.out.println("-------------------vo持久化--begin-----------------------------");
        //1:从redis中获取所有vo对象
        List<StrategyStatisVO> vos = strategyStatisVORedisService.queryByPattern("*");
        //2：遍历vo对象，更新到对应攻略里面
        for (StrategyStatisVO vo : vos) {
            strategyService.updateStatisVo(vo);
        }
        System.out.println("-------------------vo持久化--end-----------------------------");

    }

}
