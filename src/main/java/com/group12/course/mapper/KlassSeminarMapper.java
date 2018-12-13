package com.group12.course.mapper;

import com.group12.course.entity.KlassSeminar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface KlassSeminarMapper {

    int delete(Long id);

    int insert(KlassSeminar record);

    KlassSeminar selectKlassSeminarById(Long id);

    int update(KlassSeminar record);

}