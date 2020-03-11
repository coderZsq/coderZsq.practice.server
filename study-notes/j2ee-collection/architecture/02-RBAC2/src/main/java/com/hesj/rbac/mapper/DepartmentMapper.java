package com.hesj.rbac.mapper;

import com.hesj.rbac.domain.Department;
import com.hesj.rbac.qo.QueryObject;
import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    List<Department> selectByQuery(QueryObject qo);

    int selectByCount();
}