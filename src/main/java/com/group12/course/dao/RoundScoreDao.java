package com.group12.course.dao;

import com.group12.course.entity.RoundScore;
import com.group12.course.mapper.RoundScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundScoreDao {
    @Autowired
    RoundScoreMapper roundScoreMapper;

    public Integer updateRoundScore(RoundScore roundScore){
        return roundScoreMapper.updateRoundScore(roundScore);
    }
}
