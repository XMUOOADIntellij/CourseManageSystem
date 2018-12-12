package com.group12.course.dao;

import com.group12.course.entity.Teacher;
import com.group12.course.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Teacher dao 层对应接口的实现
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class TeacherDao {

    @Autowired
    TeacherMapper teacherMapper;

    public Teacher login(Teacher teacher){
        Teacher tempTeacher=teacherMapper.getTeacher(teacher.getAccount());
        if (tempTeacher.getPassword().equals(teacher.getPassword())){
            return tempTeacher;
        }
        else {
            return new Teacher();
        }
    }

    public Teacher getTeacher(String account){
        return teacherMapper.getTeacher(account);
    }

    public int deleteTeacher(String account){
        return teacherMapper.deleteTeacher(account);
    }

    public int addTeacher(Teacher teacher){
        return teacherMapper.addTeacher(teacher);
    }

    public int changeTeacher(Teacher teacher){
        return teacherMapper.updateTeacher(teacher);
    }
}
