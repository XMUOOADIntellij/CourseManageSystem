package com.group12.course.service;

import com.group12.course.dao.*;
import com.group12.course.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            return null;
            //TODO AttendanceNOTFOUND
        }
        if (teacher.getId().equals(seminarScore.getKlassSeminar().
                getSeminar().getCourse().getTeacher().getId())) {

            record.setKlassSeminar(attendance.getKlassSeminar());
            record.setTeam(attendance.getTeam());
            return scoreDao.updateSeminarScore(seminarScore);

        } else {
            //TODO 权限
            return null;
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
                    return null;
                    //todo 权限
                }
            } else {
                //TODO SEMINARNOTFOUND
                return null;
            }
        } else {
            //TODO teamNotFound
            return null;
        }
    }

    public void getStudentScore(Teacher teacher, SeminarScore seminarScore) {

    }

    /**
     * 老师获得学生课程下的每轮成绩
     *
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
                return null;
                //todo 权限
            }
        } else {
            //TODO COURSENOTFOUND
            return null;
        }
    }

    public List<SeminarScore> getRoundSeminarScore(Long roundId, Long teamId) {
        Round round = roundDao.getRound(roundId);
        Klass klass;
        if (teamDao.getTeamById(teamId) != null) {
            klass = teamDao.getTeamById(teamId).getKlass();
        } else {
            //TODO TEAMNOTFOUND
            return null;
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
            //todo RoundNotFound
            //klassNOTFOUND
            return null;
        }
    }

    public SeminarScore getAttendanceScore(Long attendanceId) {
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        if (attendance != null) {
            return scoreDao.selectSeminarScoreByKlassSeminarIdAndTeamId(
                    attendance.getKlassSeminar().getId(), attendance.getTeam().getId()
            );
        } else {
            //TODO ATTENDANCENOTFOUND
            return null;
        }

    }
}
