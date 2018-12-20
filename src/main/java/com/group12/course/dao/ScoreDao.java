package com.group12.course.dao;

import com.group12.course.entity.*;
import com.group12.course.mapper.RoundScoreMapper;
import com.group12.course.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 讨论课分数Dao层
 * @author Y Jiang
 * @date 2018/12/19
 */
@Component
public class ScoreDao {
    @Autowired
    SeminarScoreMapper seminarScoreMapper;
    @Autowired
    RoundScoreMapper roundScoreMapper;

    @Autowired
    CourseDao courseDao;
    @Autowired
    RoundDao roundDao;
    @Autowired
    SeminarDao seminarDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;

    /**
     * 计算平均分
     * @param score 分数
     * @return BigDecimal均分
     */
    private BigDecimal averageScore(List<BigDecimal> score){
        BigDecimal result = new BigDecimal(0);
        for(BigDecimal item:score){
            result = result.add(item);
        }
        //TODO 换成除以每轮的报名数
        return result.divide(new BigDecimal(score.size())).setScale(2, RoundingMode.HALF_DOWN);
    }

    /**
     * 计算最高分
     * @param score 分数
     * @return BigDecimal均分
     */
    private BigDecimal maxScore(List<BigDecimal> score){
        BigDecimal result = new BigDecimal(0);
        for(BigDecimal item:score){
            if(item.compareTo(result)>0){
                result = item;
            }
        }
        return result;
    }

    /**
     * 计算总分
     * @param presentationScore 展示分
     * @param questionScore 提问分
     * @param reportScore 报告分
     * @param course 课程分
     * @return BigDecimal 总分
     */
    private BigDecimal calculateTotalScore(BigDecimal presentationScore,BigDecimal questionScore,
                                           BigDecimal reportScore,Course course){
        return
                (
                        presentationScore.multiply(new BigDecimal(course.getPresentationPercentage()))
                                .add(questionScore.multiply(new BigDecimal(course.getQuestionPercentage())))
                                .add(reportScore.multiply(new BigDecimal(course.getReportPercentage())))
                )
                        .divide(new BigDecimal(course.getQuestionPercentage()
                                +course.getPresentationPercentage()
                                +course.getReportPercentage()))
                        .setScale(2);
    }

    /**
     * 通过讨论课成绩计算轮次成绩
     * @param seminarScores 此轮的讨论课成绩
     * @return RoundScore
     * Method 1->平均分 0->最高分
     */
    private RoundScore calculateRoundScore(List<SeminarScore> seminarScores,Round round){

        RoundScore roundScore = new RoundScore();

        List<BigDecimal> presentationScore = new ArrayList<>();
        List<BigDecimal> questionScore = new ArrayList<>();
        List<BigDecimal> reportScore = new ArrayList<>();
        for(SeminarScore item:seminarScores){
            presentationScore.add(item.getPresentationScore());
            questionScore.add(item.getQuestionScore());
            reportScore.add(item.getReportScore());
        }

        roundScore.setPresentationScore(
                round.getPresentationScoreMethod() == 1?
                        averageScore(presentationScore):maxScore(presentationScore)
        );

        roundScore.setReportScore(
                round.getReportScoreMethod() == 1?
                        averageScore(reportScore):maxScore(reportScore)
        );

        roundScore.setQuestionScore(
                round.getQuestionScoreMethod() == 1?
                        averageScore(questionScore):maxScore(questionScore)
        );

        return roundScore;
    }

    public Integer deleteSeminarScoreByKlassSeminarId(Long klassSeminarId){
        // TODO 更新RoundDao的记录
        return seminarScoreMapper.deleteSeminarScoreByKlassSeminarId(klassSeminarId);
    }

    public SeminarScore selectSeminarScoreByKlassSeminarIdAndTeamId(Long klassSeminarId,Long teamId){
        return seminarScoreMapper.selectSeminarScoreByKlassSeminarIdAndTeamId(klassSeminarId,teamId);
    }

    /**
     * 修改分数
     * @param seminarScore
     * @return
     */
    public Integer updateSeminarScore(SeminarScore seminarScore){
        Course course = courseDao.getCourse(seminarScore.getKlassSeminar().getKlass().getCourse().getId());
        seminarScoreMapper.updateSeminarScore(seminarScore);
        seminarScore = seminarScoreMapper.selectSeminarScoreByKlassSeminarIdAndTeamId(
                seminarScore.getKlassSeminar().getId(),seminarScore.getTeam().getId()
        );

        //按照比例更改总分
        seminarScore.setTotalScore(
                calculateTotalScore(seminarScore.getPresentationScore(),seminarScore.getQuestionScore(),
                        seminarScore.getReportScore(),course));

        seminarScoreMapper.updateSeminarScore(seminarScore);

        //更改RoundScore
        Round round = roundDao.getRound(seminarScore.getKlassSeminar().getSeminar().getRound().getId());
        List<KlassSeminar> klassSeminarList = new ArrayList<>();

        //获得班级
        Klass klass = seminarScore.getKlassSeminar().getKlass();

        //找到该轮的班级讨论课
        for(Seminar seminar:seminarDao.listSeminarByRoundId(round.getId())){
            klassSeminarList.add(
                    klassSeminarDao.getKlassSeminarBySeminarIdAndClassId(seminar.getId(),klass.getId())
            );
        }

        //根据班级讨论课和小组找到该轮该小组分数
        List<SeminarScore> seminarScoreList = new ArrayList<>();
        for(KlassSeminar item : klassSeminarList){
            seminarScoreList.add(
                    selectSeminarScoreByKlassSeminarIdAndTeamId(item.getId(),seminarScore.getTeam().getId())
            );
        }

        //计算RoundScore
        RoundScore roundScore = calculateRoundScore(seminarScoreList,round);
        roundScore.setTotalScore(
                calculateTotalScore(roundScore.getPresentationScore(),roundScore.getQuestionScore(),
                        roundScore.getReportScore(),course)
        );
        roundScore.setRound(round);
        roundScore.setTeam(seminarScore.getTeam());
        roundScoreMapper.updateRoundScore(roundScore);
        return null;
    }

    public List<RoundScore> listRoundScoreByRoundIdList(List<Long>roundId){
        return roundScoreMapper.listRoundScoreByRoundIdList(roundId);
    }




}
