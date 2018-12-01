package com.group12.course.dao.daoimpl;

import com.group12.course.dao.CourseDao;
import com.group12.course.entity.Course;

import com.group12.course.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Y Jiang
 * 2018/11/28
 */
@Component
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 获得所有课程
     *
     * @return List<Course>
     */
    @Override
    public List<Course> listCourses() {
        return courseMapper.listCourses();
    }

    /**
     * 根据课程id获得课程
     *
     * @param id int
     * @return Course
     */
    @Override
    public Course getCourseNameById(int id) {
        return courseMapper.getCourseNameById(id);
    }


    /**
     * 增加课程
     *
     * @param  entity Course
     * @return Course
     */
    @Override
    public boolean add(Course entity) {
        return courseMapper.add(entity);
    }

    /**
     * 删除课程
     *
     * @param id int
     * @return Course
     */
    @Override
    public boolean remove(int id) {
        return courseMapper.remove(id);
    }

    /**
     * 更新课程
     *
     * @param  entity Course
     * @return Course
     */
    @Override
    public boolean update(Course entity) {
        return courseMapper.update(entity);
    }
}

