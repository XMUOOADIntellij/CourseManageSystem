package com.group12.course.service;

import com.group12.course.dao.CourseDao;
import com.group12.course.entity.Course;
import com.group12.course.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Klass Service 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Service
public class CourseService {

    @Autowired
    CourseDao courseDao;

    /**
     * 获得当前用户所有课程
     * @return List<Course>
     * @param  teacherId 老师ID
     */
    public List<CourseVO> listCourses(Long teacherId){
        /*TODO*/
        return new ArrayList<>();
    }

    /**
     * 根据课程id获得课程
     * @param courseId int
     * @return Course
     */
    public CourseVO getCourseById(Long courseId){

        return new CourseVO();
    }

    /**
     * 增加课程
     * @param courseVO
     * @return Course
     */
    public int addCourse(Long teacherId,CourseVO courseVO){
        Course course = new Course();
        course.setCourseName(courseVO.getCourseName());
        course.setIntroduction(courseVO.getIntroduction());
        course.setPresentationPercentage(courseVO.getPresentationPercentage());
        course.setQuestionPercentage(courseVO.getQuestionPercentage());
        course.setReportPercentage(courseVO.getReportPercentage());
        course.setTeamStartTime(courseVO.getTeamStartTime());
        course.setTeamEndTime(courseVO.getTeamEndTime());

        //int status = courseDao.addCourse(teacherId,course);
        Long courseId = course.getId();

        /*TODO：组队人数+冲突课程*/
        return 1;
    }

    /**
     * 删除课程
     *
     * @param id int
     * @return Course
     */
    public int deleteCourse(Long id){
        return courseDao.deleteCourse(id);
    }

    /**
     * 更新课程
     *
     * @param course Course
     * @return Course
     */
    public int updateCourse(Course course){
        return courseDao.updateCourse(course);
    }

}
