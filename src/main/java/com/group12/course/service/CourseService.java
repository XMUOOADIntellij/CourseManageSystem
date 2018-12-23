package com.group12.course.service;

import com.group12.course.dao.CourseDao;
import com.group12.course.dao.KlassStudentDao;
import com.group12.course.dao.ShareSeminarApplicationDao;
import com.group12.course.dao.ShareTeamApplicationDao;
import com.group12.course.entity.Course;
import com.group12.course.entity.KlassStudent;
import com.group12.course.entity.Student;
import com.group12.course.entity.application.ShareSeminarApplication;
import com.group12.course.entity.application.ShareTeamApplication;
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
    @Autowired
    ShareTeamApplicationDao shareTeamApplicationDao;
    @Autowired
    ShareSeminarApplicationDao shareSeminarApplicationDao;
    @Autowired
    KlassStudentDao klassStudentDao;

    /**
     * 获得当前用户所有课程
     * @return List<Course>
     * @param  teacherId 老师ID
     */
    public List<Course> getCourseByTeacherId(Long teacherId){
        return courseDao.getCourseByTeacherId(teacherId);
    }

    /**
     * 根据课程id获得课程
     * @param courseId int
     * @return Course
     */
    public Course getCourseById(Long courseId){
        return courseDao.getCourse(courseId);
    }

    /**
     * 增加课程
     * @param course
     * @return Course
     */
    public int addCourse(Course course){
        return courseDao.addCourse(course);
    }

    /**
     * 删除课程
     * @param id int
     * @return Course
     */
    public int deleteCourse(Long id){
        return courseDao.deleteCourse(id);
    }

    /**
     * 更新课程
     * @param course Course
     * @return Course
     */
    public int updateCourse(Course course){
        return courseDao.updateCourse(course);
    }

    /**
     * 根据课程id 查询共享分组信息
     * @param mainCourseId
     * @return
     */
    public List<ShareTeamApplication> getShareTeamApplicationByMainCourseId(Long mainCourseId){
        return shareTeamApplicationDao.selectShareTeamApplicationByMainCourseId(mainCourseId);
    }

    /**
     * 根据课程id 查询共享讨论课信息
     * @param mainCourseId
     * @return
     */
    public List<ShareSeminarApplication> getShareSeminarApplicationByMainCourseId(Long mainCourseId){
        return shareSeminarApplicationDao.selectShareSeminarApplicationByMainCourseId(mainCourseId);
    }

    /**
     * 取消分组共享
     * @param id
     * @return
     */
    public int deleteShareTeamApplication(Long id){
        return shareTeamApplicationDao.deleteShareTeamApplication(id);
    }

    /**
     * 取消讨论课共享
     * @param id
     * @return
     */
    public int deleteShareSeminarApplication(Long id){
        return shareSeminarApplicationDao.deleteShareSeminarApplication(id);
    }

    /**
     * 获得没有组队的学生
     * @param courseId
     * @return
     */
    public List<Student> getStudentNoTeam(Long courseId){

        List<Student> studentNoTeamList = new ArrayList<>();
        List<KlassStudent> klassStudentList = klassStudentDao.selectKlassStudentByCourseId(courseId);
        for (KlassStudent klassStudent:klassStudentList) {
            if(klassStudent.getTeam()!=null){
                studentNoTeamList.add(klassStudent.getStudent());
            }
        }
        return studentNoTeamList;
    }

    /**
     * 新增共享分组请求
     * @param shareTeamApplication
     * @return
     */
    public int addShareTeamApplication(ShareTeamApplication shareTeamApplication){
        return shareTeamApplicationDao.addShareTeamApplication(shareTeamApplication);
    }

    /**
     * 新增共享讨论课请求
     * @param shareSeminarApplication
     * @return
     */
    public int addShareSeminarApplication(ShareSeminarApplication shareSeminarApplication){
        return shareSeminarApplicationDao.addShareSeminarApplication(shareSeminarApplication);
    }

}
