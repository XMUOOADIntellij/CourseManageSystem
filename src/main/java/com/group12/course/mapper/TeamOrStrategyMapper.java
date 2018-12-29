package com.group12.course.mapper;

import com.group12.course.entity.strategy.TeamOrStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 */
@Mapper
@Component
public interface TeamOrStrategyMapper {
    /**
     * 查询
     * @param id
     * @return
     */
    List<TeamOrStrategy> selectTeamOrStrategyById(Long id);

    /**
     * 添加
     * @param id
     * @param strategyName
     * @param strategyId
     * @return
     */
    int addTeamOrStrategy(@Param("id") Long id,@Param("strategyName") String strategyName, @Param("strategyId") Long strategyId);

    /**
     * 查询
     * @return
     */
    List<TeamOrStrategy> selectAllTeamOrStrategy();
}
