package cn.wolfcode.wolf2w.search.service;

import cn.wolfcode.wolf2w.search.domain.DestinationEs;

import java.util.List;

public interface IDestinationEsService {
    /** 添加
    * @param destinationEsEs
    * @return
     */
    void save(DestinationEs destinationEsEs);

    /**
     * 更新
     * @param destinationEsEs
     * @return
     */
    void update(DestinationEs destinationEsEs);

    /**
     * 查单个
     * @param id
     * @return
     */
    DestinationEs get(String id);

    /**
     * 查多个
     * @return
     */
    List<DestinationEs> list();

    /**
     * 删除
     * @param id
     */
    void delete(String id);

}
