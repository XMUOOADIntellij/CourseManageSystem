package com.group12.course.dao;


import com.group12.course.entity.Klass;
import com.group12.course.entity.KlassStudent;
import com.group12.course.mapper.KlassMapper;
import com.group12.course.mapper.KlassStudentMapper;
import com.group12.course.mapper.StudentMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class KlassStudentDaoTest {

    @Autowired
    KlassStudentMapper klassStudentMapper;
    @Autowired
    KlassMapper klassMapper;
    @Autowired
    StudentMapper studentMapper;


    @Test
    @Rollback
    public void testAddKlassStudent(){

        KlassStudent klassStudent = new KlassStudent();

        Klass klass = klassMapper.selectKlassById(new Long(1));
        klassStudent.setKlass(klass);

        klassStudent.setCourse(klass.getCourse());
        klassStudent.setStudent(studentMapper.selectStudentById(new Long(1)));
        int status = klassStudentMapper.addKlassStudent(klassStudent);
        Assert.assertEquals(1,status);

        klassStudentMapper.deleteKlassStudentByKlassIdAndStudentId(klass.getId(),klassStudent.getStudent().getId());
    }

    @Test
    public void testSelectKlassByCourseIdAndStudentId(){
        KlassStudent klassStudent = klassStudentMapper.selectKlassStudentByCourseIdAndStudentId(new Long(16),new Long(134));
        System.out.print(klassStudent.getKlass());
        Assert.assertNotNull(klassStudent);
    }
}
