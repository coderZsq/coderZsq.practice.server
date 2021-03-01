package cn.wolfcode.wolf2w.job;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyRank;
import cn.wolfcode.wolf2w.service.IStrategyRankService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 攻略排行统计定时任务
 */
//@Component
public class StrategyRankStatisJob {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IStrategyRankService strategyRankService;



    private List<StrategyRank> createStrategyRank(List<Map<String, Object>> mapList, Date now, int type){
        List<StrategyRank> data = new ArrayList<>();
        //2:将上面统计的数据添加到攻略排行统计中
        for (Map<String, Object> map : mapList) {
            StrategyRank rank = new StrategyRank();
            rank.setType(type);
            rank.setDestId(Long.valueOf(map.get("dest_id").toString()));
            rank.setDestName(map.get("dest_name").toString());
            rank.setStrategyId(Long.valueOf(map.get("id").toString()));
            rank.setStrategyTitle(map.get("title").toString());
            rank.setStatisnum(Long.valueOf(map.get("statisnum").toString()));
            rank.setStatisTime(now);
            data.add(rank);
        }
        return data;
    }
    private List<Map<String, Object>> queryStrategyRank(Integer isabroad, String statisnumColumn){
        QueryWrapper<Strategy> wrapper = WrapperUtil.query(Strategy.class)
                .select("id,dest_id,dest_name,title,"+statisnumColumn+" statisnum")
                .eq(isabroad != null, "isabroad", isabroad)
                .orderByDesc("statisnum")
                .last("limit 10");
        return strategyService.listMaps(wrapper);
    }

    /**
     * 　Cron表达式是一个字符串，字符串以5或6个空格隔开，分为6或7个域，每一个域代表一个含义，Cron有如下两种语法格式：
     *
     * 　　（1） Seconds Minutes Hours DayofMonth Month DayofWeek Year
     *            秒      分      时      几号     月     周几     年
     * 　　（2）Seconds Minutes Hours DayofMonth Month DayofWeek   spring支持这种
     *           秒      分      时      几号     月     周几
     *
     *           0      0       2        1      *       ?         *   表示在每月的1日的凌晨2点调整任务
     *           0      15     10        ?      *     MON-FRI         表示周一到周五每天上午10:15执行作业
     */

    //@Scheduled： 定时任务标签， 按照指定时间计划执行dowork方法
    //cron 表达式， 即时间计划，简单理解：什么时候，做事情
    @Scheduled(cron = "0/10 * * * * ? ")
    public void dowork(){

        System.out.println("----------------统计--begin------------------------");
        //1:从攻略表中按要求统计出满足条件数据
        //第一份数：
        //国外攻略排行：收藏数 + 点赞数
       /* SELECT id,dest_id,dest_name,title,thumbsupnum + favornum statisnum
        FROM strategy WHERE isabroad = 1
        ORDER BY thumbsupnum + favornum DESC limit 10
        */
        List<Map<String, Object>> mapList1 = this.queryStrategyRank(Strategy.ABROAD_YES, "thumbsupnum + favornum");
        //国内攻略排行：收藏数 + 点赞数
        List<Map<String, Object>> mapList2 = this.queryStrategyRank(Strategy.ABROAD_NO, "thumbsupnum + favornum");
        //热门攻略排行：浏览数 + 评论数
        List<Map<String, Object>> mapList3 = this.queryStrategyRank(null, "viewnum + replynum");

        Date now = new Date();
        List<StrategyRank> data = new ArrayList<>();
        //2:将上面统计的数据添加到攻略排行统计中
        data.addAll(this.createStrategyRank(mapList1, now, StrategyRank.TYPE_ABROAD));
        data.addAll(this.createStrategyRank(mapList2, now, StrategyRank.TYPE_CHINA));
        data.addAll(this.createStrategyRank(mapList3, now, StrategyRank.TYPE_HOT));

        //save
        strategyRankService.saveBatch(data);
        System.out.println("----------------统计--end------------------------");
    }
}
