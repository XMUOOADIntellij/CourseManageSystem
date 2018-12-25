package com.group12.course.service;

import com.group12.course.dao.*;
import com.group12.course.exception.RecordNotFoundException;
import com.group12.course.exception.UnauthorizedOperationException;
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
    @Autowired
    CourseDao courseDao;

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
            throw new RecordNotFoundException("找不到该班级讨论课");
        }
    }

    /**
     * 获得当前班级讨论课正在展示的小组
     *
     * @param classId   班级
     * @param seminarId 讨论课
     * @return 正在展示的小组
     */
    public Attendance getCurrentAttendanceBySeminarIdAndClassId(Long classId, Long seminarId) {
        KlassSeminar klassSeminar =
                klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            return attendanceDao.selectPresentedAttendanceByKlassSeminarId(klassSeminar.getId());
        } else {
            throw new RecordNotFoundException("找不到该班级讨论课");
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
                throw new RecordNotFoundException("找不到该班级讨论课");
            }
        } else {
            throw new RecordNotFoundException("讨论课不存在");
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
                throw new UnauthorizedOperationException("只能更改自己组的展示顺序");
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
                throw new UnauthorizedOperationException("只能取消自己组的报名");
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
                throw new RecordNotFoundException("找不到该班级讨论课");
            }
        } else {
            throw new RecordNotFoundException("找不到该讨论课");
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
                throw new UnauthorizedOperationException("只能给自己组上传报告");
            }
        } else {
            throw new RecordNotFoundException("Attendance不存在");
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
                throw new UnauthorizedOperationException("只能给自己组上传ppt");
            }
        } else {
            throw new RecordNotFoundException("上传ppt对应的展示报名不存在");
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
                logger.trace("下载所有ppt出错: " + e.getMessage());
            }
        } else {
            throw new RecordNotFoundException("下载ppt的班级讨论课不存在");
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
                logger.trace("下载所有ppt出错: " + e.getMessage());
            }
        } else {
            throw new RecordNotFoundException("下载报告的班级讨论课不存在");
        }
    }

    public Attendance nextAttendance(Long seminarId, Long classId, Long attendanceId, Teacher teacher) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            if (teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())) {
                Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
                if (attendance != null) {
                    //当前小组状态更新
                    attendance.setPresented(false);
                    attendanceDao.updateAttendance(attendance);

                    //找到下一组，当前讨论课的展示小组序号+1
                    Attendance nextAttendance = attendanceDao.selectAttendanceByKlassSeminarIdAndTeamOrder(
                            klassSeminar.getId(), attendance.getTeamOrder() + 1
                    );
                    nextAttendance.setPresented(true);
                    return nextAttendance;
                } else {
                    throw new RecordNotFoundException("Attendance不存在");
                }
            } else {
                throw new UnauthorizedOperationException("此节讨论课的老师才能操作");
            }
        } else {
            throw new RecordNotFoundException("该班级讨论课不存在");
        }
    }
}
