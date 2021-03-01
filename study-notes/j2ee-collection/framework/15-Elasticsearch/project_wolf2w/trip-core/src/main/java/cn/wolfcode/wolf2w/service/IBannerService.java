package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Banner;
import cn.wolfcode.wolf2w.query.BannerQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * Banner服务接口
 */
public interface IBannerService extends IService<Banner>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<Banner> queryPage(BannerQuery qo);

    /**
     * 查询游记banner
     * @param type
     * @return
     */
    List<Banner> queryByType(int type);
}
