package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.*;
import cn.wolfcode.wolf2w.mapper.StrategyContentMapper;
import cn.wolfcode.wolf2w.mapper.StrategyMapper;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IStrategyCatalogService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.service.IStrategyThemeService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StrategyServiceImpl extends ServiceImpl<StrategyMapper, Strategy> implements IStrategyService {


    @Autowired
    private StrategyContentMapper strategyContentMapper;
    @Autowired
    private IStrategyCatalogService strategyCatalogService;
    @Autowired
    private IStrategyThemeService strategyThemeService;
    
    @Autowired
    private IDestinationService destinationService;

    @Override
    public IPage<Strategy> queryPage(StrategyQuery qo) {
        IPage<Strategy> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Strategy> wrapper = Wrappers.<Strategy>query()
                .eq(qo.getDestId() != null, "dest_id", qo.getDestId())
                .eq(qo.getThemeId() != null, "theme_id", qo.getThemeId())
                .orderByDesc(StringUtils.hasLength(qo.getOrderBy()), qo.getOrderBy());

        if(qo.getType() != null){
            if(qo.getType() == StrategyCondition.TYPE_ABROAD || qo.getType() == StrategyCondition.TYPE_CHINA){
                wrapper.eq("dest_id", qo.getRefid());
            }else if(qo.getType() == StrategyCondition.TYPE_THEME){
                wrapper.eq("theme_id", qo.getRefid());
            }
        }

        return super.page(page, wrapper);
    }

    @Override
    public boolean saveOrUpdate(Strategy entity) {

        //目的地id跟name
        //分类名称
        StrategyCatalog catalog = strategyCatalogService.getById(entity.getCatalogId());
        entity.setCatalogName(catalog.getName());
        entity.setDestId(catalog.getDestId());
        entity.setDestName(catalog.getDestName());

        //主题name
        StrategyTheme theme = strategyThemeService.getById(entity.getThemeId());
        entity.setThemeName(theme.getName());

        //是否国外
        List<Destination> toasts = destinationService.queryToasts(catalog.getDestId());
        if(toasts != null && toasts.size() > 0){
            if("中国".equals(toasts.get(0).getName())){
                entity.setIsabroad(Strategy.ABROAD_NO);
            }else{
                entity.setIsabroad(Strategy.ABROAD_YES);
            }
        }

        boolean flag = false;
        if(entity.getId() == null){
            //创建时间
            entity.setCreateTime(new Date());
            //各种统计数
            entity.setFavornum(0);
            entity.setReplynum(0);
            entity.setSharenum(0);
            entity.setViewnum(0);
            entity.setThumbsupnum(0);
            flag = super.save(entity);
            //内容
            StrategyContent content = entity.getContent();
            content.setId(entity.getId());
            strategyContentMapper.insert(content);

        }else{
            //更新
            flag = super.updateById(entity);
            //内容
            StrategyContent content = entity.getContent();
            content.setId(entity.getId());
            strategyContentMapper.updateById(content);
        }

        //同步修改-不建议---->异步修改
        //esService.update(es);

        return flag;
    }

    @Override
    public StrategyContent getContent(Long id) {
        return strategyContentMapper.selectById(id);
    }

    @Override
    public List<Strategy> queryViewnumTop3(Long destId) {
        //1:查询条件：destid
        //2：排序viewnum 倒序
        //3: 取3个
        QueryWrapper<Strategy> wrapper = WrapperUtil.query(Strategy.class)
                .eq("dest_id", destId)
                .orderByDesc("viewnum")
                .last("limit 3");
        return super.list(wrapper);
    }

    @Override
    public void updateStatisVo(StrategyStatisVO vo) {
        UpdateWrapper<Strategy> wrapper = WrapperUtil.update(Strategy.class)
                .eq("id", vo.getStrategyId())
                .set("viewnum", vo.getViewnum())
                .set("replynum", vo.getReplynum())
                .set("favornum", vo.getFavornum())
                .set("sharenum", vo.getSharenum())
                .set("thumbsupnum", vo.getThumbsupnum());
        super.update(wrapper);
    }

    @Override
    public List<Strategy> queryByDestId(Long destId) {
        QueryWrapper<Strategy> wrapper = WrapperUtil.query(Strategy.class)
                .eq("dest_id", destId);
        return super.list(wrapper);
    }
}
