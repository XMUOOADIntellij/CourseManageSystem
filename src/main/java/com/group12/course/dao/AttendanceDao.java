package com.group12.course.dao;

import com.group12.course.entity.Attendance;
import com.group12.course.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendanceDao {
    @Autowired
    AttendanceMapper attendanceMapper;

    public Attendance getAttendace(Long classId, Long seminarId,Long teamId){

        return null;
    }
    //TODO 
}
