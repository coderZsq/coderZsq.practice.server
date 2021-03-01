package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.StrategyCondition;
import cn.wolfcode.wolf2w.domain.StrategyRank;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 攻略统计条件服务接口
 */
public interface IStrategyConditionService extends IService<StrategyCondition> {


    /**
     * 通过类型查询攻略统计条件
     * @param type
     * @return
     */
    List<StrategyCondition> queryByType(int type);
}
