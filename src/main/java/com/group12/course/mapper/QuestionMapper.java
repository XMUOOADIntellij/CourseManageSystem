package com.group12.course.mapper;

import com.group12.course.entity.Question;

/**
 * 讨论课提问的Mapper
 * @author Y Jiang
 * @date 2018/12/12
 */
public interface QuestionMapper {

    int delete(Long id);

    int insert(Question record);

    Question select(Long id);

    int update(Question record);

}