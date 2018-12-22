package com.group12.course.mapper;

import com.group12.course.entity.KlassSeminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface KlassSeminarMapper {

    Integer deleteById(Long id);

    Integer deleteBySeminarId(Long seminarId);

    Integer insert(KlassSeminar record);

    KlassSeminar selectKlassSeminarById(Long id);

    KlassSeminar selectKlassSeminarBySeminarIdAndKlassId(Long seminarId, Long classId);

    List<KlassSeminar> selectKlassSeminarBySeminarId(Long seminarId);

    Integer updateKlassSeminar(KlassSeminar record);

    Integer insertKlassSeminarList(List<KlassSeminar> list);

    List<KlassSeminar> selectKlassSeminarBySeminarIdList(@Param("seminarId") List<Long> seminarId);
}