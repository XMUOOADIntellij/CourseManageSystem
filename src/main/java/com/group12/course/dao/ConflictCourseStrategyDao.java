package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import com.group12.course.mapper.ConflictCourseStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConflictCourseStrategyDao {

    @Autowired
    ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    public ConflictCourseStrategy selectConflictCourseStrategyById(Long id){
        return conflictCourseStrategyMapper.selectConflictCourseStrategyById(id);
    }

    public int deleteConflictCourseStrategy(Long id){
        return conflictCourseStrategyMapper.deleteConflictCourseStrategy(id);
    }

    public int addConflictCourseStrategy(ConflictCourseStrategy record){
        return conflictCourseStrategyMapper.addConflictCourseStrategy(record);
    }

    public int updateConflictCourseStrategy(ConflictCourseStrategy record){
        return conflictCourseStrategyMapper.updateConflictCourseStrategy(record);
    }

    public List<ConflictCourseStrategy> selectConflictCourseStrategyByCourseId(Long courseId){
        return conflictCourseStrategyMapper.selectConflictCourseStrategyByCourseId(courseId);
    }
}
