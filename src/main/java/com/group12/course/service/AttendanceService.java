package com.group12.course.service;

import com.group12.course.dao.AttendanceDao;
import com.group12.course.dao.KlassSeminarDao;
import com.group12.course.dao.TeamDao;
import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Student;
import com.group12.course.entity.Team;
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
    @Autowired
    TeamDao teamDao;

    /**
     * 获得当前班级讨论课的展示报名
     * @param classId   班级
     * @param seminarId 讨论课ID
     * @return List
     */
    public List<Attendance> getKlassSeminarAttendance(Long classId, Long seminarId){
        return attendanceDao.selectAttendanceByKlassSeminarId(
                klassSeminarDao.getKlassSeminarBySeminarIdAndClassId(seminarId,classId).getId());
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

      KlassSeminar klassSeminar = klassSeminarDao.getKlassSeminarBySeminarIdAndClassId(seminarId,classId);
      if(klassSeminar!=null){
       return attendanceDao.selectAttendanceByKlassSeminarIdAndTeamId(klassSeminar.getId(),teamId);
       }
        else {
            return null;
        }
    }


    public Integer changeAttendanceOrder(Attendance attendance){
        //TODO 顺序没有被报
        return attendanceDao.updateAttendance(attendance);
    }

    public Integer cancelAttendance(Long attendanceId, Student student){

        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        Team team = teamDao.getTeamByStudentId(student.getId());

        if(attendance!=null){
            if(team!=null){
                if(attendance.getTeam().getId().equals(team.getId())) {
                    return attendanceDao.deleteAttendanceById(attendanceId);
                }
                else{
                    //TODO 越权
                    return null;
                }
            }
            else{
                //TODO teamNotFound
                return null;
            }
        }
        else{
            //TODO attendanceNotFound
            return null;
        }
    }

    public Long enrollAttendance(Attendance attendance,Student student){
        Team team = teamDao.getTeamByStudentId(student.getId());
            if(team!=null){
                attendance.setTeam(team);
                attendance.setPresented(false);
                return attendanceDao.insertAttendance(attendance);
            }
            else{
                //TODO TeamNotFoundException
                return null;
            }
    }
}
