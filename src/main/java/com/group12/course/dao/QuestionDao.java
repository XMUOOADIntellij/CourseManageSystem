package com.group12.course.dao;

import com.group12.course.entity.Question;
import com.group12.course.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 提问Dao层
 * @author Y Jiang
 * @date 2018/12/12
 */
@Component
public class QuestionDao {
    @Autowired
    QuestionMapper questionMapper;

    public Integer deleteQuestionByKlassSeminarId(Long klassSeminarId){
        return questionMapper.deleteQuestionByKlassSeminarId(klassSeminarId);
    }

    public Long insertQuetion(Question record){
        questionMapper.insertQuestion(record);
        return record.getId();
    }

    public Integer updateQuestion(Question record){
        return questionMapper.updateQuestion(record);
    }

    public Question getQustionById(Long id){
        return questionMapper.selectQuestionById(id);
    }

    public List<Question> listQuestionByKlassSeminarIdAndAttendanceId(Long klassSeminarId,Long attendanceId){
        return questionMapper.listQuestionByKlassSeminarIdAndAttendanceId(klassSeminarId,attendanceId);
    }

    public List<Question> listQuestionByKlassSeminarIdAndTeamId(Long klassSeminarId,Long teamId){
        return questionMapper.listQuestionByKlassSeminarIdAndTeamId(klassSeminarId,teamId);
    }
    public List<Question> listQuestionByKlassSeminarId(Long klassSeminarId){
        return questionMapper.listQuestionByKlassSeminarId(klassSeminarId);
    }
}
