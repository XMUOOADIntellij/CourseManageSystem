package com.group12.course.view;

import com.group12.course.entity.Course;
import com.group12.course.entity.CourseNotFoundException;
import com.group12.course.entity.MyError;
import com.group12.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

// 添加RestController的注解
// 该控制器的方法所返回的对象将会通过消息转换机制，产生客户端所需要的资源表述
// 相当于在每个方法前加@ResponseBody
@RestController

//控制器指定可以处理哪些URL请求
@RequestMapping("/course")
public class CourseController {


    @Autowired
    CourseService courseService;


    //匹配到的网页地址是http://localhost:8080/course/getall
    //value定义网页访问路径
    // produces表明这个方法只会处理Accept头部信息包含"application/json"的请求
    //@GetMapping作用相当于@RequestMapping(value="/getall",method=RequestMethod.GET)
    @GetMapping(value="/getall",produces = "application/json")
    public List<Course> getAllCourse() {
        //返回所有课程的List
        return courseService.getAllCourses();
    }


    //把要查询的课程id直接放在请求url后面，用@PathVariable注解将url中的id值绑定到方法的参数上
    @GetMapping(value="query/{id}",produces = "application/json")
    public Course getCourseNameById(@PathVariable int id) {
        try{
            Course course=courseService.getCourseNameById(id);
            return course;
        }
        catch (Exception e){
            //若课程id不在列表中，则抛出异常，由@ExceptionHander来处理该特定异常
            throw new CourseNotFoundException(id);
        }
    }

    //@RequestBody注解表示告诉Spring查找一个消息转换器，将来自客户端的资源表述转换为对象
    //consumes表示要求请求的Content-Type头部信息为"application/json"
    @PostMapping(value="/add",consumes = "application/json")
    public Course addCourse(@RequestBody Course course) {
        //返回刚添加的课程信息
        return courseService.addCourse(course);
    }

    @PutMapping(value="/update/{id}")
    public Course updateCourse(@RequestBody Course course,@PathVariable int id){
        try{
            Course course1=courseService.updateCourse(course);
            //返回刚修改的课程信息
            return course1;
        }
        catch (Exception e){
            throw new CourseNotFoundException(id);
        }
    }

    @DeleteMapping(value="/delete/{id}")
    public Course getAllCourse(@PathVariable int id) {
        try{
            Course course=courseService.deleteCourse(id);
            //返回刚删除的课程信息


            return course;
        }
        catch (Exception e){
            throw new CourseNotFoundException(id);
        }
    }

    //@ExceptionHandler可以用在控制器中，处理特定的异常
    //ResponseEntity可以包含响应的相关的元数据（如头部信息，状态码）以及要转换成资源表述的对象
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<MyError> courseNotFound(CourseNotFoundException e){
        int courseId=e.getId();
        MyError error=new MyError(404,"courseID "+courseId+" NOT FOUND");
        return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
    }
}

