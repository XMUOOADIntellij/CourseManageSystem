package com.group12.course.dao;


import com.group12.course.entity.KlassSeminar;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class KlassSeminarDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    //KlassSeminarMapper klassSeminarMapper;
    @Autowired
    KlassSeminarDao klassSeminarDao;

    @Test
    public void testSelectKlassSeminarById(){
        KlassSeminar record = klassSeminarDao.getKlassSeminarById(new Long(1));
    }

    @Test
    public void testSelectKlassSeminarBySeminarId(){
        List<KlassSeminar> record = klassSeminarDao.getKlassSeminarBySeminarId(new Long(4));
        Assert.assertEquals(2,record.size());

    }

    @Test
    public void testSelectKlassSeminar(){
        KlassSeminar klassSeminar = klassSeminarDao.getKlassSeminarBySeminarIdAndClassId(new Long(4),new Long(3));
        Assert.assertNotNull(klassSeminar);
        Assert.assertNotNull(klassSeminar.getKlass().getGrade());
    }

    @Test
    public void testDeleteBySeminarId(){
        Assert.assertEquals(2,klassSeminarDao.deleteKlassSeminarBySeminarId(new Long(4)).intValue());
    }

    @Test
    public void testUpdateKlassSeminar(){
        KlassSeminar klassSeminar = new KlassSeminar();
        klassSeminar.setId(new Long(1));
        klassSeminar.setReportDdl(LocalDateTime.now());
        klassSeminarDao.updateKlassSeminar(klassSeminar);
    }

    @Test
    public void testSelectKlassSeminarBySeminarIdList(){
        List<Long> seminarId = new ArrayList<>();
        seminarId.add(new Long(1));
        seminarId.add(new Long(2));
        Assert.assertNotNull(klassSeminarDao.getKlassSeminarBySeminarIdList(seminarId));
    }

}
