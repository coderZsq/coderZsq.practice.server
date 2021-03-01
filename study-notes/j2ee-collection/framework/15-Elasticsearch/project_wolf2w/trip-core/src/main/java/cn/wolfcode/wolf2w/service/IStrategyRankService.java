package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.domain.StrategyRank;
import cn.wolfcode.wolf2w.query.RegionQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 攻略排行服务接口
 */
public interface IStrategyRankService extends IService<StrategyRank> {


    /**
     * 通过类型查询攻略排行
     * @param type
     * @return
     */
    List<StrategyRank> queryByType(int type);
}
