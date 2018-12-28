package com.group12.course.service;

import com.group12.course.dao.*;
import com.group12.course.exception.RecordNotFoundException;
import com.group12.course.exception.UnauthorizedOperationException;
import com.group12.course.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 提问Service层
 *
 * @author Y Jiang
 * @date 2018/12/12
 */
@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    KlassDao klassDao;
    @Autowired
    AttendanceDao attendanceDao;

    /**
     * 获取某个展示报名已经被提问的问题
     *
     * @param teacher      老师
     * @param seminarId    讨论课id
     * @param klassId      班级id
     * @param attendanceId 展示报名
     * @return 提问列表
     */
    public List<Question> getAttendanceQuestion(Teacher teacher, Long seminarId, Long klassId, Long attendanceId) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, klassId);
        if (klassSeminar != null) {
            if (klassSeminar.getKlass().getCourse().getTeacher().getId().equals(teacher.getId())) {
                return questionDao.listQuestionByKlassSeminarIdAndAttendanceId(klassSeminar.getId(), attendanceId);
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可查看");
            }
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
    }

    /**
     * 学生讨论课提问
     *
     * @param seminarId 讨论课id
     * @param classId   班级id
     * @param question  提问
     * @param student   学生对象
     * @return Question
     */
    public Question askQuestion(Long seminarId, Long classId, Question question, Student student) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        Klass klass = klassDao.getKlass(classId);
        if (klass != null) {
            if (klassSeminar != null) {

                question.setKlassSeminar(klassSeminar);
                question.setStudent(student);
                question.setScore(null);
                question.setSelected(false);

                Course course = klass.getCourse();
                //属于被共享分组的课程
                Team team;
                if (course.getTeamMainCourse() != null) {
                    team = teamDao.getTeamByStudentIdAndCourseId(student.getId(), course.getTeamMainCourse().getId());
                } else {
                    team = teamDao.getTeamByStudentIdAndCourseId(student.getId(), course.getId());
                }
                if (teamDao.checkStudentIsInSpecialTeam(student.getId(), team.getId())) {
                    return null;
                } else {
                    question.setTeam(team);
                    if (questionDao.insertQuetion(question) != 0) {
                        return question;
                    } else {
                        return null;
                    }
                }
            } else {
                throw new RecordNotFoundException("找不到班级讨论课");
            }
        } else {
            throw new RecordNotFoundException("找不到班级");
        }
    }

    public Integer scoreQuestion(Question record, Teacher teacher) {
        //提问存在
        Question question = questionDao.getQustionById(record.getId());
        if (question != null) {
            // 是当前老师课上的提问
            if (question.getKlassSeminar().getKlass().getCourse()
                    .getTeacher().getId().equals(teacher.getId())) {
                return questionDao.updateQuestion(record);
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可更改");
            }
        } else {
            throw new RecordNotFoundException("提问不存在");
        }
    }


    /**
     * 老师抽取提问
     *
     * @param teacher      老师对象
     * @param seminarId    讨论课
     * @param classId      班级
     * @param attendanceId 被抽取的展示
     * @return Question
     */
    public Question answerQuestion(Teacher teacher, Long seminarId, Long classId, Long attendanceId) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        Attendance attendance = attendanceDao.selectAttendanceById(attendanceId);

        if (!attendance.getPresented()) {
            if (klassSeminar != null) {
                if (klassSeminar.getKlass().getCourse().getTeacher().getId()
                        .equals(teacher.getId())) {

                    List<Question> questionList = questionDao.listQuestionByKlassSeminarId(klassSeminar.getId());
                    // 小组，此次讨论课被提问次数
                    Map<Long, Integer> questionCount = new TreeMap<>();

                    // 计算提问数，提问被抽加一，提问没被抽减一
                    for (Question item : questionList) {
                        int count = item.getSelected() ? 1 : -1;
                        questionCount.merge(item.getTeam().getId(), count,
                                (valueOrigin, valueNew) -> (valueOrigin + valueNew));
                    }

                    //当前展示的提问
                    List<Question> attendanceQuestion = questionDao.listQuestionByKlassSeminarIdAndAttendanceId(
                            klassSeminar.getId(), attendanceId);

                    //按照id小的排序，小的代表先创建先提问
                    attendanceQuestion.sort(new Comparator<Question>() {
                        @Override
                        public int compare(Question o1, Question o2) {
                            return o1.getId().intValue() - o2.getId().intValue();
                        }
                    });

                    //按照未提问优先为主，先到先得选择顺序
                    Question result = null;
                    int minCount = Integer.MAX_VALUE;
                    for (Question item : attendanceQuestion) {
                        Integer count = questionCount.get(item.getTeam().getId());
                        if (count == null) {
                            return item;
                        } else if (count < minCount) {
                            result = item;
                            minCount = questionCount.get(item.getTeam().getId());
                        }
                    }
                    return result;
                } else {
                    throw new UnauthorizedOperationException("只有当前课的老师可抽取提问");
                }
            } else {
                throw new RecordNotFoundException("找不到班级讨论课");
            }
        } else {
            throw new UnauthorizedOperationException("该小组未在展示");
        }
    }

    public Question selectQuestionById(Long questionId) {
        return questionDao.getQustionById(questionId);
    }

}
