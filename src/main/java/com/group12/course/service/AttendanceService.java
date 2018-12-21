package com.group12.course.service;

import com.group12.course.dao.AttendanceDao;
import com.group12.course.dao.KlassSeminarDao;
import com.group12.course.dao.TeamDao;
import com.group12.course.entity.Attendance;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Student;
import com.group12.course.entity.Team;
import com.group12.course.tools.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 讨论课展示 Service层
 * @author Y Jiang
 * @date 2018/12/17
 */
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
                klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId,classId).getId());
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

      KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId,classId);
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

    /**
     * 上传报告
     * @param attendanceId 展示id
     * @param file 文件
     * @param student 学生jwt
     * @return 文件的url
     */
    public String uploadReport(Long attendanceId,MultipartFile file,Student student){
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        Team team = teamDao.getTeamByStudentId(student.getId());
        if(attendance!=null){
            //自己组的报名才能传
            if(team.getId().equals(attendance.getTeam().getId())){
            //TODO path 服务器
            String filePath = "E:/report/"+attendance.getKlassSeminar().getId()+"/";
            String fileName = attendance.getKlassSeminar().getKlass().getKlassSerial()
                              +"_"+attendance.getTeam().getTeamSerial()
                              +file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            try{
                FileUtil.uploadFile(file,filePath,fileName);
            }catch (Exception e){
                return null;
            }
            attendance.setReportName(file.getOriginalFilename());
            attendance.setReportUrl(filePath+fileName);
            attendanceDao.updateAttendance(attendance);
            return filePath+fileName;
            }
            else{
                //TODO 权限
                return null;
            }
        }
        else{
            //TODO AttendanceNotFound
            return null;
        }
    }

    /**
     * 上传ppt
     * @param attendanceId 展示id
     * @param file 文件
     * @param student 学生jwt
     * @return 文件的url
     */
    public String uploadPpt(Long attendanceId,MultipartFile file,Student student){
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        Team team = teamDao.getTeamByStudentId(student.getId());
        if(attendance!=null){
            //自己组的报名才能传
            if(team.getId().equals(attendance.getTeam().getId())){
                //TODO path 服务器
                String filePath = "E:/ppt/"+attendance.getKlassSeminar().getId()+"/";
                String fileName = attendance.getKlassSeminar().getKlass().getKlassSerial()
                        +"_"+attendance.getTeam().getTeamSerial()
                        +file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

                try{
                    FileUtil.uploadFile(file,filePath,fileName);
                }catch (Exception e){
                    return null;
                }
                attendance.setPptName(file.getOriginalFilename());
                attendance.setPptUrl(filePath+fileName);
                attendanceDao.updateAttendance(attendance);
                return filePath+fileName;
            }
            else{
                //TODO 权限
                return null;
            }
        }
        else{
            //TODO AttendanceNotFound
            return null;
        }
    }

    /**
     * 下载报告
     * @param attendanceId 展示id
     * @param response 回应
     */
    public void downloadReport(Long attendanceId, HttpServletResponse response){
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        String fileUrl = attendance.getReportUrl();
        try{

            FileUtil.downloadFile(response,fileUrl,attendance.getReportName());}
        catch (Exception e){
            return;
        }
    }

    public void downloadPpt(Long attendanceId, HttpServletResponse response){
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        String fileUrl = attendance.getPptUrl();
        try{
            FileUtil.downloadFile(response,fileUrl,attendance.getPptName());}
        catch (Exception e){
            return;
        }
    }

    public void downloadAllPpt(Long seminarId,Long classId,HttpServletResponse response){
        List<String> fileName = new ArrayList<>();
        List<String> url = new ArrayList<>();
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId,classId);
        if(klassSeminar!=null){
           List<Attendance> attendanceList = attendanceDao.selectAttendanceByKlassSeminarId(klassSeminar.getId());
           for(Attendance item:attendanceList) {
               fileName.add(item.getPptName());
               url.add(item.getPptUrl());
           }
           try{
               FileUtil.downloadAllFiles(response,url,fileName);
           }catch (Exception e){
               //TODO
           }
        }
        else{
            //TODO KlassSeminarNotFound
        }
    }

    public void downloadAllReport(Long seminarId,Long classId,HttpServletResponse response){
        List<String> fileName = new ArrayList<>();
        List<String> url = new ArrayList<>();
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId,classId);
        if(klassSeminar!=null){
            List<Attendance> attendanceList = attendanceDao.selectAttendanceByKlassSeminarId(klassSeminar.getId());
            for(Attendance item:attendanceList){
                fileName.add(item.getReportName());
                url.add(item.getReportUrl());
            }
            try{
                FileUtil.downloadAllFiles(response,url,fileName);
            }catch (Exception e){
                //TODO
            }
        }
        else{
            //TODO KlassSeminarNotFound
        }
    }
}
