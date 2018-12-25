package com.group12.course.mapper;

import com.group12.course.entity.strategy.TeamAndStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamAndStrategyMapper {

    List<TeamAndStrategy> selectTeamAndStrategyById(Long id);

    int addTeamAndStrategy(TeamAndStrategy teamAndStrategy);

}
