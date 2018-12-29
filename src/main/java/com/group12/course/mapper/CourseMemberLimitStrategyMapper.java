package com.group12.course.mapper;

import com.group12.course.entity.strategy.CourseMemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Tan Xue
 */
@Mapper
@Component
public interface CourseMemberLimitStrategyMapper {

    /**
     * 查询课程人数限制策略记录
     * @param id
     * @return
     */
    CourseMemberLimitStrategy selectCourseMemberLimitStrategyById(Long id);

    /**
     * 删除课程人数限制策略
     * @param id
     * @return
     */
    int deleteCourseMemberLimitStrategy(Long id);

    /**
     * 添加课程人数限制策略
     * @param record
     * @return
     */
    int addCourseMemberLimitStrategy(CourseMemberLimitStrategy record);

    /**
     * 修改课程人数限制策略
     * @param record
     * @return
     */
    int updateCourseMemberLimitStrategy(CourseMemberLimitStrategy record);
}
