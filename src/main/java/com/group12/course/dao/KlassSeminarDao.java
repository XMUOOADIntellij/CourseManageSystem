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


    public KlassSeminar selectKlassSeminarBySeminarIdAndClassId(Long seminarId,Long classId){
         return klassSeminarMapper.listKlassSeminarBySeminarIdAndKlassId(seminarId,classId);
    }

    public KlassSeminar selectKlassSeminarById(Long id){
        return klassSeminarMapper.selectKlassSeminarById(id);
    }

    public List<KlassSeminar> listKlassSeminarBySeminarId(Long seminarId){
        return  klassSeminarMapper.listKlassSeminarBySeminarId(seminarId);
    }

    public Integer insertKlassSeminarList(List<KlassSeminar> record){
        return  klassSeminarMapper.insertKlassSeminarList(record);
    }

    public Integer deleteKlassSeminarBySeminarId(Long seminarId){
        return  klassSeminarMapper.deleteBySeminarId(seminarId);
    }

    public Integer updateKlassSeminar(KlassSeminar klassSeminar){
        if(klassSeminar.getId()!=null){
        return  klassSeminarMapper.updateKlassSeminar(klassSeminar);}
        else{
            return null;
        }
    }

    public List<KlassSeminar> listKlassSeminarBySeminarIdList(List<Long> seminarId){
        return klassSeminarMapper.listKlassSeminarBySeminarIdList(seminarId);
    }
}
