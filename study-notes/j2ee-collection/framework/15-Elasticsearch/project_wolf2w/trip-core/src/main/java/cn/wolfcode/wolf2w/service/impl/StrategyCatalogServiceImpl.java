package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.StrategyCatalog;
import cn.wolfcode.wolf2w.mapper.StrategyCatalogMapper;
import cn.wolfcode.wolf2w.query.StrategyCatalogQuery;
import cn.wolfcode.wolf2w.service.IStrategyCatalogService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import cn.wolfcode.wolf2w.vo.CatalogVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StrategyCatalogServiceImpl extends ServiceImpl<StrategyCatalogMapper, StrategyCatalog> implements IStrategyCatalogService {


    @Autowired
    private IStrategyService strategyService;


    @Override
    public IPage<StrategyCatalog> queryPage(StrategyCatalogQuery qo) {
        IPage<StrategyCatalog> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<StrategyCatalog> wrapper = WrapperUtil.query(StrategyCatalog.class);
        return super.page(page, wrapper);
    }
   /* select
    dest_name, GROUP_CONCAT(id) ids,GROUP_CONCAT(name) names
    from strategy_catalog
    group by dest_name*/

    @Override
    public List<CatalogVO> queryGroupList() {
        List<CatalogVO> list = new ArrayList<>();
        QueryWrapper<StrategyCatalog> wrapper = WrapperUtil.query(StrategyCatalog.class)
                .select("dest_name, GROUP_CONCAT(id) ids,GROUP_CONCAT(name) names")
                .groupBy("dest_name");
        List<Map<String, Object>> mapList = super.listMaps(wrapper);
        for (Map<String, Object> map : mapList) {
            String destName = map.get("dest_name").toString();  //上海
            String idStr = map.get("ids").toString();   //4,5,6
            String nameStr = map.get("names").toString(); //最上海,主题乐园,上海不容错过
            List<StrategyCatalog> catalogList = this.createCatalog(idStr, nameStr);
            CatalogVO vo = new  CatalogVO(destName, catalogList);
            list.add(vo);
        }
        return list;
    }
    //解析字符串得到攻略分类对象集合
    private List<StrategyCatalog> createCatalog(String idStr, String nameStr){
        //攻略分类集合
        List<StrategyCatalog> list = new ArrayList<>();
        //4,5,6
        //最上海,主题乐园,上海不容错过
        String[] ids = idStr.split(",");
        String[] names = nameStr.split(",");
        for(int i = 0; i < ids.length; i++){
            Long id = Long.valueOf(ids[i]);
            String name = names[i];
            StrategyCatalog sc = new StrategyCatalog();
            sc.setId(id);
            sc.setName(name);
            list.add(sc);
        }
        return  list;
    }

    @Override
    public List<StrategyCatalog> queryByDestId(Long destId) {
        //1:指定目的地下所有攻略分类， 并排序
        QueryWrapper<StrategyCatalog> wrapper = WrapperUtil.query(StrategyCatalog.class)
                .eq("dest_id", destId)
                .eq("state", StrategyCatalog.STATE_NORMAL)
                .orderByAsc("seq");
        List<StrategyCatalog> list = super.list(wrapper);

        QueryWrapper<Strategy> wp = WrapperUtil.query(Strategy.class);
        //2:查询每个分类下所有的攻略明细
        for (StrategyCatalog sc : list) {
            wp.eq("catalog_id", sc.getId());
            List<Strategy> sts = strategyService.list(wp);
            sc.setStrategies(sts);
            wp.clear();
        }
        return list;
    }
}
