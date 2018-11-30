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
     *
     */
    @GetMapping(value = "/getAll", produces = "application/json")
    public List<Class> getAllClass() {
        return classService.listClasses();
    }

    /**
     * 把要查询的班级id直接放在请求url后面，用@PathVariable注解将url中的id值绑定到方法的参数上
     */
    @GetMapping(value = "query/{id}", produces = "application/json")
    public Class getClassNameById(@PathVariable int id) {
        try {
            return classService.getClassNameById(id);
        } catch (Exception e) {
            //若班级id不在列表中，则抛出异常，由@ExceptionHander来处理该特定异常
            throw new ClassesNotFoundException(id);
        }
    }


}
