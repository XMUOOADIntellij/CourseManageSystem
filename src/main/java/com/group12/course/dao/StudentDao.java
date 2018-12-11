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

    public Student login(Student student){
        Student tempStudent=studentMapper.getStudent(student.getAccount());
        if(tempStudent.getPassword().equals(student.getPassword())){
            return tempStudent;
        }
        else {
            return new Student();
        }
    }

    public Student getStudent(String account){
        return studentMapper.getStudent(account);
    }

    public int deleteStudent(String account){
        return studentMapper.deleteStudent(account);
    }

    public int addStudent(Student student){
        return studentMapper.addStudent(student);
    }

    public int changeStudent(Student student){
        return studentMapper.updateStudent(student);
    }
}
