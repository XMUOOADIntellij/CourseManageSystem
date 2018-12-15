package com.group12.course.dao;

import com.group12.course.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeminarScoreDao {
    @Autowired
    SeminarScoreMapper seminarScoreMapper;

    public Integer deleteSeminarScoreByKlassSeminarId(Long klassSeminarId){
        return seminarScoreMapper.deleteSeminarScoreByKlassSeminarId(klassSeminarId);
    }
}
