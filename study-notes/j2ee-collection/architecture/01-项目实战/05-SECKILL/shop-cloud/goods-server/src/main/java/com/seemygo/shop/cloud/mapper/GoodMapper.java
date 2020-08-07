package com.seemygo.shop.cloud.mapper;

import com.seemygo.shop.cloud.domain.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
@Mapper
public interface GoodMapper {

    @SelectProvider(type = GoodSelectProvider.class, method = "selectListByIdList")
    List<Good> selectListByIdList(@Param("idList") Set<Long> idList);

    class GoodSelectProvider {

        public String selectListByIdList(@Param("idList") Set<Long> idList) {
            StringBuilder sql = new StringBuilder(100).append("select * from t_goods ");
            if (CollectionUtils.isEmpty(idList)) {
                return sql.toString();
            }

            sql.append(" where id in (");
            // select * from t_goods where id in (
            for (Long id : idList) {
                sql.append(id).append(",");
            }

            // select * from t_goods where id in (1,2,3,
            // 删除最后的,
            sql.deleteCharAt(sql.length()-1).append(")");
            // select * from t_goods where id in (1,2,3)

            return sql.toString();
        }
    }
}
