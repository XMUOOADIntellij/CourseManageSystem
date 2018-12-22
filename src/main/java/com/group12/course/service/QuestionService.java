package com.group12.course.service;

import com.group12.course.dao.CourseDao;
import com.group12.course.dao.KlassSeminarDao;
import com.group12.course.dao.QuestionDao;
import com.group12.course.dao.TeamDao;
import com.group12.course.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                return questionDao.getQuestionByKlassSeminarId(klassSeminar.getId());
            } else {
                //todo 权限
                return null;
            }
        } else {
            return null;
            //TODO SeminarNotFound
        }
    }

    public List<Question> getAttendanceQuestion(Teacher teacher,Long seminarId,Long klassId,Long attendanceId){
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, klassId);
        if (klassSeminar != null) {
            if (teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())) {
                return questionDao.getQuestionByKlassSeminarIdAndAttendanceId(klassSeminar.getId(),attendanceId);
            } else {
                //todo 权限
                return null;
            }
        } else {
            return null;
            //TODO SeminarNotFound
        }
    }

    public Long askQuestion(Long seminarId, Long classId, Question question, Student student) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            question.setKlassSeminar(klassSeminar);
            question.setTeam(teamDao.getTeamByStudentIdAndCourseId(student.getId(), klassSeminar.getSeminar().getCourse().getId()));
            question.setStudent(student);
            question.setScore(null);
            question.setSelected(false);
            return questionDao.insertQuetion(question);
        } else {
            //TODO SeminarNotFound
            return null;
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

}
