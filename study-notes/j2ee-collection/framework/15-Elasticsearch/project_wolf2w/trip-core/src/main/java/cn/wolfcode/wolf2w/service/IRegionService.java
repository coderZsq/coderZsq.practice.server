package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.query.RegionQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 区域服务接口
 */
public interface IRegionService extends IService<Region> {


    /**
     * 分页查询
     * @param qo
     * @return
     */
    IPage<Region> queryPage(RegionQuery qo);

    /**
     * 修改热门状态
     * @param id
     * @param hot
     */
    void changeHotValue(Long id, int hot);

    /**
     * 查询热门区域
     * @return
     */
    List<Region> queryHotRegion();
}
