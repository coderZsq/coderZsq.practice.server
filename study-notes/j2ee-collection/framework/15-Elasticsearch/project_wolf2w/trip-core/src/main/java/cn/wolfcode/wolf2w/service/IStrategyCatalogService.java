package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.StrategyCatalog;
import cn.wolfcode.wolf2w.query.StrategyCatalogQuery;
import cn.wolfcode.wolf2w.vo.CatalogVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 攻略分类服务服务接口
 */
public interface IStrategyCatalogService extends IService<StrategyCatalog> {


    /**
     * 分页查询
     * @param qo
     * @return
     */
    IPage<StrategyCatalog> queryPage(StrategyCatalogQuery qo);


    /**
     * 分组下拉框
     * @return
     */
    List<CatalogVO> queryGroupList();

    /**
     * 查询指定目的地id下的攻略分类
     * @param destId
     * @return
     */
    List<StrategyCatalog> queryByDestId(Long destId);
}
