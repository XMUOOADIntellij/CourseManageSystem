package com.group12.course.sevice;

import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Student;
import com.group12.course.service.AttendanceService;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttendanceServiveTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    AttendanceService attendanceService;


    @Test
    public void testGetAttendance(){

    }

    @Test
    public void testChangeAttendanceOrder(){
        Attendance attendance = new Attendance();
        attendance.setId(new Long(2));
        attendance.setTeamOrder(3);
        //attendanceService.changeAttendanceOrder(attendance);
    }

    @Test
    public void testCancelAttendance(){
        Student student = new Student();
        student.setId(new Long(1));
        Assert.assertNotNull(attendanceService.cancelAttendance(new Long(2),student));
    }

    @Test
    public void testEnrollAttendance(){
        Attendance attendance = new Attendance();
        attendance.setTeamOrder(3);
        attendance.setKlassSeminar(new KlassSeminar());
        attendance.getKlassSeminar().setId(new Long(2));

        Student student =new Student();
        student.setId(new Long(1));


    }
}
