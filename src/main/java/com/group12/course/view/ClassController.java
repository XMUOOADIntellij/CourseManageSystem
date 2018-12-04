package com.group12.course.view;

import com.group12.course.entity.Class;
import com.group12.course.entity.Course;
import com.group12.course.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class controller
 * @author Tan Xue
 * @date 2018/12/30
 */

@RestController
@RequestMapping("/api/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * 找到当前课程的所有班级
     * @param course 课程
     * @return List  班级列表
     */
    @GetMapping(value = "/getAll",produces = "application/json")
    public List<Class> getAllClasses(@RequestBody Course course){
        return classService.getAllClasses(course.getId());
    }

    /**
     * 根据班级Id获得班级
     * 把要查询的课程id直接放在请求url后面，用@PathVariable注解将url中的id值绑定到方法的参数上
     * value定义网页访问路径
     * @param id 班级号
     * @return Class 对象
     */
    @GetMapping(value = "/{id}",produces = "application/json")
    public Class getClassByClassId(@PathVariable String id) {
        return classService.getClassById(id);
    }

    /**
     * 新建班级
     * RequestBody注解表示告诉Spring查找一个消息转换器，将来自客户端的资源表述转换为对象
     * @param class1 班级
     * 若添加成功，返回200
     *  若添加失败，则返回410
     */
    @PostMapping(value = "/add")
    public void createClass(@RequestBody Class class1,HttpServletResponse response) {
        int status = classService.addClass(class1);
        if(status!=0){
            response.setStatus(200);
        }
        else{
            response.setStatus(410);
        }
    }

    /**
     * 修改班级
     * @param class1 班级
     * 若修改成功，返回200
     * 若修改失败，则返回410
     */
    @PutMapping(value = "/update")
    public void updateClass(@RequestBody Class class1,HttpServletResponse response) {
        int status=classService.updateClass(class1);
        if(status!=0){
            response.setStatus(200);
        }
        else {
            response.setStatus(410);
        }
    }

    /**
     * 删除班级
     * @param id int
     * 若删除成功，返回200
     * 若删除失败，则返回410
     */
    @DeleteMapping(value = "/delete/{id}")
    public void deleteClass(@PathVariable String id,HttpServletResponse response) {
        int status=classService.deleteClass(id);
        if(status!=0){
            response.setStatus(200);
        }
        else {
            response.setStatus(410);
        }
    }
}
