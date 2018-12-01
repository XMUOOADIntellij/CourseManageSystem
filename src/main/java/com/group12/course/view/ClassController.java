package com.group12.course.view;

import com.group12.course.entity.Class;
import com.group12.course.exception.ClassesNotFoundException;
import com.group12.course.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tan Xue
 * 2018年12月30日
 */
@RestController
@RequestMapping("/Class")
public class ClassController {
    @Autowired
    private ClassService classService;

    /**
     * 获得所有班级
     */
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Class> getAllClass() {
        return classService.listClasses();
    }

    /**
     * 根据ClassId获得班级
     * 把要查询的班级id直接放在请求url后面，用@PathVariable注解将url中的id值绑定到方法的参数上
     */
    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public Class getClassByClassId(@PathVariable int id) {
        try {
            return classService.getClassByClassId(id);
        } catch (Exception e) {
            //若班级id不在列表中，则抛出异常，由@ExceptionHander来处理该特定异常
            throw new ClassesNotFoundException(id);
        }
    }

    /**
     * 增加班级
     * RequestBody注解表示告诉Spring查找一个消息转换器，将来自客户端的资源表述转换为对象
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean addClass(@RequestBody Class class1) {
        //返回刚添加的课程信息
        return classService.addClass(class1);
    }

    /**
     * 删除班级
     * @param id int
     * @return Class
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public boolean deleteClass(@PathVariable int id) {
        try {
            return classService.deleteClass(id);
        } catch (Exception e) {
            throw new ClassesNotFoundException(id);
        }
    }


    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public boolean updateCourse(@RequestBody Class class1, @PathVariable int id) {
        try {
            return classService.updateClass(class1);
        } catch (Exception e) {
            throw new ClassesNotFoundException(id);
        }
    }

}
