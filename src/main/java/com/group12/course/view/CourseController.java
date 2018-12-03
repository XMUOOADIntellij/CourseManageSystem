package com.group12.course.view;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.group12.course.entity.*;
import com.group12.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @author Y Jiang
 * 2018/11/28
 * CourseController
 */

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/getall", produces = "application/json")
    public List<Course> listCourses(@RequestBody Teacher teacher) {
        return courseService.listCourses(teacher.getId());
    }


    @PostMapping(value = "/add", consumes = "application/json")
    public boolean addCourse(@RequestBody Course course) {
        return courseService.addCourse(course)!=0;
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
//
//    /**
//     * ExceptionHandler可以用在控制器中，处理特定的异常
//     * ResponseEntity可以包含响应的相关的元数据（如头部信息，状态码）以及要转换成资源表述的对象
//     *
//     * @param e CourseNotFoundException
//     * @return ResponseEntity<MyError>
//     */
//
//    @ExceptionHandler(CourseNotFoundException.class)
//    public ResponseEntity<MyError> courseNotFound(CourseNotFoundException e) {
//        int courseId = e.getId();
//        MyError error = new MyError(404, "courseID " + courseId + " NOT FOUND");
//        return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
//    }
}

