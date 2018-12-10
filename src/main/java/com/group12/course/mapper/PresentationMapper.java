package com.group12.course.mapper;

import com.group12.course.entity.Presentation;

public interface PresentationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Presentation record);

    int insertSelective(Presentation record);

    Presentation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Presentation record);

    int updateByPrimaryKey(Presentation record);
}