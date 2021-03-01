package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.domain.TravelContent;
import cn.wolfcode.wolf2w.query.TravelQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 游记服务接口
 */
public interface ITravelService extends IService<Travel>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Travel> queryPage(TravelQuery qo);

    /**
     * 查询游记内容
     * @param id
     * @return
     */
    TravelContent getContent(Long id);

    /**
     * 执行审核逻辑
     * @param id
     * @param state
     */
    void audit(Long id, int state);

    /**
     * 目的地下点击量前3
     * @param destId
     * @return
     */
    List<Travel> queryViewnumTop3(Long destId);

    /**
     * 查询指定目的地id下游记
     * @param destId
     * @return
     */
    List<Travel> queryByDestId(Long destId);
}
