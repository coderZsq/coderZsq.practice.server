package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyContent;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 攻略服务服务接口
 */
public interface IStrategyService extends IService<Strategy> {


    /**
     * 分页查询
     * @param qo
     * @return
     */
    IPage<Strategy> queryPage(StrategyQuery qo);

    /**
     * 攻略内容对象
     * @param id
     * @return
     */
    StrategyContent getContent(Long id);


    /**
     * 指定目的地下点击量前3攻略集合
     * @param destId
     * @return
     */
    List<Strategy> queryViewnumTop3(Long destId);

    /**
     * vo对象持久化操作
     * @param vo
     */
    void updateStatisVo(StrategyStatisVO vo);

    /**
     * 指定目的地id下的攻略集合
     * @param destId
     * @return
     */
    List<Strategy> queryByDestId(Long destId);
}
