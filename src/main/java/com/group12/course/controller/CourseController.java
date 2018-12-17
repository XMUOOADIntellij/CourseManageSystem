package com.group12.course.controller;


import com.group12.course.entity.*;
import com.group12.course.service.CourseService;
import com.group12.course.service.KlassService;
import com.group12.course.service.StudentService;
import com.group12.course.vo.CourseVO;
import com.group12.course.vo.ShareVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * Course Controller 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    KlassService klassService;
    @Autowired
    StudentService studentService;

    /**
     * 获得当前用户所有课程
     * @return List<Course>
     * @param  teacherId 老师ID
     */
    @GetMapping(value="",produces = "application/json; charset=utf-8")
    public List<Course> listCourses(Long teacherId,HttpServletResponse response){
        return courseService.listCourses(teacherId);
    }

    /**
     * 根据课程id获得课程
     * @param courseId int
     * @return Course
     */
    @GetMapping(value="/{courseId}",produces = "application/json; charset=utf-8")
    public Course getCourseById(@PathVariable Long courseId,HttpServletResponse response){
        return courseService.getCourseById(courseId);
    }

    /**
     * 新建课程
     * @param courseVO
     * @return Course
     */
    @PostMapping(value="",produces = "application/json; charset=utf-8")
    public void createCourse(@RequestParam Long teacherId,@RequestBody CourseVO courseVO, HttpServletResponse response){
        Course course = new Course();
        course.setCourseName(courseVO.getCourseName());
        course.setIntroduction(courseVO.getIntroduction());
        course.setPresentationPercentage(courseVO.getPresentationPercentage());
        course.setQuestionPercentage(courseVO.getQuestionPercentage());
        course.setReportPercentage(courseVO.getReportPercentage());
        course.setTeamStartTime(courseVO.getTeamStartTime());
        course.setTeamEndTime(courseVO.getTeamEndTime());

        int status = courseService.addCourse(teacherId,course);
        Long courseId = course.getId();


        return;
    }

    /**
     * 删除课程
     *
     * @param courseId int
     * @return Course
     */
    @DeleteMapping(value="/{courseId}",produces = "application/json; charset=utf-8")
    public void deleteCourse(@PathVariable Long courseId,HttpServletResponse response){

        int status = courseService.deleteCourse(courseId);
        if(status == 0){
            response.setStatus(400);
        }
        else{
            response.setStatus(204);
        }
    }

    /**
     * 修改课程信息
     * @param course Course
     * @return Course
     */
    @PostMapping(value="",produces = "application/json; charset=utf-8")
    public void updateCourse(@RequestBody Course course,HttpServletResponse response){
        int status = courseService.updateCourse(course);
        if(status == 0){
            response.setStatus(400);
        }
        else{
            response.setStatus(204);
        }
    }

    /**
     * 根据课程id获取成绩
     */
    @GetMapping(value="/{courseId}/score",produces = "application/json; charset=utf-8")
    public List<SeminarScore> getSeminarScore(@PathVariable Long courseId,HttpServletResponse response){
        return new ArrayList<>();
    }

    /**
     * 修改成绩
     * @param courseId
     * @param response
     */
    @PutMapping(value="/{courseId}",produces = "application/json; charset=utf-8")
    public void modifyScore(@PathVariable Long courseId,@RequestBody SeminarScore seminarScore,HttpServletResponse response){

    }

    /**
     * 根据课程id获取小组信息
     */
    @GetMapping(value="/{courseId}/team",produces = "application/json; charset=utf-8")
    public List<Team> getTeamByCourseId(@PathVariable Long courseId,HttpServletResponse response){

        return new ArrayList<>();
    }

    /**
     * 根据课程id获取未组队学生
     */
    @GetMapping(value="/{courseId}/noTeam",produces = "application/json; charset=utf-8")
    public List<Student> getStudentNoTeam(@PathVariable Long courseId,HttpServletResponse response){
        return new ArrayList<>();
    }

    /**
     * 根据id获取课程的班级列表
     * @param courseId 课程id
     * @return 班级列表
     */
    @GetMapping(value="/{courseId}/class",produces = "application/json; charset=utf-8")
    public List<Klass> getAllKlassByCourseId(@PathVariable Long courseId,HttpServletResponse response){
        List<Klass> list = klassService.getAllKlassByCourseId(courseId);
        if (list.isEmpty()){
            response.setStatus(404);
            return new ArrayList<>();
        }
        else {
            response.setStatus(200);
            return list;
        }
    }

    /**
     * 在课程下创建班级
     * @param courseId
     * @param klass
     * @param response
     */
    @PostMapping(value="/{courseId}/class",produces = "application/json; charset=utf-8")
    public void createKlass(@PathVariable Long courseId,@RequestBody Klass klass,HttpServletResponse response){
        int status = klassService.addKlass(klass);
        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(201);
        }
    }

    /**
     * 在指定班级导入学生名单
     * @param
     * @return
     */
    @PutMapping(value="/{courseId}/class/{classId}",produces = "application/json; charset=utf-8")
    public void uploadStudentList(@PathVariable Long courseId, @PathVariable Long classId, @RequestParam("file") MultipartFile file, HttpServletResponse response){
//        int status = studentService.uploadStudentList(courseId,classId,file);
//        if(status == 0){
//            response.setStatus(404);
//        }
//        else{
//            response.setStatus(201);
//        }
    }

    /**
     * 删除某一课程下的某一班级
     * @param courseId
     * @param classId
     */
    @DeleteMapping(value="/{courseId}/class/{classId}",produces = "application/json; charset=utf-8")
    public void deleteKlassByKlassId(@PathVariable Long courseId,@PathVariable Long classId,HttpServletResponse response){
        int status = klassService.deleteKlass(classId);
        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(204);
        }
    }

    /**
     * 获取共享信息
     * @param courseId
     * @param response
     * @return
     */
    @GetMapping(value="/{courseId}/share",produces = "application/json; charset=utf-8")
    public List<ShareVO> getShareMessage(@PathVariable Long courseId, HttpServletResponse response){
        /*TODO*/
        return new ArrayList<>();
    }

    /**
     * 取消共享
     * @param courseId
     * @param shareId
     * @param response
     */
    @DeleteMapping(value="/{courseId}/share/{shareId}",produces = "application/json; charset=utf-8")
    public void deleteShareById(@PathVariable Long courseId,@PathVariable Long shareId,@RequestParam String type,HttpServletResponse response){
        /*TODO*/
    }

    /**
     * 新增共享
     * @param courseId
     * @param subCourseId
     * @param response
     */
    @PostMapping(value="/{courseId}/sharerequest",produces = "application/json; charset=utf-8")
    public void createShareRequest(@PathVariable Long courseId,@RequestParam String type,@RequestParam Long subCourseId,HttpServletResponse response){
        /*TODO*/

    }

}



