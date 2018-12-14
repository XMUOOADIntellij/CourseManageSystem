package com.group12.course.mapper;

import com.group12.course.dto.KlassSeminarDto;
import com.group12.course.entity.KlassSeminar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface KlassSeminarMapper {

    int delete(Long id);

    int insert(KlassSeminar record);

    KlassSeminar selectKlassSeminarById(Long id);

    KlassSeminarDto selectKlassSeminar(Long seminarId, Long classId);

    int update(KlassSeminar record);

}