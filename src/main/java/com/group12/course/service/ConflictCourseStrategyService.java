package com.group12.course.service;

import com.group12.course.dao.ConflictCourseStrategyDao;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConflictCourseStrategyService {
    @Autowired
    ConflictCourseStrategyDao conflictCourseStrategyDao;

    public int addConflicCourseStrategy(ConflictCourseStrategy conflictCourseStrategy){
        return conflictCourseStrategyDao.addConflictCourseStrategy(conflictCourseStrategy);
    }

}
