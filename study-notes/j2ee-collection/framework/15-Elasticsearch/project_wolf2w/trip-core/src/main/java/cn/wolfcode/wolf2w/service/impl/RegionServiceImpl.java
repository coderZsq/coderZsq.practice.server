package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.mapper.RegionMapper;
import cn.wolfcode.wolf2w.query.RegionQuery;
import cn.wolfcode.wolf2w.service.IRegionService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {
    //思考： mp是怎么进分页？
    //1:分页插件
    //2:分页逻辑
    @Override
    public IPage<Region> queryPage(RegionQuery qo) {
        IPage<Region> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        return super.page(page, null);
    }

    @Override
    public void changeHotValue(Long id, int hot) {
        UpdateWrapper<Region> wrapper = Wrappers.<Region>update()
                .eq("id", id)
                .set("ishot", hot);
        super.update(wrapper);
    }

    @Override
    public List<Region> queryHotRegion() {

        //1:要热门
        //2:排序
        QueryWrapper<Region> wrapper = Wrappers.<Region>query()
                .eq("ishot", Region.STATE_HOT)
                .orderByAsc("seq");

        //wrapper = WrapperUtil.query(Region.class);
        return super.list(wrapper);
    }
}
