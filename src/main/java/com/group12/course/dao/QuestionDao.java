package com.group12.course.dao;

import com.group12.course.entity.Question;
import com.group12.course.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionDao {
    @Autowired
    QuestionMapper questionMapper;

    public Integer deleteQuestionByKlassSeminarId(Long klassSeminarId){
        return questionMapper.deleteQuestionByKlassSeminarId(klassSeminarId);
    }

    public List<Question> getQuestionByKlassSeminarId(Long klassSeminarId){
        return questionMapper.selectQuestionByKlassSeminarId(klassSeminarId);
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

}
