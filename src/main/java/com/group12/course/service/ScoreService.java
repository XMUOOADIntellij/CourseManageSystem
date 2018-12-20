package com.group12.course.service;

import com.group12.course.dao.CourseDao;
import com.group12.course.dao.RoundDao;
import com.group12.course.dao.ScoreDao;
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

    /**
     * 老师获得学生课程下的成绩
     * @param teacher 老师对象
     * @param courseId 课程Id
     */
    public List<RoundScore> getStudentRoundScore(Teacher teacher,Long courseId){
        Course course = courseDao.getCourse(courseId);
        List<Long> roundIdList = new ArrayList<>();
        if(course!=null){
            for(Round item : roundDao.getRoundByCourseId(course.getId())){
                roundIdList.add(item.getId());
            }
            return scoreDao.listRoundScoreByRoundIdList(roundIdList);
        }
        else{
            //TODO COURSENOTFOUND
            return null;
        }
    }
}
