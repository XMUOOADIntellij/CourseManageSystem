package com.group12.course.dao;

import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Seminar;
import com.group12.course.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeminarDao {

    @Autowired
    SeminarMapper seminarMapper;

    /**
     * 创建课程讨论课
     * @param record 讨论课记录
     * @return  id
     */
    public Long createSeminar(Seminar record){
        seminarMapper.insertSeminar(record);
        return record.getId();
    }

    /**
     * 根据id删除课程讨论课
     * @param seminarId 讨论课id
     * @return 1成功 0失败
     */
    public Integer deleteSeminarById(Long seminarId){
        return seminarMapper.deleteSeminarById(seminarId);
    }

    public Seminar selectSeminarById(Long id){
        return seminarMapper.selectSeminarById(id);
    }

    public Integer updateSeminar(Seminar seminar,Long seminarId){
        return seminarMapper.updateSeminar(seminar,seminarId);
    }

}
