package com.group12.course.dao;


import com.group12.course.entity.Klass;
import com.group12.course.mapper.KlassMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class KlassDaoTest {

    @Autowired
    KlassMapper klassMapper;

    @Test
    public void testGetKlass(){
        Klass record =  klassMapper.selectKlassById(new Long(1));
        System.out.print(record);
        Assert.assertNotNull(record.getCourse());
    }


    @Test
    public void testGetAllKlass(){
        List<Klass> records = klassMapper.getAllKlassByCourseId(new Long(1));
        Assert.assertFalse(records.isEmpty());
    }
}
