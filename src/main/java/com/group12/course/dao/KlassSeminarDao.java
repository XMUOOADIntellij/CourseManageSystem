package com.group12.course.dao;

import com.group12.course.entity.KlassSeminar;
import com.group12.course.mapper.KlassSeminarMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KlassSeminarDao {
    @Autowired
    KlassSeminarMapper klassSeminarMapper;


    public KlassSeminar getKlassSeminarBySeminarIdAndClassId(@Param("seminarId") Long seminarId,@Param("classId") Long classId){
         return klassSeminarMapper.selectKlassSeminarBySeminarIdAndKlassId(seminarId,classId);
    }

    public KlassSeminar getKlassSeminarById(Long id){
        return klassSeminarMapper.selectKlassSeminarById(id);
    }

    public List<KlassSeminar> getKlassSeminarBySeminarId(Long seminarId){
        return  klassSeminarMapper.selectKlassSeminarBySeminarId(seminarId);
    }

    public Integer insertByList(List<KlassSeminar> record){
        return  klassSeminarMapper.insertByList(record);
    }

    public Integer deleteKlassSeminarBySeminarId(Long seminarId){
        return  klassSeminarMapper.deleteBySeminarId(seminarId);
    }

    public Integer updateKlassSeminar(Long seminarId,Long classId,KlassSeminar klassSeminar){
        return  klassSeminarMapper.updateKlassSeminar(klassSeminar,
                klassSeminarMapper.selectKlassSeminarBySeminarIdAndKlassId(seminarId, classId).getId());
    }
}
