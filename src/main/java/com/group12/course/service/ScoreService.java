package com.group12.course.service;

import com.group12.course.dao.CourseDao;
import com.group12.course.dao.ScoreDao;
import com.group12.course.entity.SeminarScore;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    CourseDao courseDao;

    public Integer modifyScore(Teacher teacher, SeminarScore seminarScore){
        if(teacher.getId().equals(
                scoreDao.selectSeminarScoreByKlassSeminarIdAndTeamId(
                seminarScore.getKlassSeminar().getId(),seminarScore.getTeam().getId())
                .getKlassSeminar().getKlass().getCourse().getTeacher().getId())) {
            return scoreDao.updateSeminarScore(seminarScore);
        }
        else{
            //TODO 权限
            return null;
        }
    }

    public void getStudentScore(Teacher teacher,SeminarScore seminarScore){
        
    }

    public void getCourseScore(Student student){
      
    }
}
