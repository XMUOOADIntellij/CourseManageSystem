package com.group12.course.mapper;

import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.SeminarScore;

public interface SeminarScoreMapper {

    int delete(Long id);

    int insert(SeminarScore record);

    SeminarScore select(Long id);

    int update(SeminarScore record);
}
