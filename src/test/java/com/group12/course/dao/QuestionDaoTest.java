package com.group12.course.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuestionDaoTest {
    @Autowired
    QuestionDao questionDao;

    @Test
    public void testGetAllQuestions(){
        Assert.assertNotNull(questionDao.listQuestionByKlassSeminarId(new Long(1)));
    }
}
