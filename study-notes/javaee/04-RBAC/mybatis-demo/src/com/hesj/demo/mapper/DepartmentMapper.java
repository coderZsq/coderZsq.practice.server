package com.hesj.demo.mapper;

import com.hesj.demo.domain.Department;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
public interface DepartmentMapper {

    public Department selectByPrimarykey(Long id);


    public int  delete(Long id);
}
