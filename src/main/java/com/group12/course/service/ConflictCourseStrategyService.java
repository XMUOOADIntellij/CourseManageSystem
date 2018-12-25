package com.group12.course.service;

import com.group12.course.dao.ConflictCourseStrategyDao;
import com.group12.course.entity.Course;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConflictCourseStrategyService {
    @Autowired
    ConflictCourseStrategyDao conflictCourseStrategyDao;

    public int addConflicCourseStrategy(ConflictCourseStrategy conflictCourseStrategy){
        return conflictCourseStrategyDao.addConflictCourseStrategy(conflictCourseStrategy);
    }

    public List<Course> getConflictCourse(Long courseId){
        List<Course> courseList = new ArrayList<>();

        ConflictCourseStrategy record = conflictCourseStrategyDao.selectConflictCourseStrategyByCourseId(courseId);
        List<ConflictCourseStrategy> conflictCourseStrategyList = conflictCourseStrategyDao.selectConflictCourseStrategyById(record.getId());

        for (ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategyList) {
            courseList.add(conflictCourseStrategy.getCourse());
        }

        return courseList;
    }

}
