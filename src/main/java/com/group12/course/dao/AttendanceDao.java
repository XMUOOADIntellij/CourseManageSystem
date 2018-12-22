package com.group12.course.dao;

import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Seminar;
import com.group12.course.mapper.AttendanceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceDao {
    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    TeamDao teamDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;

    private Boolean orderExist(List<Attendance> attendanceList, Integer order) {
        for (Attendance item : attendanceList) {
            if (order.equals(item.getTeamOrder())) {
                return true;
            }
        }
        return false;
    }

    private Boolean orderLegal(Seminar seminar, Integer order) {
        if (order > seminar.getMaxTeam()) {
            return false;
        } else if (order < 0) {
            return false;
        }
        return true;
    }

    public Attendance selectAttendanceByKlassSeminarIdAndTeamId(Long klassSeminarId, Long teamId) {
        return attendanceMapper.selectAttendanceByKlassSeminarIdAndTeamId(klassSeminarId, teamId);
    }

    public List<Attendance> listAttendanceByKlassSeminarId(Long klassSeminarId) {
        return attendanceMapper.listAttendanceByKlassSeminarId(klassSeminarId);
    }

    public Attendance selectAttendanceById(Long attendanceId) {
        return attendanceMapper.selectAttendanceById(attendanceId);
    }

    public Integer updateAttendance(Attendance record) {
        Attendance attendance = selectAttendanceById(record.getId());
        KlassSeminar klassSeminar;
        if (attendance != null) {
            klassSeminar = attendance.getKlassSeminar();
        } else {
            // TODO AttendanceNotFound
            return null;
        }

        //若更改报名次序则验证，不更改则跳过
        Boolean order =((!orderExist(listAttendanceByKlassSeminarId(klassSeminar.getId()), record.getTeamOrder()))
                && orderLegal(klassSeminar.getSeminar(), record.getTeamOrder()));
        if (order||record.getTeamOrder()==null||record.getTeamOrder().equals(attendance.getTeamOrder()))
        {
            return attendanceMapper.updateAttendance(record);
        } else {
            //TODO 非法更新
            return null;
        }

    }

    public Integer deleteAttendanceById(Long attendanceId) {
        return attendanceMapper.deleteAttendanceById(attendanceId);
    }

    public Integer deleteAttendanceByKlassSeminarId(Long klassSeminarId) {
        return attendanceMapper.deleteAttendanceByKlassSeminarId(klassSeminarId);
    }

    public Long insertAttendance(Attendance record) {
        if (record.getTeam() != null &&
                record.getKlassSeminar() != null &&
                record.getTeamOrder() != null &&
                record.getPresented() != null) {
            KlassSeminar klassSeminar = record.getKlassSeminar();
            if ((!orderExist(listAttendanceByKlassSeminarId(klassSeminar.getId()), record.getTeamOrder()))
                    && orderLegal(klassSeminar.getSeminar(), record.getTeamOrder())) {
                attendanceMapper.insertAttendance(record);
                return record.getId();
            } else {
                //TODO 信息不合法
                return null;
            }
        } else {
            //TODO 信息不完整
            return null;
        }
    }

    public Attendance selectPresentedAttendanceByKlassSeminarId(Long klassSeminarId) {
        return attendanceMapper.selectPresentedAttendanceByKlassSeminarId(klassSeminarId);
    }
}
