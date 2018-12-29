package com.group12.course.mapper;

import com.group12.course.entity.strategy.TeamAndStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 */
@Mapper
@Component
public interface TeamAndStrategyMapper {

    /**
     * 查询
     * @param id
     * @return
     */
    List<TeamAndStrategy> selectTeamAndStrategyById(Long id);

    /**
     * 添加
     * @param id
     * @param strategyName
     * @param strategyId
     * @return
     */
    int addTeamAndStrategy(@Param("id") Long id, @Param("strategyName") String strategyName, @Param("strategyId") Long strategyId);

    /**
     * 查询
     * @return
     */
    List<TeamAndStrategy> selectAllTeamAndStrategy();
}
