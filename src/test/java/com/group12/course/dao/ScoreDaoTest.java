package com.group12.course.dao;


import com.group12.course.entity.*;
import com.group12.course.mapper.RoundScoreMapper;
import com.group12.course.mapper.SeminarMapper;
import com.group12.course.mapper.SeminarScoreMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreDaoTest {
    @Autowired
    SeminarScoreMapper seminarScoreMapper;
    @Autowired
    RoundScoreMapper roundScoreMapper;
    @Autowired
    ScoreDao scoreDao;

    @Test
    public void testSelectSeminarScore(){
        //SeminarScore record = seminarScoreMapper.selectSeminarScoreById(new Long(1));
    }

    @Test
    public void testUpdateRoundSeminar(){
        RoundScore roundScore = new RoundScore();
        roundScore.setTeam(new Team());
        roundScore.getTeam().setId(new Long(1));
        roundScore.setRound(new Round());
        roundScore.getRound().setId(new Long(1));
        roundScore.setQuestionScore(new BigDecimal(4));
        roundScore.setReportScore(new BigDecimal(4.5));

        roundScoreMapper.updateRoundScore(roundScore);
    }

    @Test
    public void testSelectSeminarScoreById(){
        Assert.assertNotNull(scoreDao.selectSeminarScoreByKlassSeminarIdAndTeamId(new Long(1),new Long(1)));
    }

    @Test
    public void testModifyScore() {
        SeminarScore seminarScore = new SeminarScore();
        seminarScore.setTeam(new Team());
        seminarScore.getTeam().setId(new Long(1));

        seminarScore.setKlassSeminar(new KlassSeminar());
        seminarScore.getKlassSeminar().setId(new Long(1));
        seminarScore.getTeam().setId(new Long(1));

        seminarScore.getKlassSeminar().setKlass(new Klass());
        seminarScore.getKlassSeminar().getKlass().setId(new Long(3));
        seminarScore.getKlassSeminar().getKlass().setCourse(new Course());

        seminarScore.getKlassSeminar().getKlass().getCourse().setId(new Long(1));

        seminarScore.setQuestionScore(new BigDecimal(4));
        seminarScore.setPresentationScore(new BigDecimal(4));
        seminarScore.setReportScore(new BigDecimal(4));


        //seminarScoreMapper.updateSeminarScore(seminarScore);
        Assert.assertNull(scoreDao.updateSeminarScore(seminarScore));
    }
}
