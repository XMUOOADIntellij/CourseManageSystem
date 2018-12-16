package com.group12.course.dao;

import com.group12.course.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionDao {
    @Autowired
    QuestionMapper questionMapper;

    public Integer deleteQuestionByKlassSeminarId(Long klassSeminarId){
        return questionMapper.deleteQuestionByKlassSeminarId(klassSeminarId);
    }


}
