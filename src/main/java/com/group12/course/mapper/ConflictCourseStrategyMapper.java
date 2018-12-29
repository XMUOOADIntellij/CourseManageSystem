package com.group12.course.mapper;

import com.group12.course.entity.Course;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 */
@Mapper
@Component
public interface ConflictCourseStrategyMapper {

    /**
     * 根据id返回冲突课程
     * @param id
     * @return
     */
    List<ConflictCourseStrategy> selectConflictCourseStrategyById(Long id);

    /**
     * 删除冲突课程记录
     * @param id
     * @return
     */
    int deleteConflictCourseStrategy(Long id);

    /**
     * 添加冲突课程记录
     * @param record
     * @return
     */
    int addConflictCourseStrategy(ConflictCourseStrategy record);


    /**
     * 列出所有的记录
     * @return
     */
    List<ConflictCourseStrategy> selectAllConflictCourseStrategy();


}
