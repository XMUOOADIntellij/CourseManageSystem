package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.Klass;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import com.group12.course.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Course Dao 层接口实现
 * @author Tan Xue
 * @date 2018/12/12
 */

@Component
public class CourseDao {

    @Autowired
    CourseMapper courseMapper;
    @Autowired
    KlassDao klassDao;
    @Autowired
    MemberLimitStrategyDao memberLimitStrategyDao;
    @Autowired
    ConflictCourseStrategyDao conflictCourseStrategyDao;

    public Course getCourse(Long id){
        return courseMapper.selectCourseById(id);
    }

    /**
     * 根据id 删除课程
     * @param id
     * @return
     */
    public int deleteCourse(Long id){
//        if(courseMapper.selectCourseById(id)!=null)
//        {
//            List<Klass> klassList = klassDao.getAllKlassByCourseId(id);
//            //根据找到的班级，删除班级以及班级与学生的关联
//            for (Klass klass:klassList) {
//                Long klassId = klass.getId();
//                klassDao.deleteStudentByKlassId(klassId);
//                klassDao.deleteKlass(klassId);
//            }
//
//            List<ConflictCourseStrategy> conflictCourseStrategyList = conflictCourseStrategyDao.selectConflictCourseStrategyByCourseId(id);
//            //删除该课程的冲突记录
//            for (ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategyList) {
//                Long conflictCourseStrategyId = conflictCourseStrategy.getId();
//                conflictCourseStrategyDao.deleteConflictCourseStrategy(conflictCourseStrategyId);
//            }
//
//            /*TODO 删除组队策略*/
//
//            return courseMapper.deleteCourse(id);
//
//        }
        return courseMapper.deleteCourse(id);
    }

    public int addCourse(Course course){
        return courseMapper.addCourse(course.getTeacher().getId(),course);
    }

    public int updateCourse(Course course){
        return courseMapper.updateCourse(course);
    }

    public List<Course> getCourseByTeacherId(Long teacherId){
        return courseMapper.selectCourseByTeacherId(teacherId);
    }
}
