package com.group12.course.mapper;

import com.group12.course.entity.strategy.TeamStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TeamStrategyMapper {
    int deleteTeamStrategyByCourseId(Long CourseId);

    int addTeamStrategy(Long courseId,Long strategyId,String strategyName);

    TeamStrategy selectTeamStrategyByCourseId(Long id);

    int updateTeamStrategy(TeamStrategy teamStrategy);

    TeamStrategy selectTeamStrategyByCourseIdAndStrategyName(Long courseId,String strategyName);
}
