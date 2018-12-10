package com.group12.course.mapper;

import com.group12.course.entity.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(Long account);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long account);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}