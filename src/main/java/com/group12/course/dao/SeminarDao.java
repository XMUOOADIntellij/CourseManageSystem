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
        return 0;
    }

    public int deleteSeminar(){
        return 0;
    }

    public Seminar selectSeminar(){
        return null;
    }

}
