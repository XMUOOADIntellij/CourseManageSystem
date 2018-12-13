package com.group12.course.dao;

import com.group12.course.entity.Teacher;
import com.group12.course.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Teacher dao 层对应接口的实现
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class TeacherDao {

    @Autowired
    TeacherMapper teacherMapper;

    private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");
    final String defaultPassword = "123456";

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

    /**
     * 获取数据库中所有教师的记录
     *
     * @return 代表获取到的老师对象
     * */
    public List<Teacher> getAllTeacher(){
        return teacherMapper.getAllTeacher();
    }

    /**
     * 通过姓名或账号来获取老师记录
     * @param param 老师姓名或账号
     * @return 所有同名的老师
     * */
    public List<Teacher> getTeacherByParam(String param){
        if (NUMBER_PATTERN.matcher(param).matches()){
            List<Teacher> list = new ArrayList<>();
            // 通过账号只会找到一个
            list.add(getTeacher(param));
            return list;
        }
        else {
            return teacherMapper.getTeacherByName(param.trim());
        }
    }

    /**
     * 删除教师
     * @param account 账号
     * @return 处理数量
     * 成功删除为1，否则为0
     * */
    public int deleteTeacher(String account){
        return teacherMapper.deleteTeacherByAccount(account);
    }

    /**
     * 添加教师
     * @param teacher 新教师对象
     * @return 处理数量
     * 成功添加为1，否则为0
     * */
    public int addTeacher(Teacher teacher){
        return teacherMapper.addTeacher(teacher);
    }

    /**
     * 修改教师
     * @param teacher 教师对象
     * @return 处理数量
     * 成功修改为1，否则为0
     * */
    public int changeTeacher(Teacher teacher){
        return teacherMapper.updateTeacher(teacher);
    }

    /**
     * 重设教师密码
     * @param id 教师id
     * @return 处理数量
     * */
    public int resetPassword(Long id){
        return teacherMapper.updateTeacher(new Teacher(id,defaultPassword));
    }

    /**
     * 删除教师
     * @param id 账号
     * @return 处理数量
     * 成功删除为1，否则为0
     * */
    public int deleteTeacherById(Long id){
        return teacherMapper.deleteTeacherByID(id);
    }
}
