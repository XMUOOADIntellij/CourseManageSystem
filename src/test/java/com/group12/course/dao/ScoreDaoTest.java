package com.group12.course.dao;


import com.group12.course.entity.SeminarScore;
import com.group12.course.mapper.SeminarMapper;
import com.group12.course.mapper.SeminarScoreMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ScoreDaoTest {
    @Autowired
    SeminarScoreMapper seminarScoreMapper;

    @Test
    public void testSelectSeminarScore(){
        SeminarScore record = seminarScoreMapper.selectSeminarScoreById(new Long(1));
    }

}
