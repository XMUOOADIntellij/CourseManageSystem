package com.group12.course.controller;

import com.group12.course.entity.Course;
import com.group12.course.entity.Teacher;
import com.group12.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    private CourseService courseService;

    @PostMapping(value = "", consumes = "application/json; charset=utf-8")
    public Long addCourse(@RequestBody Course course,HttpServletResponse response) {
        courseService.addCourse(course);
        return course.getId();
    }

    @GetMapping(value = "", produces = "application/json; charset=utf-8")
    public List<Course> listCourses(@RequestBody Teacher teacher,HttpServletResponse response) {
        return courseService.listCourses(teacher.getTeacherId());
    }

    @GetMapping(value = "/{courseId}",produces = "application/json; charset=utf-8")
    public Course getCourse(@PathVariable Long courseId,HttpServletResponse response){
        return courseService.getCourseById(courseId);
    }


    }




    /**
     * @param course Course
     * @return Course
     */
    @PutMapping(value = "/update")
    public boolean updateCourse(@RequestBody Course course) {
        try {
            return courseService.updateCourse(course)!=0;
        } catch (Exception e) {
            throw new CourseNotFoundException(course.getId().intValue());
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id)!=0;
    }


}
