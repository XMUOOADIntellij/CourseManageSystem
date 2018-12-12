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

    /**
     * 教师登陆
     *
     * @param teacher 传入的教师对象
     * @return 若密码正确，返回该教师对象
     * 否则返回一个新的教师对象
     * */
    public Teacher login(Teacher teacher){
        if (teacher==null||teacher.getAccount()==null||teacher.getPassword()==null){
            return new Teacher();
        }
        else {
            Teacher tempTeacher=teacherMapper.getTeacherByAccount(teacher.getAccount());
            if (tempTeacher.getPassword().equals(teacher.getPassword())){
                return tempTeacher;
            }
            else {
                return new Teacher();
            }
        }
    }

    /**
     * 获取个人信息
     * @param account 用户账户
     * @return 账户存在则返回该对象，否则则返回一个新对象
     * */
    public Teacher getTeacher(String account){
        if (account==null){
            return new Teacher();
        }
        else {
            return teacherMapper.getTeacherByAccount(account);
        }
    }

    public int deleteTeacher(String account){
        return teacherMapper.deleteTeacherByAccount(account);
    }

    public int addTeacher(Teacher teacher){
        return teacherMapper.addTeacher(teacher);
    }

    public int changeTeacher(Teacher teacher){
        return teacherMapper.updateTeacher(teacher);
    }
}
