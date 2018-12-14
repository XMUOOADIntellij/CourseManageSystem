package com.group12.course.dao;

import com.group12.course.dto.KlassSeminarDto;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.mapper.KlassSeminarMapper;
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
    KlassSeminarDao klassSeminarDao;

    @Test
    public void testSelectKlassSeminarById(){
        KlassSeminar record = klassSeminarMapper.selectKlassSeminarById(new Long(1));
        Assert.assertNotNull(record.getSeminar());
    }

    @Test
    public void testSelectKlassSeminar(){
       KlassSeminarDto klassSeminarDto =klassSeminarMapper.selectKlassSeminar(new Long(4),new Long(3));
       Assert.assertEquals(1,klassSeminarDto.getId().intValue());
       Assert.assertEquals("未开始",klassSeminarDto.getSeminarStatus());

        klassSeminarDto =  klassSeminarDao.getKlassSeminar(new Long(4),new Long(3));
       //Assert.assertNotNull(klassSeminarDao.getKlassSeminar(new Long(4),new Long(3)));
       //KlassSeminar klassSeminar = klassSeminarMapper.selectKlassSeminar(new Long(4),new Long(3));
       // Assert.assertNotNull(klassSeminarMapper.selectKlassSeminar(new Long(4),new Long(3)));
       // Assert.assertNotNull(klassSeminar);
       // Assert.assertNotNull(klassSeminarDao.getKlassSeminar(new Long(4),new Long(3)));
        //System.out.println(klassSeminar.toString());
        //klassSeminar = klassSeminarDao.getKlassSeminar(new Long(4),new Long(3));
        //Assert.assertEquals(new Long(1),klassSeminarDao.getKlassSeminar(new Long(4),new Long(3)).getId());
    }

}
