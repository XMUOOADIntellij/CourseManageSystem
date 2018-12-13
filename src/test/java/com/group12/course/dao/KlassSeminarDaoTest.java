package com.group12.course.dao;

import com.group12.course.entity.KlassSeminar;
import com.group12.course.mapper.KlassSeminarMapper;
import org.apache.ibatis.session.SqlSession;
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
public class KlassSeminarDaoTest {
    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    @Test
    public void testSelectKlassSeminar(){
        KlassSeminar record = klassSeminarMapper.selectKlassSeminarById(new Long(1));
        Assert.assertNotNull(record.getSeminar());
    }

}
