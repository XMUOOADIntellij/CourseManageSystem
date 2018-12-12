package com.group12.course.service;

import com.group12.course.dao.StudentDao;
import com.group12.course.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Student service 层
 * @author Xu Gang
 * @date 2018年12月11日
 * */
@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    /**
     * 学生登陆
     *
     * @param student 传入的学生对象
     * @return 返回 dao 层返回的对象
     * */
    public Student login(Student student){
        return studentDao.login(student);
    }

    /**
     * 修改信息
     *
     * @param student 传入要修改的对象
     * @return 修改的个数
     * */
    public int updateStudent(Student student){
        return studentDao.changeStudent(student);
    }

    /**
     * 获取个人信息
     * @param account 用户账户
     * @return 账户存在则返回该对象，否则则返回一个新对象
     * */
    public Student getStudent(String account){
        return studentDao.getStudent(account);
    }

    /**
     * 获取所有学生的记录
     *
     * @return 返回所有的学生的列表
     * */
    public List<Student> getAllStudent(){
        return studentDao.getAllStudent();
    }

    /**
     * 通过账号或者姓名获取学生信息
     * @param param 可以是学生姓名或者账号
     * @return 返回符合的学生的列表
     * 当是通过账号查询时，列表只会包含一个
     * */
    public List<Student> getStudentByParam(String param){
        return studentDao.getStudentByParam(param);
    }
}
