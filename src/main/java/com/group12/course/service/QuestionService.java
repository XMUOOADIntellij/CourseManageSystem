package com.group12.course.service;

import com.group12.course.exception.RecordNotFoundException;
import com.group12.course.exception.UnauthorizedOperationException;
import com.group12.course.dao.CourseDao;
import com.group12.course.dao.KlassSeminarDao;
import com.group12.course.dao.QuestionDao;
import com.group12.course.dao.TeamDao;
import com.group12.course.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 提问Service层
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
    CourseDao courseDao;

    public List<Question> getAllQuestion(Teacher teacher, Long seminarId, Long klassId) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, klassId);
        if (klassSeminar != null) {
            if (teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())) {
                return questionDao.listQuestionByKlassSeminarId(klassSeminar.getId());
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可查看");
            }
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
    }

    public List<Question> getAttendanceQuestion(Teacher teacher, Long seminarId, Long klassId, Long attendanceId) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, klassId);
        if (klassSeminar != null) {
            if (teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())) {
                return questionDao.listQuestionByKlassSeminarIdAndAttendanceId(klassSeminar.getId(), attendanceId);
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可查看");
            }
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
    }

    public Question askQuestion(Long seminarId, Long classId, Question question, Student student) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            question.setKlassSeminar(klassSeminar);
            question.setTeam(teamDao.getTeamByStudentIdAndCourseId(student.getId(), klassSeminar.getSeminar().getCourse().getId()));
            question.setStudent(student);
            question.setScore(null);
            question.setSelected(false);

            if (questionDao.insertQuetion(question) != 0) {
                return question;
            } else {
                return null;
            }
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
    }

    public Integer scoreQuestion(Question record, Teacher teacher) {
        //提问存在
        Question question = questionDao.getQustionById(record.getId());
        if (question != null) {
            // 是当前老师课上的提问
            if (question.getKlassSeminar().getSeminar().getCourse().getId()
                    .equals(teacher.getId())) {
                return questionDao.updateQuestion(record);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Question answerQuestion(Teacher teacher, Long seminarId, Long classId, Long attendanceId) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);

        if (klassSeminar != null) {
            if (teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())) {

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
                        return o1.getId().intValue()-o2.getId().intValue();
                    }
                });

                //按照未提问优先为主，先到先得选择顺序
                Question result = null;
                int minCount=Integer.MAX_VALUE;
                for(Question item:attendanceQuestion){
                    Integer count = questionCount.get(item.getTeam().getId());
                    if(count==null){
                        return item;
                    }
                    else if(count<minCount){
                        result = item;
                        minCount = questionCount.get(item.getTeam().getId());
                    }
                }
                return  result;
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可抽取提问");
            }
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
    }


}
