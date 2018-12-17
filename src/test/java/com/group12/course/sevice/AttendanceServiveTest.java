package com.group12.course.sevice;

import com.group12.course.dao.AttendanceDao;
import com.group12.course.dao.KlassSeminarDao;
import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.service.AttendanceService;
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
//    @Autowired
//    KlassSeminarDao klassSeminarDao;
//    @Autowired
//    AttendanceDao attendanceDao;

    @Test
    public void testGetAttendance(){
        Attendance attendance = attendanceService.getAttendance(new Long(3),new Long(4),new Long(3));
        //Assert.assertNotNull(attendanceService.getAttendance(new Long(3),new Long(4),new Long(3)));
        Assert.assertNotNull(attendance.getKlassSeminar().getKlass().getGrade());
    }

    @Test
    public  void testCancelAttendance(){
        //Assert.assertEquals(Long.valueOf(1),attendanceService.cancelAttendance(new Long(1)));
    }
}
