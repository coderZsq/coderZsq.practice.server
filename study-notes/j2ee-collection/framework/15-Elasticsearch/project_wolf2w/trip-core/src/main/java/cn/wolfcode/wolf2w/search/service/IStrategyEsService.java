package cn.wolfcode.wolf2w.search.service;

import cn.wolfcode.wolf2w.search.domain.StrategyEs;

import java.util.List;

public interface IStrategyEsService {
    /** 添加
    * @param strategyEsEs
    * @return
     */
    void save(StrategyEs strategyEsEs);

    /**
     * 更新
     * @param strategyEsEs
     * @return
     */
    void update(StrategyEs strategyEsEs);

    /**
     * 查单个
     * @param id
     * @return
     */
    StrategyEs get(String id);

    /**
     * 查多个
     * @return
     */
    List<StrategyEs> list();

    /**
     * 删除
     * @param id
     */
    void delete(String id);

}
