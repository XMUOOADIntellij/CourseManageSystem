package com.group12.course.mapper;

import com.group12.course.entity.KlassStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author Tan Xue
 */
@Mapper
@Component
public interface KlassStudentMapper {

    /**
     * 根据课程id选择班级学生记录
     * @param courseId
     * @return
     */
    List<KlassStudent> selectKlassStudentByCourseId(Long courseId);

    /**
     * 根据学生id选择班级学生记录
     * @param studentId
     * @return
     */
    List<KlassStudent> selectKlassStudentByStudentId(Long studentId);

    /**
     * 根据班级id选择班级学生记录
     * @param klassId
     * @return
     */
    List<KlassStudent> selectKlassStudentByKlassId(Long klassId);

    /**
     * 根据班级id删除班级学生记录
     * @param klassId
     * @return
     */
    int deleteKlassStudentByKlassId(Long klassId);

    /**
     * 根据学生id删除班级学生记录
     * @param studentId
     * @return
     */
    int deleteKlassStudentByStudentId(Long studentId);

    /**
     * 根据班级id和学生id删除班级学生记录
     * @param klassId
     * @param studentId
     * @return
     */
    int deleteKlassStudentByKlassIdAndStudentId(@Param("klassId") Long klassId,@Param("studentId") Long studentId);

    /**
     * 添加班级学生记录
     * @param klassStudent
     * @return
     */
    int addKlassStudent(@Param("klassStudent") KlassStudent klassStudent);


    /**
     * 根据课程id和学号学生id选择班级学生记录
     * @param courseId
     * @param studentId
     * @return
     */
    KlassStudent selectKlassStudentByCourseIdAndStudentId(@Param("courseId") Long courseId,@Param("studentId") Long studentId);

}
