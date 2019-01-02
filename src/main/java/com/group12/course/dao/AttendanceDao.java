package com.group12.course.dao;

import com.group12.course.exception.InformationException;
import com.group12.course.exception.RecordNotFoundException;
import com.group12.course.exception.UnauthorizedOperationException;
import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Seminar;
import com.group12.course.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 讨论课报名相关Dao层
 * @author Y Jiang
 * @date 2018/12/12
 */
@Component
public class AttendanceDao {
    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    TeamDao teamDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;

    private Boolean orderExist(List<Attendance> attendanceList, Integer order) {
        if(attendanceList!=null){
            return true;
        }
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
                throw new InformationException("Team Order 已存在或不合法");
            }
        } else {
            throw new InformationException("Attendance 必要字段不存在");
        }
    }

    public List<Attendance> listAttendanceByKlassSeminarId(Long klassSeminarId) {
        return attendanceMapper.listAttendanceByKlassSeminarId(klassSeminarId);
    }

    public Integer updateAttendance(Attendance record) {
        Attendance attendance = selectAttendanceById(record.getId());
        KlassSeminar klassSeminar;
        if (attendance != null) {
            klassSeminar = attendance.getKlassSeminar();
        } else {
            throw new RecordNotFoundException("Attendance Not Found");
        }

        //若更改报名次序则验证，不更改则跳过
        Boolean order =((!orderExist(listAttendanceByKlassSeminarId(klassSeminar.getId()), record.getTeamOrder()))
                && orderLegal(klassSeminar.getSeminar(), record.getTeamOrder()));
        if (order||record.getTeamOrder()==null||record.getTeamOrder().equals(attendance.getTeamOrder()))
        {
            return attendanceMapper.updateAttendance(record);
        } else {
           throw new UnauthorizedOperationException("Only this Seminar's teacher can operate");
        }

    }

    public Integer deleteAttendanceById(Long attendanceId) {
        return attendanceMapper.deleteAttendanceById(attendanceId);
    }

    public Integer deleteAttendanceByKlassSeminarId(Long klassSeminarId) {
        return attendanceMapper.deleteAttendanceByKlassSeminarId(klassSeminarId);
    }

    public Attendance selectAttendanceById(Long attendanceId) {
        return attendanceMapper.selectAttendanceById(attendanceId);
    }

    public Attendance selectPresentedAttendanceByKlassSeminarId(Long klassSeminarId) {
        return attendanceMapper.selectPresentedAttendanceByKlassSeminarId(klassSeminarId);
    }

    public Attendance selectAttendanceByKlassSeminarIdAndTeamId(Long klassSeminarId, Long teamId) {
        return attendanceMapper.selectAttendanceByKlassSeminarIdAndTeamId(klassSeminarId, teamId);
    }

    public Attendance selectAttendanceByKlassSeminarIdAndTeamOrder(Long klassSeminar,Integer teamOrder){
        return  attendanceMapper.selectAttendanceByKlassSeminarIdAndTeamOrder(klassSeminar,teamOrder);
    }
}
