package com.group12.course.dao;

import com.group12.course.entity.KlassStudent;
import com.group12.course.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KlassStudentDao {

    @Autowired
    KlassStudentMapper klassStudentMapper;


    /**
     * 根据课程id 查询学生班级记录
     * @param courseId
     * @return
     */
    public List<KlassStudent> selectKlassStudentByCourseId(Long courseId){
        return klassStudentMapper.selectKlassStudentByCourseId(courseId);
    }

    /**
     * 根据学生id 查询学生班级记录
     * @param studentId
     * @return
     */
    public List<KlassStudent> selectKlassStudentByStudentId(Long studentId){
        return klassStudentMapper.selectKlassStudentByStudentId(studentId);
    }

    /**
     * 根据班级id 删除学生班级记录
     * @param klassId
     * @return
     */
    public int deleteKlassStudentByKlassId(Long klassId){
        return klassStudentMapper.deleteKlassStudentByKlassId(klassId);
    }

    /**
     * 添加班级学生记录
     * @param klassStudent
     * @return
     */
    public int addKlassStudent(KlassStudent klassStudent){
        return klassStudentMapper.addKlassStudent(klassStudent);
    }

}
