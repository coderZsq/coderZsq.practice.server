package cn.wolfcode.wolf2w.util;

import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.domain.Strategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * wrapper工具类使用
 */
public class WrapperUtil {
    //WrapperUtil.query(Strategy.class);
    public static <T> QueryWrapper<T> query(Class<T> clz){

        //Class<Strategy> ss = Strategy.class;
        return Wrappers.<T>query();
    }
    public static <T> UpdateWrapper<T> update(Class<T> clz){
        return Wrappers.<T>update();
    }
}
