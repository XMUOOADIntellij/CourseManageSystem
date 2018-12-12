package com.group12.course.mapper;

import com.group12.course.entity.Question;

public interface QuestionMapper {

    int delete(Long id);

    int insert(Question record);

    Question select(Long id);

    int update(Question record);

}