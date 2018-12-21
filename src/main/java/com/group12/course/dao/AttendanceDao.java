package com.group12.course.dao;

import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
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

    private Boolean OrderExist(List<Attendance> attendanceList, Integer order) {
        for (Attendance item : attendanceList) {
            if (order.equals(item.getTeamOrder())) {
                return true;
            }
        }
        return false;
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

    public Integer updateAttendance(Attendance attendance) {
        if (!OrderExist
                (listAttendanceByKlassSeminarId(
                        selectAttendanceById(attendance.getId()).getKlassSeminar().getId()),
                        attendance.getTeamOrder())) {
            return attendanceMapper.updateAttendance(attendance);
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

    public Long insertAttendance(Attendance attendance) {
        if (teamDao.getTeamById(attendance.getTeam().getId()) != null) {
            //TODO teamnotfound
            Long klassSeminarId = attendance.getKlassSeminar().getId();
            if (klassSeminarDao.selectKlassSeminarById(klassSeminarId) != null) {
                List<Attendance> attendanceList = attendanceMapper.listAttendanceByKlassSeminarId(klassSeminarId);
                Integer teamOrder = attendance.getTeamOrder();
                for (Attendance item : attendanceList) {
                    if (teamOrder.equals(item.getTeamOrder())) {
                        //TODO order已经存在Exception
                    }
                }
                attendanceMapper.insertAttendance(attendance);
            } else {
                //TODO klassSeminar not found
            }
            return attendance.getId();
        } else {
            return null;
        }
    }
}
