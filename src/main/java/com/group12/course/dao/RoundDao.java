package com.group12.course.dao;

import com.group12.course.entity.Round;
import com.group12.course.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Round Dao 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Component
public class RoundDao {
    @Autowired
    RoundMapper roundMapper;
    @Autowired
    CourseDao courseDao;

    public Round getRound(Long id){
        return roundMapper.selectRoundById(id);
    }

    public int deleteRound(Long id){
        return roundMapper.deleteRound(id);
    }

    public int addRound(Long courseId){

        Round round = new Round();
        round.setCourse(courseDao.getCourse(courseId));

        List<Round> roundList = getRoundByCourseId(courseId);
        Integer count = roundList.size();
        Integer serial = count+1;
        round.setRoundSerial(serial);
        return roundMapper.addRound(round);
    }

    public int updateRound(Round round){
        return roundMapper.updateRound(round);
    }

    public List<Round> getRoundByCourseId(Long id){
        return roundMapper.selectRoundByCourseId(id);
    }
}
