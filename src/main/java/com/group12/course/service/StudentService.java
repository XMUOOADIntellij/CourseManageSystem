package com.group12.course.service;

import com.group12.course.dao.StudentDao;
import com.group12.course.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Student service 层
 * @author Xu Gang
 * @date 2018年12月11日
 * */
@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    public Student login(Student student){
        if (student.getPassword()==null||student.getAccount()==null){
            return new Student();
        }
        return studentDao.login(student);
    }
}
