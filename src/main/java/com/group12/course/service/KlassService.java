package com.group12.course.service;

import com.group12.course.dao.KlassDao;
import com.group12.course.dao.KlassStudentDao;
import com.group12.course.dao.StudentDao;
import com.group12.course.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public int updateKlass(Klass klass){
        return klassDao.updateKlass(klass);
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

        for (Student student:students) {
            studentDao.addStudent(student);

            KlassStudent klassStudent = new KlassStudent();

            klassStudent.setKlass(getKlass(klassId));
            klassStudent.setCourse(getKlass(klassId).getCourse());
            klassStudent.setStudent(student);
            klassStudentDao.addKlassStudent(klassStudent);
        }
        return 1;

    }

}
