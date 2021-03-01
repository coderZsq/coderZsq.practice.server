package cn.wolfcode.wolf2w.job;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyCondition;
import cn.wolfcode.wolf2w.domain.StrategyRank;
import cn.wolfcode.wolf2w.service.IStrategyConditionService;
import cn.wolfcode.wolf2w.service.IStrategyRankService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 攻略条件统计定时任务
 */
//@Component
public class StrategyConditonStatisJob {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IStrategyConditionService strategyConditionService;

    /**
    select dest_id, dest_name, count(id) count from strategy where isabroad =0
    group by dest_id, dest_name
    order by count desc;
    */
    @Scheduled(cron = "0/10 * * * * ? ")
    public void dowork(){

        System.out.println("----------------统计--begin------------------------");
        //1:从攻略表中分组统计目的地，或者主题
        //国内
        QueryWrapper<Strategy> wrapper = WrapperUtil.query(Strategy.class)
                .select("dest_id, dest_name, count(id) count")
                .eq("isabroad", Strategy.ABROAD_NO)
                .groupBy("dest_id", "dest_name")
                .orderByDesc("count");
        List<Map<String, Object>> mapList1 = strategyService.listMaps(wrapper);

        //海外
        wrapper.clear();
        wrapper.eq("isabroad", Strategy.ABROAD_YES)
                .select("dest_id, dest_name, count(id) count")
                .groupBy("dest_id", "dest_name")
                .orderByDesc("count");
        List<Map<String, Object>> mapList2 = strategyService.listMaps(wrapper);

        //主题
        wrapper.clear();
        wrapper.groupBy("theme_id", "theme_name")
                .select("theme_id, theme_name, count(id) count")
                .orderByDesc("count");
        List<Map<String, Object>> mapList3 = strategyService.listMaps(wrapper);

        //2：将上面统计好数据添加到攻略条件统计表中
        Date now = new Date();
        List<StrategyCondition> data = new ArrayList<>();
        for (Map<String, Object> map : mapList1) {
            StrategyCondition sc  = new StrategyCondition();
            sc.setRefid(Long.valueOf(map.get("dest_id").toString()));
            sc.setName(map.get("dest_name").toString());
            sc.setCount(Long.valueOf(map.get("count").toString()));
            sc.setType(StrategyCondition.TYPE_CHINA);
            sc.setStatisTime(now);
            data.add(sc);
        }

        for (Map<String, Object> map : mapList2) {
            StrategyCondition sc  = new StrategyCondition();
            sc.setRefid(Long.valueOf(map.get("dest_id").toString()));
            sc.setName(map.get("dest_name").toString());
            sc.setCount(Long.valueOf(map.get("count").toString()));
            sc.setType(StrategyCondition.TYPE_ABROAD);
            sc.setStatisTime(now);
            data.add(sc);
        }
        for (Map<String, Object> map : mapList3) {
            StrategyCondition sc  = new StrategyCondition();
            sc.setRefid(Long.valueOf(map.get("theme_id").toString()));
            sc.setName(map.get("theme_name").toString());
            sc.setCount(Long.valueOf(map.get("count").toString()));
            sc.setType(StrategyCondition.TYPE_THEME);
            sc.setStatisTime(now);
            data.add(sc);
        }
        strategyConditionService.saveBatch(data);

        System.out.println("----------------统计--end------------------------");
    }
}
