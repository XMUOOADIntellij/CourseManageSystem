package com.group12.course.service;

import com.group12.course.dao.KlassDao;
import com.group12.course.dao.KlassStudentDao;
import com.group12.course.dao.StudentDao;
import com.group12.course.entity.*;
import com.group12.course.tools.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Klass Service 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Service
public class KlassService {

    @Autowired
    KlassDao klassDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    KlassStudentDao klassStudentDao;
    @Autowired
    CourseService courseService;

    public Klass getKlass(Long id){
        return klassDao.getKlass(id);
    }

    public int deleteKlass(Long id){
        return klassDao.deleteKlass(id);
    }

    public int addKlass(Klass klass){
        return klassDao.addKlass(klass);
    }



    /**
     * 获取某一课程下的所有班级
     * @param courseId 课程id
     * @return 班级列表
     */
    public List<Klass> getAllKlassByCourseId(Long courseId){
        return klassDao.getAllKlassByCourseId(courseId);
    }

    /**
     * 导入班级学生名单
     * @param klassId
     * @param file
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    public int uploadStudentList(Long klassId,MultipartFile file){
        if(file.isEmpty()){
            return 0;
        }

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        final String[] excelSuffixs={".xlsx",".xls"};

        if (!(excelSuffixs[0].equals(suffixName)||excelSuffixs[1].equals(suffixName))){
            return 0;
        }

        FileHandler fileHandler =new FileHandler();
        ArrayList<Student> students=fileHandler.handlerStudentList(file);

        //删除旧的班级学生关联
        klassStudentDao.deleteKlassStudentByKlassId(klassId);
        //插入新的班级学生关联
        Klass klass = klassDao.getKlass(klassId);
        Course course = klass.getCourse();
        for (Student student:students) {
            //如果账户已经存在，则先删除
            Student tempStudent = studentDao.getStudent(student.getAccount());
            if(tempStudent==null) {
                //插入学生账户
                student.setEmail(student.getAccount() + "@stu.xmu.edu.cn");
                studentDao.addStudent(student);
            }

            tempStudent = studentDao.getStudent(student.getAccount());

            KlassStudent klassStudent = new KlassStudent();
            klassStudent.setKlass(klass);
            klassStudent.setCourse(course);
            klassStudent.setStudent(tempStudent);
            klassStudentDao.addKlassStudent(klassStudent);


        }
        return 1;

    }

    public Klass getKlassByCourseIdAndStudentId(Long courseId,Long studentId){
        return klassStudentDao.selectKlassByCourseIdAndStudentId(courseId,studentId);
    }

}
