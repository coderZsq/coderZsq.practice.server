package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.StrategyTheme;
import cn.wolfcode.wolf2w.mapper.StrategyThemeMapper;
import cn.wolfcode.wolf2w.query.StrategyThemeQuery;
import cn.wolfcode.wolf2w.service.IStrategyThemeService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StrategyThemeServiceImpl extends ServiceImpl<StrategyThemeMapper, StrategyTheme> implements IStrategyThemeService {
    @Override
    public IPage<StrategyTheme> queryPage(StrategyThemeQuery qo) {
        IPage<StrategyTheme> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<StrategyTheme> wrapper = WrapperUtil.query(StrategyTheme.class);
        return super.page(page, wrapper);
    }
}
