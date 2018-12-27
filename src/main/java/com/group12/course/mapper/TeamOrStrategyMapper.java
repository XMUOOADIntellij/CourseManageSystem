package com.group12.course.mapper;

import com.group12.course.entity.strategy.TeamOrStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamOrStrategyMapper {
    List<TeamOrStrategy> selectTeamOrStrategyById(Long id);

    int addTeamOrStrategy(@Param("id") Long id,@Param("strategyName") String strategyName, @Param("strategyId") Long strategyId);

    List<TeamOrStrategy> selectAllTeamOrStrategy();
}
