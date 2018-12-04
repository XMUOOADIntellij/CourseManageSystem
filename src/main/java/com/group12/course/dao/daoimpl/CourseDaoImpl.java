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


    @Override
    public List<Course> listCourses(Long teacherNum){
        return courseMapper.listCourses(teacherNum);
    }

    @Override
    public int deleteByPrimaryKey(Long id){
        return courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Course record){
        return courseMapper.insert(record);
    }

    @Override
    public int insertSelective(Course record){
        return courseMapper.insertSelective(record);
    }

    @Override
    public Course selectByPrimaryKey(Long id){
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Course record){
        return  courseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Course record){
        return courseMapper.updateByPrimaryKey(record);
    }
}

