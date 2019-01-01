package com.group12.course.dao;

import com.group12.course.entity.Klass;
import com.group12.course.entity.KlassStudent;
import com.group12.course.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 */
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

    public List<KlassStudent> selectKlassStudentByKlassId(Long klassId){
        return klassStudentMapper.selectKlassStudentByKlassId(klassId);
    }

    /**
     * 根据班级id 删除学生班级记录
     * @param klassId
     * @return
     */
    public int deleteKlassStudentByKlassId(Long klassId){
        return klassStudentMapper.deleteKlassStudentByKlassId(klassId);
    }

    public int deleteKlassStudentByStudentId(Long studentId){
        return klassStudentMapper.deleteKlassStudentByStudentId(studentId);
    }

    /**
     * 根据学生id和班级id删除学生与班级的关联
     * @param klassId
     * @param studentId
     * @return
     */
    public int deleteKlassStudentByKlassIdAndStudentId(Long klassId,Long studentId){
        return klassStudentMapper.deleteKlassStudentByKlassIdAndStudentId(klassId,studentId);
    }
    /**
     * 添加班级学生记录
     * @param klassStudent
     * @return
     */
    public int addKlassStudent(KlassStudent klassStudent){
        return klassStudentMapper.addKlassStudent(klassStudent);
    }

    /**
     * 查找学生在某课程下的班级
     * @param courseId
     * @param studentId
     * @return
     */
    public Klass selectKlassByCourseIdAndStudentId(Long courseId, Long studentId){

        KlassStudent klassStudent= klassStudentMapper.selectKlassStudentByCourseIdAndStudentId(courseId,studentId);
        if(klassStudent!=null){
            return klassStudent.getKlass();
        }
        else{
            return null;
        }
    }

}
