package com.coderZsq._05_smis.dao;

import com.coderZsq._05_smis.domain.Student;

import java.util.List;

// 封装Student对象的CRUD操作
public interface IStudentDAO {
    /**
     * 保存操作
     *
     * @param stu 学生对象, 封装了需要保存的信息
     */
    void save(Student stu);

    /**
     * 删除操作
     *
     * @param id 被删除学生的主键值
     */
    void delete(Long id);

    /**
     * 更新操作
     *
     * @param id     被更改学生的主键值
     * @param newStu 学生新的信息
     */
    void update(Long id, Student newStu);

    /**
     * 查询指定ID的学生对象
     *
     * @param id 被查询学生的主键值
     * @return 如果id存在, 返回该学生对象, 否则, 返回null
     */
    Student get(Long id);

    /**
     * 查询并返回所有学生对象
     * @return 如果结果集为空, 返回一个空的List对象
     */
    List<Student> listAll();
}
