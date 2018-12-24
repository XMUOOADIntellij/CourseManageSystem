package com.group12.course.dao;

import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Team;
import com.group12.course.mapper.AttendanceMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AttendanceDaoTest {
    @Autowired
    AttendanceDao attendanceDao;

    @Test
    public void testEnrollPresentation(){
    }

    @Test
    public void testGetAttendance(){
        Assert.assertNotNull(attendanceDao.selectAttendanceByKlassSeminarIdAndTeamId(new Long(1),new Long(3)));
    }

    @Test
    public void testDeleteAttendance(){
        // Assert.assertEquals(1,attendanceMapper.deleteAttendanceById(new Long(1)).longValue());
    }

    @Test
    public void testInsertAttendance(){
        Attendance attendance = new Attendance();
        attendance.setTeam(new Team());
        attendance.getTeam().setId(new Long(1));
        attendance.setKlassSeminar(new KlassSeminar());
        attendance.getKlassSeminar().setId(new Long(1));
        attendance.setPresented(false);
        attendance.setTeamOrder(3);
        attendanceDao.insertAttendance(attendance);
        Assert.assertNotNull(attendance.getId());
    }

    @Test
    public void selectAttendanceByKlassSeminarIdAndTeamOrder(){
        Assert.assertNotNull(attendanceDao.selectAttendanceByKlassSeminarIdAndTeamOrder(1L,3));
    }
}
