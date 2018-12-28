package com.group12.course.mapper;

import com.group12.course.entity.strategy.TeamStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamStrategyMapper {
    int deleteTeamStrategyByCourseId(Long courseId);

    int addTeamStrategy(@Param("courseId") Long courseId,@Param("strategySerial") Integer strategySerial,@Param("strategyName") String strategyName,@Param("strategyId") Long strategyId);

    List<TeamStrategy> selectTeamStrategyByCourseId(Long id);

    int updateTeamStrategy(TeamStrategy teamStrategy);

    TeamStrategy selectTeamStrategyByCourseIdAndStrategyName(@Param("courseId") Long courseId,@Param("strategyName") String strategyName);
}
