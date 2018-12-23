package com.group12.course.mapper;

import com.group12.course.entity.strategy.TeamAndStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TeamAndStrategyMapper {

    TeamAndStrategy selectTeamAndStrategyById(Long id);

}
