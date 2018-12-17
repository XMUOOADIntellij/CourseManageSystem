package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import com.group12.course.mapper.ConflictCourseStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConflictCourseStrategyDao {

    @Autowired
    ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    ConflictCourseStrategy selectConflictCourseStrategyById(Long id){
        return conflictCourseStrategyMapper.selectConflictCourseStrategyById(id);
    }

    int deleteConflictCourseStrategy(Long id){
        return conflictCourseStrategyMapper.deleteConflictCourseStrategy(id);
    }

    int addConflictCourseStrategy(ConflictCourseStrategy record){
        return conflictCourseStrategyMapper.addConflictCourseStrategy(record);
    }

    int updateConflictCourseStrategy(ConflictCourseStrategy record){
        return conflictCourseStrategyMapper.updateConflictCourseStrategy(record);
    }


}
