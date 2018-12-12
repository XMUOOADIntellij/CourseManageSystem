package com.group12.course.dao;

import com.group12.course.entity.Student;
import com.group12.course.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Student dao 层对应接口的实现
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class StudentDao {

    @Autowired
    StudentMapper studentMapper;


    /**
     * 学生登陆
     *
     * @param student 传入的学生对象
     * @return 若密码符合，返回真实的学生对象
     * 否则返回一个新对象
     * */
    public Student login(Student student){
        if (student.getPassword()==null||student.getAccount()==null){
            return new Student();
        }
        else{
            Student tempStudent=studentMapper.getStudentByAccount(student.getAccount());
            if(tempStudent.getPassword().equals(student.getPassword())){
                return tempStudent;
            }
            else {
                return new Student();
            }
        }
    }

    /**
     * 获取个人信息
     * @param account 用户账户
     * @return 账户存在则返回该对象，否则则返回一个新对象
     * */
    public Student getStudent(String account){
        if (account==null){
            return new Student();
        }
        else {
            return studentMapper.getStudentByAccount(account);
        }
    }

    public int deleteStudent(String account){
        return studentMapper.deleteStudentByAccount(account);
    }

    public int addStudent(Student student){
        return studentMapper.addStudent(student);
    }

    public int changeStudent(Student student){
        return studentMapper.updateStudent(student);
    }
}
