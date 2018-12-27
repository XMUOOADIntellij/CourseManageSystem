package com.group12.course.dao;

import com.group12.course.exception.RecordNotFoundException;
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
 *
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
    @Autowired
    TeamDao teamDao;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    KlassRoundDao klassRoundDao;

    /**
     * 计算平均分
     *
     * @param score 分数
     * @return BigDecimal均分
     */
    private BigDecimal averageScore(List<BigDecimal> score, Integer divisor) {
        BigDecimal result = new BigDecimal(0);
        for (BigDecimal item : score) {
            result = result.add(item);
        }
        return result.divide(new BigDecimal(divisor).setScale(2, RoundingMode.HALF_DOWN));
    }

    /**
     * 计算最高分
     *
     * @param score 分数
     * @return BigDecimal均分
     */
    private BigDecimal maxScore(List<BigDecimal> score) {
        BigDecimal result = new BigDecimal(0);
        for (BigDecimal item : score) {
            if (item.compareTo(result) > 0) {
                result = item;
            }
        }
        return result;
    }

    /**
     * 计算总分
     *
     * @param presentationScore 展示分
     * @param questionScore     提问分
     * @param reportScore       报告分
     * @param course            课程分
     * @return BigDecimal 总分
     */
    private BigDecimal calculateTotalScore(BigDecimal presentationScore, BigDecimal questionScore,
                                           BigDecimal reportScore, Course course) {
        return
                (
                        presentationScore.multiply(new BigDecimal(course.getPresentationPercentage()))
                                .add(questionScore.multiply(new BigDecimal(course.getQuestionPercentage())))
                                .add(reportScore.multiply(new BigDecimal(course.getReportPercentage())))
                )
                        .divide(new BigDecimal(course.getQuestionPercentage()
                                + course.getPresentationPercentage()
                                + course.getReportPercentage()))
                        .setScale(2);
    }

    /**
     * 通过讨论课成绩计算轮次成绩
     *
     * @param seminarScores 此轮的讨论课成绩
     * @return RoundScore
     * Method 1->平均分 0->最高分
     */
    private RoundScore calculateRoundScore(List<SeminarScore> seminarScores, Round round, Klass klass) {

        RoundScore roundScore = new RoundScore();
        List<BigDecimal> presentationScore = new ArrayList<>();
        List<BigDecimal> questionScore = new ArrayList<>();
        List<BigDecimal> reportScore = new ArrayList<>();
        for (SeminarScore item : seminarScores) {
            presentationScore.add(item.getPresentationScore());
            questionScore.add(item.getQuestionScore());
            reportScore.add(item.getReportScore());
        }

        KlassRound klassRound = klassRoundDao.getKlassRoundByKlassIdAndRoundId(klass.getId(), round.getId());
        //每轮下的报名数
        Integer enrollNum = klassRound.getEnrollNumber();

        roundScore.setPresentationScore(
                round.getPresentationScoreMethod() == 1 ?
                        averageScore(presentationScore, enrollNum) : maxScore(presentationScore)
        );

        roundScore.setReportScore(
                round.getReportScoreMethod() == 1 ?
                        averageScore(reportScore, enrollNum) : maxScore(reportScore)
        );

        roundScore.setQuestionScore(
                round.getQuestionScoreMethod() == 1 ?
                        averageScore(questionScore, enrollNum) : maxScore(questionScore)
        );

        return roundScore;
    }

    /**
     * 在班级讨论课开始的时候为每个参与的小组初始化分数
     *
     * @param klassSeminarId 班级讨论课Id
     */
    public void initialScoreBeforeKlassSeminar(Long klassSeminarId) {

        //获得班级讨论课
        KlassSeminar klassSeminar =
                klassSeminarDao.selectKlassSeminarById(klassSeminarId);

        //为该班级的每个队伍插入seminarScore记录
        List<SeminarScore> seminarScoreList = new ArrayList<>();
        for (Team item : teamDao.listTeamByKlassId(klassSeminar.getKlass().getId())) {
            SeminarScore seminarScore = new SeminarScore();
            seminarScore.setTeam(item);
            seminarScore.setKlassSeminar(klassSeminar);
            if (seminarScoreMapper.selectSeminarScoreByKlassSeminarIdAndTeamId(klassSeminarId, item.getId()) == null) {
                seminarScore.setReportScore(new BigDecimal(0));
                seminarScore.setPresentationScore(new BigDecimal(0));
                seminarScore.setQuestionScore(new BigDecimal(0));
                seminarScore.setTotalScore(new BigDecimal(0));
                seminarScoreList.add(seminarScore);
            }

            //如果这个队伍还没有RoundScore，新建一个
            if (roundScoreMapper.selectRoundScoreByRoundIdAndTeamId(
                    klassSeminar.getSeminar().getRound().getId(), item.getId()) == null) {
                RoundScore roundScore = new RoundScore();
                roundScore.setTeam(item);
                roundScore.setRound(klassSeminar.getSeminar().getRound());
                roundScore.setReportScore(new BigDecimal(0));
                roundScore.setPresentationScore(new BigDecimal(0));
                roundScore.setQuestionScore(new BigDecimal(0));
                roundScore.setTotalScore(new BigDecimal(0));
                insertRoundScore(roundScore);
            }
        }
        if (!seminarScoreList.isEmpty()) {
            insertSeminarScoreList(seminarScoreList);
        }
    }

    /**
     * 在班级讨论课结束的时候为每个参与的小组更新提问分
     *
     * @param klassSeminarId 班级讨论课id
     */
    public void updateScoreAfterKlassSeminar(Long klassSeminarId) {

        //获得班级讨论课
        KlassSeminar klassSeminar =
                klassSeminarDao.selectKlassSeminarById(klassSeminarId);

        //获得轮规则
        Round round;
        if (klassSeminar != null) {
            round = klassSeminar.getSeminar().getRound();
        } else {
            throw new RecordNotFoundException("未找到该班级的讨论课");
        }

        //为每个小组计算提问分
        for (Team item : teamDao.listTeamByKlassId(klassSeminar.getKlass().getId())) {
            //存放提问分
            List<BigDecimal> questionScoreList = new ArrayList<>();
            for (Question question : questionDao.listQuestionByKlassSeminarIdAndTeamId(klassSeminarId, item.getId())) {
                if (question.getScore() != null) {
                    questionScoreList.add(question.getScore());
                }
            }
            SeminarScore seminarScore = seminarScoreMapper.selectSeminarScoreByKlassSeminarIdAndTeamId(
                    klassSeminarId, item.getId()
            );
            //计算提问分
            seminarScore.setQuestionScore(
                    round.getQuestionScoreMethod().equals(1) ?
                            averageScore(questionScoreList, questionScoreList.size()) : maxScore(questionScoreList)
            );
            //更新并计算
            updateSeminarScoreAfterKlass(seminarScore);
        }

    }


    public Integer deleteSeminarScoreByKlassSeminarId(Long klassSeminarId) {

        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarById(klassSeminarId);

        //更新roundScore
        if (klassSeminar != null) {
            for(SeminarScore seminarScore :listSeminarScoreByKlassSeminarId(klassSeminarId)){
                seminarScore.setQuestionScore(new BigDecimal(0));
                seminarScore.setPresentationScore(new BigDecimal(0));
                seminarScore.setReportScore(new BigDecimal(0));
                seminarScore.setTotalScore(new BigDecimal(0));
                updateSeminarScoreAfterKlass(seminarScore);
            }
            return seminarScoreMapper.deleteSeminarScoreByKlassSeminarId(klassSeminarId);
        } else {
            return null;
        }
    }


    /**
     * 讨论课结束之后更新分数
     * 需要重新计算
     * 未开始0，正在进行1，已结束2，暂停3
     *
     * @param seminarScore 讨论课分数
     * @return 1成功 0失败
     */
    public Integer updateSeminarScoreAfterKlass(SeminarScore seminarScore) {

        //更新并重新计算成绩
        Course course = courseDao.getCourse(seminarScore.getKlassSeminar().getKlass().getCourse().getId());
        seminarScoreMapper.updateSeminarScore(seminarScore);
        seminarScore = seminarScoreMapper.selectSeminarScoreByKlassSeminarIdAndTeamId(
                seminarScore.getKlassSeminar().getId(), seminarScore.getTeam().getId()
        );

        //按照比例更改总分
        seminarScore.setTotalScore(
                calculateTotalScore(seminarScore.getPresentationScore(), seminarScore.getQuestionScore(),
                        seminarScore.getReportScore(), course));

        seminarScoreMapper.updateSeminarScore(seminarScore);

        //更改RoundScore
        Round round = seminarScore.getKlassSeminar().getSeminar().getRound();
        List<KlassSeminar> klassSeminarList = new ArrayList<>();

        //获得班级
        Klass klass = seminarScore.getKlassSeminar().getKlass();

        //找到该轮的班级讨论课
        for (Seminar seminar : seminarDao.listSeminarByRoundId(round.getId())) {
            klassSeminarList.add(
                    klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminar.getId(), klass.getId())
            );
        }

        //根据班级讨论课和小组找到该轮该小组分数
        List<SeminarScore> seminarScoreList = new ArrayList<>();
        for (KlassSeminar item : klassSeminarList) {
            seminarScoreList.add(
                    selectSeminarScoreByKlassSeminarIdAndTeamId(item.getId(), seminarScore.getTeam().getId())
            );
        }

        //计算RoundScore
        RoundScore roundScore = calculateRoundScore(seminarScoreList, round, klass);
        roundScore.setTotalScore(
                calculateTotalScore(roundScore.getPresentationScore(), roundScore.getQuestionScore(),
                        roundScore.getReportScore(), course)
        );
        roundScore.setRound(round);
        roundScore.setTeam(seminarScore.getTeam());
        roundScoreMapper.updateRoundScore(roundScore);
        return 1;

    }

    /**
     * 讨论课进行时打分，不需要计算总分
     * @param seminarScore 记录
     * @return 1成功 0失败
     */
    public Integer updateSeminarScoreWhenAttendance(SeminarScore seminarScore) {
        return seminarScoreMapper.updateSeminarScore(seminarScore);
    }

    public Integer insertSeminarScoreList(List<SeminarScore> seminarScoreList) {
        return seminarScoreMapper.insertSeminarScoreList(seminarScoreList);
    }

    public Integer insertRoundScore(RoundScore roundScore) {
        return roundScoreMapper.insertRoundScore(roundScore);
    }

    public List<SeminarScore> listSeminarScoreByKlassSeminarIdListAndTeamId(List<Long> klassSeminarIdList, Long teamId) {
        return seminarScoreMapper.listSeminarScoreByKlassSeminarIdListAndTeamId(klassSeminarIdList, teamId);
    }

    public List<RoundScore> listRoundScoreByRoundIdAndTeamIdList(List<Long>teamIds,Long roundId) {
        return roundScoreMapper.listRoundScoreByRoundIdAndTeamIdList(teamIds,roundId);
    }

    public List<RoundScore> listRoundScoreByRoundIdListAndTeamId(List<Long> roundIdList, Long teamId) {
        return roundScoreMapper.listRoundScoreByRoundIdListAndTeamId(roundIdList, teamId);
    }

    public List<SeminarScore> listSeminarScoreByKlassSeminarId(Long klassSeminarId) {
        return seminarScoreMapper.listSeminarScoreByKlassSeminarId(klassSeminarId);
    }

    public SeminarScore selectSeminarScoreByKlassSeminarIdAndTeamId(Long klassSeminarId, Long teamId) {
        return seminarScoreMapper.selectSeminarScoreByKlassSeminarIdAndTeamId(klassSeminarId, teamId);
    }

    public RoundScore selectRoundScoreByRoundIdAndTeamId(Long roundId,Long teamId){
        return  roundScoreMapper.selectRoundScoreByRoundIdAndTeamId(roundId,teamId);
    }
}
