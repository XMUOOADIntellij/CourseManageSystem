package com.group12.course.service;

import com.group12.course.dao.TeacherDao;
import com.group12.course.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Team service
 * @author Xu Gang
 * @date 2018年12月11日
 * */
@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherDao;

    /**
     * 教师登陆
     *
     * @param teacher 传入的教师对象
     * @return 若密码正确，返回该教师对象
     * 否则返回一个新的教师对象
     * */
    public Teacher login(Teacher teacher){
        return teacherDao.login(teacher);
    }

    /**
     * 修改信息
     *
     * @param teacher 传入要修改的对象
     * @return 修改的个数
     * */
    public int updateTeacher(Teacher teacher){
        return teacherDao.changeTeacher(teacher);
    }

    /**
     * 获取个人信息
     * @param account 用户账户
     * @return 账户存在则返回该对象，否则则返回一个新对象
     * */
    public Teacher getTeacher(String account){
        return teacherDao.getTeacher(account);
    }
}
