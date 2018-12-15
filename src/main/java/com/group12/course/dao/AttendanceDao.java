package com.group12.course.dao;

import com.group12.course.entity.Attendance;
import com.group12.course.mapper.AttendanceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceDao {
    @Autowired
    AttendanceMapper attendanceMapper;

    public Attendance getAttendace(Long klassSeminarId,Long teamId){
        return attendanceMapper.selectAttendance(klassSeminarId,teamId);
    }

    public List<Attendance> getAllAttendance(Long klassSeminarId){
        return attendanceMapper.selectAllAttendance(klassSeminarId);
    }

    public Integer updateAttendanceOrder(Long attendanceId,Integer teamOrder){
        return attendanceMapper.updateOrder(attendanceId,teamOrder);
    }

    public Integer deleteAttendance(Long attendanceId){
        return  attendanceMapper.delete(attendanceId);
    }
}
