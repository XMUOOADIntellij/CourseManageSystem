package com.group12.course.mapper;

import com.group12.course.entity.strategy.CourseMemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CourseMemberLimitStrategyMapper {

    CourseMemberLimitStrategy selectCourseMemberLimitStrategyById(Long id);

    int deleteCourseMemberLimitStrategy(Long id);

    int addCourseMemberLimitStrategy(CourseMemberLimitStrategy record);

    int updateCourseMemberLimitStrategy(CourseMemberLimitStrategy record);
}
