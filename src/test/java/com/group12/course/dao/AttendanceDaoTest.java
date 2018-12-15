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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
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
    }

    @Test
    public void testGetAttendance(){
        Assert.assertNotNull(attendanceMapper.selectAttendance(new Long(1),new Long(3)));
    }

    @Test
    public void testDeleteAttendance(){
        Assert.assertEquals(1,attendanceMapper.delete(new Long(1)));
    }



}
