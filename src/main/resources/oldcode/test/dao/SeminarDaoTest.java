package com.group12.course.dao;

import com.group12.course.entity.Seminar;
import com.group12.course.mapper.SeminarMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class SeminarDaoTest {

    @Autowired
    SeminarMapper seminarMapper;
    private Seminar seminar;

    @Before
    public void initialTestSeminar(){
        seminar = new Seminar();
        //seminar.setId(new Long(1));
        seminar.setTheme("需求分析");
        seminar.setContent("讨论课需求分析的内容");
        seminar.setSerial(1);
        seminar.setMaxteam(5);
        seminar.setIsordered(false);
        seminar.setIsVisible(false);
        seminar.setEnrollStartDate(LocalDateTime.of(2018, Month.DECEMBER,1,23,59,59));
        seminar.setEnrollEndDate(LocalDateTime.of(2018,Month.DECEMBER,2,23,59,59));
    }
    @Test
    @Rollback
    public void testInsert(){
        seminarMapper.deleteByPrimaryKey(seminar.getId());
        Assert.assertEquals("Insert Error",1,seminarMapper.insert(seminar));
    }

    @Test
    @Rollback
    public void testInsertSelective(){
        seminar.setTheme(null);
        seminar.setContent(null);

        seminarMapper.deleteByPrimaryKey(seminar.getId());
        Assert.assertEquals("Seminar InsertSelective Error",1,seminarMapper.insertSelective(seminar));
    }

    @Test
    @Rollback
    public void testDeleteByPrimaryKey(){
        seminarMapper.insert(seminar);
        Assert.assertEquals("Seminar Delete Error",1,seminarMapper.deleteByPrimaryKey(seminar.getId()));
        // 再次删除找不到，返回0
        Assert.assertEquals("Seminar Delete Error",0,seminarMapper.deleteByPrimaryKey(seminar.getId()));
    }

    @Test
    @Rollback
    public void testUpdateByPrimaryKey(){
        seminarMapper.deleteByPrimaryKey(seminar.getId());
        seminarMapper.insert(seminar);

        seminar.setSerial(99);
        seminar.setContent(null);
        seminar.setTheme(null);
        Assert.assertEquals(1,seminarMapper.updateByPrimaryKey(seminar));
        Assert.assertEquals(99,seminarMapper.selectByPrimaryKey(seminar.getId()).getSerial().longValue());

        Assert.assertEquals(null,seminarMapper.selectByPrimaryKey(seminar.getId()).getContent());
    }

    @Test
    public void testUpdateByPrimaryKeySelective(){
        seminarMapper.deleteByPrimaryKey(seminar.getId());
        seminarMapper.insert(seminar);

        seminar.setSerial(99);
        seminar.setContent(null);
        seminar.setTheme(null);

        Assert.assertEquals(1,seminarMapper.updateByPrimaryKeySelective(seminar));
        Assert.assertEquals(99,seminarMapper.selectByPrimaryKey(seminar.getId()).getSerial().longValue());

        // Selective 不更新空值
        Assert.assertEquals("讨论课需求分析的内容",seminarMapper.selectByPrimaryKey(seminar.getId()).getContent());
    }

    @Test
    public void testSelectByPrimaryKey(){
        seminarMapper.deleteByPrimaryKey(seminar.getId());
        seminarMapper.insert(seminar);
        Assert.assertEquals("讨论课需求分析的内容",seminarMapper.selectByPrimaryKey(seminar.getId()).getContent());

    }

}
