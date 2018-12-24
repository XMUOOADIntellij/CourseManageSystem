package com.group12.course.service;

import com.group12.course.dao.AttendanceDao;
import com.group12.course.dao.KlassSeminarDao;
import com.group12.course.dao.SeminarDao;
import com.group12.course.dao.TeamDao;
import com.group12.course.entity.*;
import com.group12.course.tools.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * 讨论课展示 Service层
 *
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
    @Autowired
    SeminarDao seminarDao;

    private final String ServerFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "file" + System.getProperty("file.separator");
    private final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    /**
     * 获得当前班级讨论课的展示报名
     *
     * @param classId   班级
     * @param seminarId 讨论课ID
     * @return List
     */
    public List<Attendance> getKlassSeminarAttendance(Long classId, Long seminarId) {

        KlassSeminar klassSeminar =
                klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            return attendanceDao.listAttendanceByKlassSeminarId(klassSeminar.getId());
        } else {
            return null;
            //TODO seminarnotfound
        }
    }

    /**
     * 获得当前班级讨论课正在展示的小组
     *
     * @param classId   班级
     * @param seminarId 讨论课
     * @return 正在展示的小组
     */
    public Attendance getCurrentAttendance(Long classId, Long seminarId) {
        KlassSeminar klassSeminar =
                klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            return attendanceDao.selectPresentedAttendanceByKlassSeminarId(klassSeminar.getId());
        } else {
            return null;
            //TODO seminarnotfound
        }

    }

    /**
     * 获得自己组的展示报名
     *
     * @param seminarId 讨论课id
     * @param student   学生对象
     * @return Attendance
     */
    public Attendance getTeamAttendance(Long seminarId, Student student) {

        //获得讨论课
        Seminar seminar = seminarDao.selectSeminarById(seminarId);
        if (seminar != null) {
            //学生所在队伍
            Team team = teamDao.getTeamByStudentIdAndCourseId(
                    student.getId(),
                    seminar.getCourse().getId());
            //班级讨论课
            KlassSeminar klassSeminar = klassSeminarDao.
                    selectKlassSeminarBySeminarIdAndClassId(seminarId, team.getKlass().getId());
            if (klassSeminar != null) {
                return attendanceDao.
                        selectAttendanceByKlassSeminarIdAndTeamId(klassSeminar.getId(), team.getId());
            } else {
                return null;
                //TODO seminarNotFound
            }
        } else {
            return null;
            //TODO SeminarNotFound
        }
    }

    public Integer changeAttendanceOrder(Attendance record, Student student) {
        Attendance attendance = attendanceDao.selectAttendanceById(record.getId());
        if (attendance != null) {
            if (attendance.getId().equals(
                    getTeamAttendance(attendance.getKlassSeminar().getSeminar().getId(), student).getId()
            )) {
                return attendanceDao.updateAttendance(record);
            } else {
                //TODO 权限不足
                return null;
            }
        } else {
            return null;
            //AttendanceNotFound
        }
    }

    public Integer cancelAttendance(Long attendanceId, Student student) {

        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        if (attendance != null) {
            if (attendance.getId().equals(
                    getTeamAttendance(attendance.getKlassSeminar().getSeminar().getId(), student).getId()
            )) {
                return attendanceDao.deleteAttendanceById(attendanceId);
            } else {
                //TODO 权限不足
                return null;
            }
        } else {
            return null;
            //AttendanceNotFound
        }
    }

    public Long enrollAttendance(Long seminarId, Attendance attendance, Student student) {
        //获得讨论课
        Seminar seminar = seminarDao.selectSeminarById(seminarId);
        if (seminar != null) {
            //学生所在队伍
            Team team = teamDao.getTeamByStudentIdAndCourseId(
                    student.getId(),
                    seminar.getCourse().getId());
            //班级讨论课
            KlassSeminar klassSeminar = klassSeminarDao.
                    selectKlassSeminarBySeminarIdAndClassId(seminarId, team.getKlass().getId());
            if (klassSeminar != null) {
                attendance.setTeam(team);
                attendance.setKlassSeminar(klassSeminar);
                attendance.setPresented(false);
                return attendanceDao.insertAttendance(attendance);
            } else {
                return null;
                //TODO seminarNotFound
            }
        } else {
            return null;
            //TODO SeminarNotFound
        }
    }

    /**
     * 上传报告
     *
     * @param attendanceId 展示id
     * @param file         文件
     * @param student      学生jwt
     * @return 文件的url
     */
    public String uploadReport(Long attendanceId, MultipartFile file, Student student) {

        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        if (attendance != null) {
            //自己组的报名才能传
            if (attendance.getId().equals(
                    getTeamAttendance(attendance.getKlassSeminar().getSeminar().getId(), student).getId())) {
                //TODO path 服务器
                String filePath = ServerFilePath + System.getProperty("file.separator") + "report" + System.getProperty("file.separator")
                        + attendance.getKlassSeminar().getId() + System.getProperty("file.separator");
                String fileName = attendance.getKlassSeminar().getKlass().getKlassSerial()
                        + "_" + attendance.getTeam().getTeamSerial()
                        + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

                logger.trace("fileName:" + fileName);
                logger.trace("filePath:" + filePath);

                try {
                    FileUtil.uploadFile(file, filePath, fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                attendance.setReportName(file.getOriginalFilename());
                attendance.setReportUrl(filePath + fileName);
                attendanceDao.updateAttendance(attendance);
                return filePath + fileName;
            } else {
                //TODO 权限
                return null;
            }
        } else {
            //TODO AttendanceNotFound
            return null;
        }
    }

    /**
     * 上传ppt
     *
     * @param attendanceId 展示id
     * @param file         文件
     * @param student      学生jwt
     * @return 文件的url
     */
    public String uploadPpt(Long attendanceId, MultipartFile file, Student student) {
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        if (attendance != null) {
            //自己组的报名才能传
            if (attendance.getId().equals(
                    getTeamAttendance(attendance.getKlassSeminar().getSeminar().getId(), student).getId())) {
                //TODO path 服务器
                String filePath = ServerFilePath + System.getProperty("file.separator") + "ppt" + System.getProperty("file.separator")
                        + attendance.getKlassSeminar().getId() + System.getProperty("file.separator");
                String fileName = attendance.getKlassSeminar().getKlass().getKlassSerial()
                        + "_" + attendance.getTeam().getTeamSerial()
                        + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

                logger.debug("fileName:" + fileName);
                logger.debug("filePath:" + filePath);

                try {
                    logger.debug("tryUpload");
                    FileUtil.uploadFile(file, filePath, fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    attendance.setPptName(file.getOriginalFilename());
                    attendance.setPptUrl(filePath + fileName);
                    attendanceDao.updateAttendance(attendance);
                }
                return filePath + fileName;
            } else {
                //TODO 权限
                return null;
            }
        } else {
            //TODO AttendanceNotFound
            return null;
        }
    }

    /**
     * 下载报告
     *
     * @param attendanceId 展示id
     * @param response     回应
     */
    public void downloadReport(Long attendanceId, HttpServletResponse response) {
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        String fileUrl = attendance.getReportUrl();
        try {

            FileUtil.downloadFile(response, fileUrl, attendance.getReportName());
        } catch (Exception e) {
            return;
        }
    }

    public void downloadPpt(Long attendanceId, HttpServletResponse response) {
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        String fileUrl = attendance.getPptUrl();
        try {
            FileUtil.downloadFile(response, fileUrl, attendance.getPptName());
        } catch (Exception e) {
            return;
        }
    }

    public void downloadAllPpt(Long seminarId, Long classId, HttpServletResponse response) {
        List<String> fileName = new ArrayList<>();
        List<String> url = new ArrayList<>();
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            List<Attendance> attendanceList = attendanceDao.listAttendanceByKlassSeminarId(klassSeminar.getId());
            for (Attendance item : attendanceList) {
                fileName.add(item.getPptName());
                url.add(item.getPptUrl());
            }
            try {
                FileUtil.downloadAllFiles(response, url, fileName);
            } catch (Exception e) {
                //TODO
            }
        } else {
            //TODO KlassSeminarNotFound
        }
    }

    public void downloadAllReport(Long seminarId, Long classId, HttpServletResponse response) {
        List<String> fileName = new ArrayList<>();
        List<String> url = new ArrayList<>();
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            List<Attendance> attendanceList = attendanceDao.listAttendanceByKlassSeminarId(klassSeminar.getId());
            for (Attendance item : attendanceList) {
                fileName.add(item.getReportName());
                url.add(item.getReportUrl());
            }
            try {
                FileUtil.downloadAllFiles(response, url, fileName);
            } catch (Exception e) {
                //TODO
            }
        } else {
            //TODO KlassSeminarNotFound
        }
    }

    public Attendance nextAttendance(Long seminarId,Long classId,Long attendanceId,Teacher teacher){
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId,classId);
        if(klassSeminar!=null){
            if(teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())){
                Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
                if(attendance!=null){
                    //当前小组状态更新
                    attendance.setPresented(false);
                    attendanceDao.updateAttendance(attendance);

                    //找到下一组，当前讨论课的展示小组序号+1
                    Attendance nextAttendance = attendanceDao.selectAttendanceByKlassSeminarIdAndTeamOrder(
                            klassSeminar.getId(),attendance.getTeamOrder()+1
                    );
                    nextAttendance.setPresented(true);
                    return  nextAttendance;
                }else{
                    //TODO AttendanceNotfound
                    return null;
                }
            }else {
                //TODO 权限
                return null;
            }
        }else{
            //TODO SEMINARNOTFOUND
            return null;
        }
    }
}
