package com.group12.course.view;

import com.group12.course.entity.Course;
import com.group12.course.entity.CourseNotFoundException;
import com.group12.course.entity.MyError;
import com.group12.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 匹配到的网页地址是http://localhost:8080/course/getall
     * value定义网页访问路径
     * produces表明这个方法只会处理Accept头部信息包含"application/json"的请求
     */
    @GetMapping(value = "/getAll", produces = "application/json")
    public List<Course> getAllCourse() {
        return courseService.listCourses();
    }

    /**
     * 把要查询的课程id直接放在请求url后面，用@PathVariable注解将url中的id值绑定到方法的参数上
     *
     * @param id int
     * @return course
     */
    @GetMapping(value = "query/{id}", produces = "application/json")
    public Course getCourseNameById(@PathVariable int id) {
        try {
            return courseService.getCourseNameById(id);
        } catch (Exception e) {
            //若课程id不在列表中，则抛出异常，由@ExceptionHander来处理该特定异常
            throw new CourseNotFoundException(id);
        }
    }

    /**
     * RequestBody注解表示告诉Spring查找一个消息转换器，将来自客户端的资源表述转换为对象
     * consumes表示要求请求的Content-Type头部信息为"application/json"
     *
     * @param course Course
     * @return Course
     */
    @PostMapping(value = "/add", consumes = "application/json")
    public boolean addCourse(@RequestBody Course course) {
        //返回刚添加的课程信息
        return courseService.addCourse(course);
    }

    /**
     * @param course Course
     * @param id     int
     * @return Course
     */
    @PutMapping(value = "/update/{id}")
    public boolean updateCourse(@RequestBody Course course, @PathVariable int id) {
        try {
            return courseService.updateCourse(course);
        } catch (Exception e) {
            throw new CourseNotFoundException(id);
        }
    }

    /**
     * @param id int
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public boolean getAllCourse(@PathVariable int id) {
        try {
            return courseService.deleteCourse(id);
        } catch (Exception e) {
            throw new CourseNotFoundException(id);
        }
    }

    /**
     * ExceptionHandler可以用在控制器中，处理特定的异常
     * ResponseEntity可以包含响应的相关的元数据（如头部信息，状态码）以及要转换成资源表述的对象
     *
     * @param e CourseNotFoundException
     * @return ResponseEntity<MyError>
     */

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<MyError> courseNotFound(CourseNotFoundException e) {
        int courseId = e.getId();
        MyError error = new MyError(404, "courseID " + courseId + " NOT FOUND");
        return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
    }
}

