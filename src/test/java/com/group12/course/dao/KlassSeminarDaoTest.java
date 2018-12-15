package com.group12.course.dao;


import com.group12.course.entity.KlassSeminar;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class KlassSeminarDaoTest {
    //KlassSeminarMapper klassSeminarMapper;
    @Autowired
    KlassSeminarDao klassSeminarDao;

    @Test
    public void testSelectKlassSeminarById(){
        KlassSeminar record = klassSeminarDao.getKlassSeminarById(new Long(1));
    }

    @Test
    public void testSelectKlassSeminar(){
        KlassSeminar klassSeminar = klassSeminarDao.getKlassSeminar(new Long(4),new Long(3));
        Assert.assertNotNull(klassSeminar);
        Assert.assertNotNull(klassSeminar.getKlass().getGrade());
    }

}
