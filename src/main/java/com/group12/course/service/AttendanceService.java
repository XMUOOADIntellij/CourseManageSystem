package com.group12.course.service;

import com.group12.course.dao.AttendanceDao;
import com.group12.course.dao.KlassSeminarDao;
import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class AttendanceService {

    @Autowired
    AttendanceDao attendanceDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;

    /**
     * 获得当前班级讨论课的展示报名
     * @param classId   班级
     * @param seminarId 讨论课ID
     * @return List
     */
    public List<Attendance> getAllAttendance(Long classId, Long seminarId){
        return attendanceDao.getAllAttendance(klassSeminarDao.getKlassSeminar(seminarId,classId).getId());
    }

    /**
     * 获得当前班级讨论课正在展示的小组
     * @param classId   班级
     * @param seminarId 讨论课
     * @param presented 状态
     * @return
     */
    public Attendance getCurrentAttendance(Long classId, Long seminarId,Boolean presented){
        //TODO 获得当前班级讨论课正在展示的小组 暂留，Socket
        return null;
    }

    /**
     * 当前小组获得报名的班级讨论课展示
     * @param classId   班级
     * @param seminarId 讨论课
     * @param teamId    队伍
     * @return
     */
    public Attendance getAttendance(Long classId, Long seminarId,Long teamId){

      KlassSeminar klassSeminar = klassSeminarDao.getKlassSeminar(seminarId,classId);
      if(klassSeminar!=null){
       return attendanceDao.getAttendace(klassSeminar.getId(),teamId);
       }
        else {
            return null;
        }
    }

    public Integer changeAttendanceOrder(Long attendanceId,Integer teamOrder){
        return attendanceDao.updateAttendanceOrder(attendanceId,teamOrder);
    }

    public  Integer cancelAttendance(Long attendanceId){
        return attendanceDao.deleteAttendance(attendanceId);
    }

}
