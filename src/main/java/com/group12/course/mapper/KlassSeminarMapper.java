package com.group12.course.mapper;

import com.group12.course.entity.KlassSeminar;

public interface KlassSeminarMapper {

    int delete(Long id);

    int insert(KlassSeminar record);

    KlassSeminar select(Long id);

    int update(KlassSeminar record);

}