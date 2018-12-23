package com.group12.course.dao;

import com.group12.course.entity.RoundScore;
import com.group12.course.mapper.RoundScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoundScoreDao {

    @Autowired
    RoundScoreMapper roundScoreMapper;

    public List<RoundScore> getRoundScoreByRoundId(Long roundId){
        return roundScoreMapper.listRoundScoreByRoundId(roundId);
    }

    public RoundScore getRoundScoreByRoundIdAndTeamId(Long roundId,Long teamId){
        return roundScoreMapper.selectRoundScoreByRoundIdAndTeamId(roundId,roundId);
    }

    /**
     * 修改某轮某小组的成绩
     * @param roundScore
     * @return
     */
    public int updateRoundScore(RoundScore roundScore){
        return roundScoreMapper.updateRoundScore(roundScore);
    }

}
