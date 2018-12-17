package com.group12.course.dao;


import com.group12.course.entity.strategy.CourseMemberLimitStrategy;
import com.group12.course.mapper.CourseMemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseMemberLimitStrategyDao {

    @Autowired
    CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    CourseMemberLimitStrategy selectCourseMemberLimitStrategyById(Long id){
        return courseMemberLimitStrategyMapper.selectCourseMemberLimitStrategyById(id);
    }

    int deleteCourseMemberLimitStrategy(Long id){
        return courseMemberLimitStrategyMapper.deleteCourseMemberLimitStrategy(id);
    }

    int addCourseMemberLimitStrategy(CourseMemberLimitStrategy record){
        return courseMemberLimitStrategyMapper.addCourseMemberLimitStrategy(record);
    }

    int updateCourseMemberLimitStrategy(CourseMemberLimitStrategy record){
        return courseMemberLimitStrategyMapper.updateCourseMemberLimitStrategy(record);
    }
}
