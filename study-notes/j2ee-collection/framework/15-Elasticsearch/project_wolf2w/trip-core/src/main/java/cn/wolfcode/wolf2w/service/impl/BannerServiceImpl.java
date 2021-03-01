package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Banner;
import cn.wolfcode.wolf2w.mapper.BannerMapper;
import cn.wolfcode.wolf2w.query.BannerQuery;
import cn.wolfcode.wolf2w.service.IBannerService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Banner服务接口实现
*/
@Service
@Transactional
public class BannerServiceImpl extends ServiceImpl<BannerMapper,Banner> implements IBannerService  {

    @Override
    public IPage<Banner> queryPage(BannerQuery qo) {
        IPage<Banner> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Banner> wrapper = Wrappers.<Banner>query();
        return super.page(page, wrapper);
    }

    @Override
    public List<Banner> queryByType(int type) {
        //1:指定type类型
        //2:必须是正常的
        //3:排序
        //4:前5个
        QueryWrapper<Banner> wrapper = WrapperUtil.query(Banner.class)
                .eq("type", type)
                .eq("state", Banner.STATE_NORMAL)
                .orderByAsc("seq")
                .last("limit 5");
        return super.list(wrapper);
    }
}
