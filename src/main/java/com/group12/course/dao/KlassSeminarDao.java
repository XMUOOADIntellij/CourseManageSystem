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
         return klassSeminarMapper.selectKlassSeminarBySeminarIdAndKlassId(seminarId,classId);
    }

    public KlassSeminar selectKlassSeminarById(Long id){
        return klassSeminarMapper.selectKlassSeminarById(id);
    }

    public List<KlassSeminar> selectKlassSeminarBySeminarId(Long seminarId){
        return  klassSeminarMapper.selectKlassSeminarBySeminarId(seminarId);
    }

    public Integer insertByList(List<KlassSeminar> record){
        return  klassSeminarMapper.insertByList(record);
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

    public List<KlassSeminar> selectKlassSeminarBySeminarIdList(List<Long> seminarId){
        return klassSeminarMapper.selectKlassSeminarBySeminarIdList(seminarId);
    }
}
