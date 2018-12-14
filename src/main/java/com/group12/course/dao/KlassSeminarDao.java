package com.group12.course.dao;

import com.group12.course.dto.KlassSeminarDto;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.mapper.KlassSeminarMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KlassSeminarDao {
    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    public KlassSeminarDto getKlassSeminar(@Param("seminarId") Long seminarId,@Param("classId") Long classId){
         return klassSeminarMapper.selectKlassSeminar(seminarId,classId);
    }
}
