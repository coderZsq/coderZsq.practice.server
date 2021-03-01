package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.StrategyCondition;
import cn.wolfcode.wolf2w.mapper.StrategyConditionMapper;
import cn.wolfcode.wolf2w.service.IStrategyConditionService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StrategyConditionServiceImpl extends ServiceImpl<StrategyConditionMapper, StrategyCondition> implements IStrategyConditionService {
    /**
        select * from strategy_condition where type = 2
        and statis_time in
                (select max(statis_time) from strategy_condition where type = 2 )
        order by count desc
     */
    @Override
    public List<StrategyCondition> queryByType(int type) {

        QueryWrapper<StrategyCondition> wrapper = WrapperUtil.query(StrategyCondition.class)
                .eq("type", type)
                .inSql("statis_time","(select max(statis_time) from strategy_condition where type = "+type+" )" )
                .orderByDesc("count");
        return super.list(wrapper);
    }
}
