package com.group12.course.mapper;

import com.group12.course.entity.strategy.TeamStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 */
@Mapper
@Component
public interface TeamStrategyMapper {
    /**
     * 删除
     * @param courseId
     * @return
     */
    int deleteTeamStrategyByCourseId(Long courseId);

    /**
     * 添加
     * @param courseId
     * @param strategySerial
     * @param strategyName
     * @param strategyId
     * @return
     */
    int addTeamStrategy(@Param("courseId") Long courseId,@Param("strategySerial") Integer strategySerial,@Param("strategyName") String strategyName,@Param("strategyId") Long strategyId);

    /**
     * 根据课程id查询
     * @param id
     * @return
     */
    List<TeamStrategy> selectTeamStrategyByCourseId(Long id);

    /**
     * 修改
     * @param teamStrategy
     * @return
     */
    int updateTeamStrategy(TeamStrategy teamStrategy);

    /**
     * 查询
     * @param courseId
     * @param strategyName
     * @return
     */
    TeamStrategy selectTeamStrategyByCourseIdAndStrategyName(@Param("courseId") Long courseId,@Param("strategyName") String strategyName);
}
