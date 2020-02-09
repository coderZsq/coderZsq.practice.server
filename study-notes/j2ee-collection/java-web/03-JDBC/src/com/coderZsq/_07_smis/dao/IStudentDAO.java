package com.coderZsq._07_smis.dao;

import com.coderZsq._07_smis.domain.Student;

import java.util.List;

public interface IStudentDAO {
    void save(Student stu);

    void delete(Long id);

    void update(Long id, Student stu);

    Student get(Long id);

    List<Student> listAll();
}
