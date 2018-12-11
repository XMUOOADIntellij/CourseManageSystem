package com.group12.course.service;

import com.group12.course.dao.TeacherDao;
import com.group12.course.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherDao;

    public Teacher login(Teacher teacher){
        if (teacher.getAccount()==null||teacher.getAccount()==null){
            return new Teacher();
        }
        return teacherDao.login(teacher);
    }
}
