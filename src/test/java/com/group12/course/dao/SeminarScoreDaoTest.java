package com.group12.course.dao;

import com.group12.course.entity.*;
import com.group12.course.mapper.SeminarScoreMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarScoreDaoTest {
    @Autowired
    SeminarScoreDao seminarScoreDao;
    @Autowired
    SeminarScoreMapper seminarScoreMapper;

    @Test
    public void testSelectSeminarScoreById(){
        Assert.assertNotNull(seminarScoreDao.selectSeminarScoreByKlassSeminarIdAndTeamId(new Long(1),new Long(1)));
    }

    @Test
    public void testModifyScore(){
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

        seminarScore.setQuestionScore(new BigDecimal(1));
        seminarScore.setPresentationScore(new BigDecimal(1));
        seminarScore.setReportScore(new BigDecimal(1));

        //seminarScoreMapper.updateSeminarScore(seminarScore);
        Assert.assertNotNull(seminarScoreDao.updateSeminarScore(seminarScore));
    }
}
