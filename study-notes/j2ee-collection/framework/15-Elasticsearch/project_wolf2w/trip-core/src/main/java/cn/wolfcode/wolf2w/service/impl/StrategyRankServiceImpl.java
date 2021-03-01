package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.StrategyRank;
import cn.wolfcode.wolf2w.mapper.StrategyRankMapper;
import cn.wolfcode.wolf2w.service.IStrategyRankService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StrategyRankServiceImpl extends ServiceImpl<StrategyRankMapper, StrategyRank> implements IStrategyRankService {

   /**
    select * from strategy_rank where type = 2
    and statis_time in (select max(statis_time) from strategy_rank where type = 2)
    order by statisnum desc
   */
    @Override
    public List<StrategyRank> queryByType(int type) {
        QueryWrapper<StrategyRank> wrapper = WrapperUtil.query(StrategyRank.class)
                .eq("type", type)
                .inSql("statis_time", "(select max(statis_time) from strategy_rank where type = "+type+")")
                .orderByDesc("statisnum");
        return super.list(wrapper);
    }
}
