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
 *
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
    @Autowired
    KlassDao klassDao;

    /**
     * 为某次的展示打分
     *
     * @param teacher      老师
     * @param record       记录
     * @param attendanceId 展示报名记录id
     * @return 1成功 0失败
     */
    public Integer modifyScoreByAttendance(Teacher teacher, SeminarScore record, Long attendanceId) {
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);
        if (attendance != null) {
            //检验老师的修改权限
            if (attendance.getKlassSeminar().getKlass().getCourse()
                    .getTeacher().getId().equals(teacher.getId())) {
                record.setKlassSeminar(attendance.getKlassSeminar());
                record.setTeam(attendance.getTeam());

                //进行时只更改，不刷新
                if (attendance.getKlassSeminar().getSeminarStatus() == 1) {
                    return scoreDao.updateSeminarScoreWhenAttendance(record);
                } else {
                    //结束后修改，刷新
                    return scoreDao.updateSeminarScoreAfterKlass(record);
                }

            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可更改分数");
            }
        } else {
            throw new RecordNotFoundException("没有找到展示课报名记录");
        }
    }

    public Integer modiftScoreBySeminar(Teacher teacher, SeminarScore seminarScore, Long seminarId, Long classId) {
        Team team = teamDao.getTeamById(seminarScore.getTeam().getId());
        KlassSeminar klassSeminar;
        if (team != null) {
            klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
            if (klassSeminar != null) {
                if (teacher.getId().equals(klassSeminar.getKlass().getCourse().getTeacher().getId())) {
                    seminarScore.setTeam(team);
                    seminarScore.setKlassSeminar(klassSeminar);
                    return scoreDao.updateSeminarScoreAfterKlass(seminarScore);
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
     * 老师获得自己课程学生一轮下的成绩
     *
     * @param teacher  老师对象
     * @param courseId 课程Id
     * @param roundId  轮数id
     */
    public List<RoundScore> getCourseRoundScoreByTeacher(Teacher teacher, Long courseId, Long roundId) {
        Course course = courseDao.getCourse(courseId);
        if (course != null) {
            if (course.getTeacher().getId().equals(teacher.getId())) {
                //当前课程下有的队伍
                List<Team> teams;
                List<Long> teamIds = new ArrayList<>();
                teams = teamDao.getTeamByCourseId(courseId);

                for (Team item : teams) {
                    teamIds.add(item.getId());
                }
                return scoreDao.listRoundScoreByRoundIdAndTeamIdList(teamIds, roundId);
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可查看该课程所有成绩");
            }
        } else {
            throw new RecordNotFoundException("找不到该课程记录");
        }
    }

    public RoundScore getCourseRoundScoreByStudent(Student student,Long courseId,Long roundId){
        Course course = courseDao.getCourse(courseId);
        if(course!=null){
            Team team;
            if(course.getTeamMainCourse()==null){
                team = teamDao.getTeamByStudentIdAndCourseId(student.getId(),courseId);
            }else{
                team = teamDao.getTeamByStudentIdAndCourseId(student.getId(),course.getTeamMainCourse().getId());
            }
            return scoreDao.selectRoundScoreByRoundIdAndTeamId(roundId,team.getId());
        }else{
            throw new RecordNotFoundException("课程记录没有找到");
        }
    }

    public List<RoundScore> getCourseRoundScoreByStudent(Student student, Long courseId) {
        Course course = courseDao.getCourse(courseId);
        if (course != null) {
            Course teamMainCourse = course.getTeamMainCourse();
            Course mainSeminarCourse = course.getSeminarMainCourse();
            List<Long> roundIds = new ArrayList<>();
            Team team;
            if (teamMainCourse != null) {
                team = teamDao.getTeamByStudentIdAndCourseId(student.getId(), teamMainCourse.getId());
            } else {
                team = teamDao.getTeamByStudentIdAndCourseId(student.getId(), courseId);
            }
            if (mainSeminarCourse == null) {
                for (Round item : roundDao.getRoundByCourseId(course.getId())) {
                    roundIds.add(item.getId());
                }
            } else {
                for (Round item : roundDao.getRoundByCourseId(mainSeminarCourse.getId())) {
                    roundIds.add(item.getId());
                }
            }
            return scoreDao.listRoundScoreByRoundIdListAndTeamId(roundIds, team.getId());
        } else {
            throw new RecordNotFoundException("找不到该课程记录");
        }
    }

    /**
     * 老师、学生获得轮下的讨论课成绩
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
            throw new RecordNotFoundException("找不到展示报名");
        }

    }
}
