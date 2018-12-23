package com.group12.course.mapper;

import com.group12.course.entity.Course;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ConflictCourseStrategyMapper {

    ConflictCourseStrategy selectConflictCourseStrategyById(Long id);

    int deleteConflictCourseStrategy(Long id);

    int addConflictCourseStrategy(ConflictCourseStrategy record);

    int updateConflictCourseStrategy(ConflictCourseStrategy record);

    /**
     * 根据课程id找到冲突课程记录
     * @param courseId
     * @return
     */
    List<ConflictCourseStrategy> selectConflictCourseStrategyByCourseId(Long courseId);

}
