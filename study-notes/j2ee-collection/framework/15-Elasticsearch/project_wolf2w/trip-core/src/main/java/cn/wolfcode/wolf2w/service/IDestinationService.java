package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 区域服务接口
 */
public interface IDestinationService extends IService<Destination> {


    /**
     *
     *查询指定id区域挂载的目的地集合
     * @param rid
     * @return
     */
    List<Destination> queryByRegionId(Long rid);

    /**
     * 分页查询
     * @param qo
     * @return
     */
    IPage<Destination> queryPage(DestinationQuery qo);

    /**
     * 查询指定目的地id吐司集合
     * @param destId
     * @return
     */
    List<Destination> queryToasts(Long destId);

    /**
     * 查询指定id区域挂载的目的地集合
     * @param regionId
     * @return
     */
    List<Destination> queryByRegionIdForApi(Long regionId);

    /**
     * 通过查询目的地
     * @param name
     * @return
     */
    Destination queryByName(String name);
}
