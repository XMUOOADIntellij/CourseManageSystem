package com.group12.course.dao;


import com.group12.course.entity.strategy.CourseMemberLimitStrategy;
import com.group12.course.mapper.CourseMemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMemberLimitStrategyDao {

    @Autowired
    CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    public CourseMemberLimitStrategy selectCourseMemberLimitStrategyById(Long id){
        return courseMemberLimitStrategyMapper.selectCourseMemberLimitStrategyById(id);
    }

    public int deleteCourseMemberLimitStrategy(Long id){
        return courseMemberLimitStrategyMapper.deleteCourseMemberLimitStrategy(id);
    }

    public int addCourseMemberLimitStrategy(CourseMemberLimitStrategy record){
        return courseMemberLimitStrategyMapper.addCourseMemberLimitStrategy(record);
    }

    public int updateCourseMemberLimitStrategy(CourseMemberLimitStrategy record){
        return courseMemberLimitStrategyMapper.updateCourseMemberLimitStrategy(record);
    }
}
