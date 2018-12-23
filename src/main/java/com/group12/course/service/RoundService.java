package com.group12.course.service;

import com.group12.course.dao.CourseDao;
import com.group12.course.dao.RoundDao;
import com.group12.course.dao.RoundScoreDao;
import com.group12.course.dao.SeminarDao;
import com.group12.course.entity.Course;
import com.group12.course.entity.Round;
import com.group12.course.entity.RoundScore;
import com.group12.course.entity.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Round Service 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Service
public class RoundService {

    @Autowired
    RoundDao roundDao;
    @Autowired
    RoundScoreDao roundScoreDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    SeminarDao seminarDao;

    /**
     * 获取某轮次下的讨论课
     * @param roundId
     * @return
     */
    public List<Seminar> getSeminarByRoundId(Long roundId){
        return seminarDao.listSeminarByRoundId(roundId);
    }

    /**
     * 根据轮次 id 获取轮次
     * @param roundId
     * @return
     */
    public Round getRound(Long roundId){
        return roundDao.getRound(roundId);
    }

    /**
     * 根据轮次 id 修改轮次
     * @param round
     * @param round
     */
    public int updateRound(Round round){
        return  roundDao.updateRound(round);
    }


    /**
     * 计算某课程下的轮次个数
     * @param courseId
     * @return
     */
    public int countRoundByCourseId(Long courseId){
        List<Round> roundList = roundDao.getRoundByCourseId(courseId);
        Integer count = roundList.size();
        return count;
    }

    /**
     * 创建轮次
     * @param round
     */
    public int addRound(Round round){
        Integer serial = countRoundByCourseId(round.getCourse().getId())+1;
        round.setRoundSerial(serial);
        return roundDao.addRound(round);
    }

    /**
     * 获取课程下的所有轮次
     * @param courseId
     * @return
     */
    public List<Round> getRoundByCourseId(Long courseId){
        return roundDao.getRoundByCourseId(courseId);
    }

    /**
     * 获取某伦次下的所有成绩
     * @param roundId
     * @return
     */
    public List<RoundScore> getRoundScoreByRoundId(Long roundId){
        return roundScoreDao.getRoundScoreByRoundId(roundId);
    }

    /**
     * 获取某伦次下某个小组的成绩
     * @param roundId
     * @return
     */
    public RoundScore getRoundScoreByRoundIdAndTeamId(Long roundId,Long teamId){
        return roundScoreDao.getRoundScoreByRoundIdAndTeamId(roundId,teamId);
    }

    /**
     * 根据展示成绩、提问成绩、报告成绩以及计分策略计算每轮的总成绩
     * @param roundScore
     * @return
     */
    public BigDecimal calculateTotalScore(RoundScore roundScore){

        Round round = roundScore.getRound();
        Course course = courseDao.getCourse(round.getCourse().getId());
        BigDecimal p1 = new BigDecimal(course.getPresentationPercentage());
        BigDecimal p2 = new BigDecimal(course.getQuestionPercentage());
        BigDecimal p3 = new BigDecimal(course.getReportPercentage());

        BigDecimal score1 = (roundScore.getPresentationScore()).multiply(p1);
        BigDecimal score2 = score1.add(roundScore.getQuestionScore().multiply(p2));
        BigDecimal score3 = score2.add(roundScore.getReportScore().multiply(p3));
        BigDecimal totalScore = score3.divide(new BigDecimal(100));
        return totalScore;
    }

    /**
     * 更新某伦次下某小组的成绩
     * @param roundScore
     * @return
     */
    public int updateRoundScore(RoundScore roundScore){

        BigDecimal totalScore = calculateTotalScore(roundScore);
        roundScore.setTotalScore(totalScore);
        return roundScoreDao.updateRoundScore(roundScore);
    }

}
