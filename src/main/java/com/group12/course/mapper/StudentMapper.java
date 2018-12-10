package com.group12.course.mapper;

import com.group12.course.entity.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Long account);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long account);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}