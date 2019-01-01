package com.group12.course.sevice;

import com.alibaba.fastjson.JSONObject;
import com.group12.course.controller.vo.QuestionVO;
import com.group12.course.dao.AttendanceDao;
import com.group12.course.dao.QuestionDao;
import com.group12.course.dao.TeacherDao;
import com.group12.course.entity.Attendance;
import com.group12.course.entity.Seminar;
import com.group12.course.entity.Teacher;
import com.group12.course.service.AttendanceService;
import com.group12.course.service.QuestionService;
import com.group12.course.service.SeminarService;
import org.json.JSONString;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    SeminarService seminarService;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    AttendanceDao attendanceDao;
    @Autowired
    QuestionService questionService;

    @Test
    public void testCreateSeminar(){
        Seminar seminar =new Seminar();
        seminar.setMaxTeam(5);
        seminar.setSeminarName("test");
        seminar.setSeminarSerial(4);
        seminar.setVisible(false);
        //seminarService.createSeminar(seminar);
    }

    @Test
    public void testDeleteSeminar(){

        //seminarService.deleteSeminar(new Long(4));
    }

    @Test
    public void testSelectKlassSeminarBySeminarIdAndKlassId(){
        Assert.assertNotNull(seminarService.selectKlassSeminar(new Long(4),new Long(3)));
    }


    @Test
    public void testProgress(){
        Teacher teacher =teacherDao.getTeacher("2007100012");
        seminarService.startSeminar(teacher,10L,21L);

        List<Attendance> attendanceList = attendanceDao.listAttendanceByKlassSeminarId(21L);
        attendanceList.sort(new Comparator<Attendance>() {
            @Override
            public int compare(Attendance o1, Attendance o2) {
                return o1.getTeamOrder().compareTo(o2.getTeamOrder());
            }
        });
        for(Attendance item:attendanceList){
            System.out.println(item.getTeamOrder()+" "+item.getPresented());
        }
    }

    @Test
    public void nextAttendance(){
        Teacher teacher =teacherDao.getTeacher("2007100012");
        seminarService.startSeminar(teacher,10L,21L);

        attendanceService.nextAttendance(10L,21L,63L);

        List<Attendance> attendanceList = attendanceDao.listAttendanceByKlassSeminarId(21L);
        attendanceList.sort(new Comparator<Attendance>() {
            @Override
            public int compare(Attendance o1, Attendance o2) {
                return o1.getTeamOrder().compareTo(o2.getTeamOrder());
            }
        });
        for(Attendance item:attendanceList){
            System.out.println(item.getTeamOrder()+" "+item.getPresented());
        }
    }

    @Test
    public void selectQuesiton(){
        Teacher teacher =teacherDao.getTeacher("2007100012");
        seminarService.startSeminar(teacher,10L,21L);

        System.out.println(
                new QuestionVO(questionService.selectQuestion(teacher,10L,21L,63L)));
    }
}
