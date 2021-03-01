package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.mapper.DestinationMapper;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IRegionService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class DestinationServiceImpl extends ServiceImpl<DestinationMapper, Destination> implements IDestinationService {
    @Autowired
    private IRegionService regionService;
    //已知：区域id --->求： 目的地集合
    @Override
    public List<Destination> queryByRegionId(Long rid) {
        //1:通过id查区域对象，得到目的地id集合
        Region region = regionService.getById(rid);
        List<Long> ids = region.parseRefIds();
        //2:通过目的地id集合查询目的地对象集合
        return super.listByIds(ids);
    }

    //已知：区域id --->求： 目的地集合
    @Override
    public List<Destination> queryByRegionIdForApi(Long regionId) {
        List<Destination> list = new ArrayList<>();
        QueryWrapper<Destination> wrapper = WrapperUtil.query(Destination.class);
        //国内
        if(-1 == regionId){
            wrapper.eq("parent_name", "中国");
            //查询中国所有省份
            list = super.list(wrapper);
        }else{
            //非国内
            list = this.queryByRegionId(regionId);
        }
        //第二层： 每个目的地下的子目的地关联查询
        //方案1：mapper.xml   关联查询或者额外sql查询
        //方案2：内存中拼接(等价于额外sql)
        for (Destination dest : list) {
            //清理所有条件
            wrapper.clear();
            wrapper.eq("parent_id", dest.getId())
                   .last(" limit 5");  //在sql语句最后拼接 limit 5语句
            //查询子目的地
            List<Destination> children = super.list(wrapper);
            dest.setChildren(children);
        }
        return list;
    }


    @Override
    public IPage<Destination> queryPage(DestinationQuery qo) {
        IPage<Destination> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Destination> wrapper = Wrappers.<Destination>query()
                .like(StringUtils.hasLength(qo.getKeyword()), "name", qo.getKeyword())
                .isNull(qo.getParentId() == null, "parent_id")  //where parent_id is null
                .eq(qo.getParentId() != null, "parent_id", qo.getParentId())  //parent_id = pid
                ;
        return super.page(page, wrapper);
    }


    // 根>>中国>>广东>>广州
    @Override
    public List<Destination> queryToasts(Long destId) {

        List<Destination> list = new ArrayList<>();

       /* if(destId != null){
            Destination d = super.getById(destId);  //广州
            list.add(d);
            if(d.getParentId() != null){
                Destination dd = super.getById(destId);  //广东
                list.add(dd);
                if(dd.getParentId() != null){
                    Destination ddd = super.getById(destId);  //广东
                    list.add(ddd);
                }
            }
        }*/

       //方案： 1：循环， 2：递归

        creatToast(list, destId);

        Collections.reverse(list);  //ABC ->  CBA

        return list;
    }
    //根>>中国>>广东>>广州
    private void creatToast(List<Destination> list, Long destId){
        if(destId == null){
            return;
        }
        Destination dest = super.getById(destId);
        list.add(dest);
        if(dest.getParentId() != null){
            creatToast(list, dest.getParentId());
        }
    }


    @Override
    public Destination queryByName(String name) {
        return super.getOne( WrapperUtil.query(Destination.class).eq("name", name));
    }
}
