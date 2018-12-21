package com.group12.course.service;

import com.group12.course.dao.CourseDao;
import com.group12.course.dao.KlassSeminarDao;
import com.group12.course.dao.QuestionDao;
import com.group12.course.dao.TeamDao;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Question;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
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

    public List<Question> getAllQuestion(Long seminarId,Long klassId){
        return  questionDao.getQuestionByKlassSeminarId(
                klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId,klassId).getId()
        );
    }

    public Long askQuestion(Long seminarId, Long classId, Question question, Student student){
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId,classId);
        if(klassSeminar!=null){
            question.setKlassSeminar(klassSeminar);
            question.setTeam(teamDao.getTeamByStudentId(student.getId()));
            question.setStudent(student);
            question.setScore(null);
            question.setSelected(false);
            return questionDao.insertQuetion(question);
        }else{
            //TODO KlassSeminarNotFound
            return null;
        }
    }

    public Integer scoreQuestion(Teacher teacher,Question question){

    if(questionDao.getQustionById(question.getId()).
            getKlassSeminar().getSeminar().
            getCourse().getTeacher().getId().equals(teacher.getId())){
        return questionDao.updateQuestion(question);
    }else{
        //TODO 权限
        return null;
    }
    }

}
