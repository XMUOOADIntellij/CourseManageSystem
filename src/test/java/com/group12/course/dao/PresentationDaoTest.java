package com.group12.course.dao;

import com.group12.course.entity.Presentation;
import com.group12.course.mapper.PresentationMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PresentationDaoTest {

    @Autowired
    PresentationMapper presentationMapper;
    Presentation presentation;

    @Before
    public void initial(){
        presentation = new Presentation();
    }

    @Test
    public void testEnrollPresentation(){
        presentation.setTeamId(new Long(123));
        presentation.setPresentationOrder(1);
        presentation.setPresented(false);
        Assert.assertEquals(1,presentationMapper.insertSelective(presentation));
        Assert.assertNotNull(presentation.getId());
    }

}
