package com.group12.course.dao;

import com.group12.course.entity.Seminar;
import com.group12.course.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeminarDao {

    @Autowired
    SeminarMapper seminarMapper;

    public int createSeminar(Seminar record){
        //TODO Seminar 表插入一条记录
        //TODO courseId -> Course表找到班级->classSeminar表插入班级讨论课记录
        //TODO 没有roundId  -> Round表(新建）
        return 0;
    }

    public int deleteSeminar(){
        //TODO seminarId 删除class_seminar记录-》删除attendance
        //TODO Seminar表删除记录
        return 0;
    }

    public Seminar selectSeminar(Long id){
        //
        return null;
    }

}
