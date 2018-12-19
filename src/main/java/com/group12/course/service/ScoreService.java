package com.group12.course.service;

import com.group12.course.dao.RoundScoreDao;


import com.group12.course.dao.ScoreDao;
import com.group12.course.entity.SeminarScore;
import com.group12.course.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    RoundScoreDao roundScoreDao;

    public Integer modifyScore(Teacher teacher, SeminarScore seminarScore){
        return null;
    }
}
