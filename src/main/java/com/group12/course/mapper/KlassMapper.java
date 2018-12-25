package com.group12.course.mapper;

import com.group12.course.entity.Course;
import com.group12.course.entity.Klass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Klass Mapper 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Mapper
@Component
public interface KlassMapper {

    /**
     * 删除班级
     * @param id 班级编号
     * @return 删除数量
     */
    int deleteKlass(Long id);

    /**
     * 添加班级
     * @param record 班级
     * @return 添加数量
     */
    int addKlass(Klass record);

    /**
     * 查询班级
     * @param id 班级编号
     * @return 班级
     */
    Klass selectKlassById(Long id);

    /**
     * 修改班级信息
     * @param record 班级
     * @return 修改数量
     */
    int updateKlass(Klass record);

    /**
     * 查询某一课程下的所有班级
     * @param courseId 课程ID
     * @return 班级列表
     */
    List<Klass> getAllKlassByCourseId(Long courseId);

    /**
     * 删除某一班级与班级下所有学生的关联
     * @param klassId
     * @return
     */
    int deleteStudentByKlassId(Long klassId);

    /**
     * 获取课程列表下的所有班级
     * @param courseIdList
     * @return
     */
    List<Klass> getAllKlassByCourseIdList(@Param("courseIdList") List<Long> courseIdList);

}