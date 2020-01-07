package com.hesj.demo.impl;

import com.hesj.demo.IDepartmentService;
import com.hesj.demo.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired //去IoC容器 根据类型
    private Department department;

    @Override
    public Department get(Long id) {
        System.out.println(department);
        System.out.println("DepartmentServiceImpl.get");
        return null;
    }
}
