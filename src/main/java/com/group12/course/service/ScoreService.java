package com.group12.course.service;

import com.group12.course.exception.RecordNotFoundException;
import com.group12.course.exception.UnauthorizedOperationException;
import com.group12.course.dao.*;
import com.group12.course.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 与分数相关 service
 * @author Y Jiang
 * @date 2018/12/22
 */
@Service
public class ScoreService {
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    RoundDao roundDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    SeminarDao seminarDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;
    @Autowired
    AttendanceDao attendanceDao;

    public Integer modifyScoreByAttendance(Teacher teacher, SeminarScore record, Long attendanceId) {
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);

        SeminarScore seminarScore;
        if (attendance != null) {
            seminarScore = scoreDao.selectSeminarScoreByKlassSeminarIdAndTeamId(
                    attendance.getKlassSeminar().getId(), attendance.getTeam().getId());
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
        if (teacher.getId().equals(seminarScore.getKlassSeminar().
                getSeminar().getCourse().getTeacher().getId())) {

            record.setKlassSeminar(attendance.getKlassSeminar());
            record.setTeam(attendance.getTeam());
            return scoreDao.updateSeminarScore(seminarScore);

        } else {
            throw new UnauthorizedOperationException("只有当前课的老师可更改分数");
        }
    }

    public Integer modiftScoreBySeminar(Teacher teacher, SeminarScore seminarScore, Long seminarId) {
        Team team = teamDao.getTeamById(seminarScore.getTeam().getId());
        KlassSeminar klassSeminar;
        if (team != null) {
            klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, team.getKlass().getId());
            if (klassSeminar != null) {
                if (teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())) {
                    seminarScore.setTeam(team);
                    seminarScore.setKlassSeminar(klassSeminar);
                    return scoreDao.updateSeminarScore(seminarScore);
                } else {
                    throw new UnauthorizedOperationException("只有当前课的老师可更改分数");
                }
            } else {
                throw new RecordNotFoundException("找不到班级讨论课");
            }
        } else {
            throw new RecordNotFoundException("找不到小组记录");
        }
    }

    /**
     * 老师获得学生课程下的每轮成绩
     * @param teacher  老师对象
     * @param courseId 课程Id
     */
    public List<RoundScore> getCourseRoundScoreByTeacher(Teacher teacher, Long courseId) {
        Course course = courseDao.getCourse(courseId);

        List<Long> roundIdList = new ArrayList<>();
        if (course != null) {
            if (course.getTeacher().getId().equals(teacher.getId())) {
                for (Round item : roundDao.getRoundByCourseId(course.getId())) {
                    roundIdList.add(item.getId());
                }
                return scoreDao.listRoundScoreByRoundIdList(roundIdList);
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可查看该课程所有成绩");
            }
        } else {
            throw new RecordNotFoundException("找不到该课程记录");
        }
    }

    /**
     * 老师获得轮下的讨论课成绩
     *
     * @param roundId 轮次id
     * @param teamId  队伍id
     * @return 讨论课成绩list
     */
    public List<SeminarScore> getRoundSeminarScore(Long roundId, Long teamId) {
        Round round = roundDao.getRound(roundId);
        Klass klass;
        if (teamDao.getTeamById(teamId) != null) {
            klass = teamDao.getTeamById(teamId).getKlass();
        } else {
            throw new RecordNotFoundException("找不到该小组信息");
        }
        List<Long> klassSeminarId = new ArrayList<>();
        if (round != null && klass != null) {
            for (Seminar seminar : seminarDao.listSeminarByRoundId(roundId)) {
                klassSeminarId.add(
                        klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminar.getId(), klass.getId()).getId()
                );
            }
            return scoreDao.listSeminarScoreByKlassSeminarIdListAndTeamId(klassSeminarId, teamId);
        } else {
            throw new RecordNotFoundException("找不到该轮次信息");
        }
    }

    public List<SeminarScore> getKlassSeminarScore(Teacher teacher, Long seminarId, Long classId) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            if (teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())) {
                return scoreDao.listSeminarScoreByKlassSeminarId(klassSeminar.getId());
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可查看该班级讨论课分数");
            }
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
    }

    public SeminarScore getAttendanceScore(Long attendanceId) {
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        if (attendance != null) {
            return scoreDao.selectSeminarScoreByKlassSeminarIdAndTeamId(
                    attendance.getKlassSeminar().getId(), attendance.getTeam().getId()
            );
        } else {
            throw new RecordNotFoundException("Attendance Not Found");
        }

    }
}
