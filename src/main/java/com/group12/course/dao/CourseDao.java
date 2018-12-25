package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.Klass;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import com.group12.course.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    TeamStrategyDao teamStrategyDao;

    public Course getCourse(Long id){
        return courseMapper.selectCourseById(id);
    }

    /**
     * 根据id 删除课程
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    public int deleteCourse(Long id){
        if(courseMapper.selectCourseById(id)!=null)
        {
            List<Klass> klassList = klassDao.getAllKlassByCourseId(id);
            //根据找到的班级，删除班级以及班级与学生的关联
            int status1 = 1;
            for (Klass klass:klassList) {
                Long klassId = klass.getId();
                status1 = klassDao.deleteKlass(klassId)==0?0:status1;
            }
            //删除team_strategy策略表中的记录
            int status2 = teamStrategyDao.deleteTeamStrategyByCourseId(id);
            //删除课程
            int status3 = courseMapper.deleteCourse(id);
            if(status1 == 0 || status2==0 || status3 ==0){
                return 0;
            }
            else{
                return 1;
            }
        }
        return 0;
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

    public List<Course> getAllCourse(){
        return courseMapper.selectAllCourse();
    }

    public List<Course> getSubCourseByTeamMainCourseId(Long teamMainCourseId){
        return courseMapper.selectSubCourseByTeamMainCourseId(teamMainCourseId);
    }

    public List<Course> getSubCourseBySeminarMainCourseId(Long seminarMainCourseId){
        return courseMapper.selectSubCourseBySeminarMainCourseId(seminarMainCourseId);
    }

}
