package com.group12.course.dao;

import com.group12.course.entity.Attendance;
import com.group12.course.mapper.AttendanceMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AttendanceDaoTest {
    @Autowired
    AttendanceMapper attendanceMapper;
    Attendance attendance;

    @Before
    public void initial(){
        attendance = new Attendance();
    }

    @Test
    public void testEnrollPresentation(){
        attendance.setTeamId(new Long(2));
        attendance.setIsPresent(false);
        attendance.setTeamOrder(6);
        attendance.setKlassSeminarId(new Long(2));

        Assert.assertEquals(1,attendanceMapper.insert(attendance));
        Assert.assertNotNull(attendance.getId());
    }

}
