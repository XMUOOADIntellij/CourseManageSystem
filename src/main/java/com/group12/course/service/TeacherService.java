package com.group12.course.service;

import com.group12.course.dao.TeacherDao;
import com.group12.course.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 添加教师
     * @param teacher 新教师对象
     * @return 处理数量，成功添加为1，否则为0
     * */
    public Teacher addTeacher(Teacher teacher){
        teacher.setPassword("123456");
        teacher.setActive(false);
        return teacherDao.addTeacher(teacher);
    }

    /**
     * 获取数据库中所有教师的记录
     *
     * @return 代表获取到的老师对象
     * */
    public List<Teacher> getAllTeacher(){
        return teacherDao.getAllTeacher();
    }

    /**
     * 通过姓名或账号来获取老师记录
     * @param param 老师姓名或账号
     * @return 所有同名的老师
     * */
    public List<Teacher> getTeacherByParam(String param){
        return teacherDao.getTeacherByParam(param.trim());
    }

    /**
     * 重设教师密码
     * @param id 教师id
     * @return 处理数量
     * */
    public int resetPassword(Long id){
        return teacherDao.resetPassword(id);
    }

    /**
     * 教师忘记密码，将密码发至邮箱
     *
     * @param account 主键id
     * @return 代表是否发送成功
     * */
    public Boolean forgetPassword(String account){
        return teacherDao.forgetPassword(account);
    }

    /**
     * 删除教师
     * @param id 账号
     * @return 处理数量
     * 成功删除为1，否则为0
     * */
    public int deleteTeacherById(Long id){
        return teacherDao.deleteTeacherById(id);
    }
}
