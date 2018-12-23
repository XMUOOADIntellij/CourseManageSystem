package com.group12.course.mapper;

import com.group12.course.entity.strategy.TeamAndStrategy;
import com.group12.course.entity.strategy.TeamOrStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TeamOrStrategyMapper {
    TeamOrStrategy selectTeamOrStrategyById(Long id);
}
