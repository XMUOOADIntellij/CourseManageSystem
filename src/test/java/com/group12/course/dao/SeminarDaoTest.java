package com.group12.course.dao;


import com.group12.course.entity.Course;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Round;
import com.group12.course.entity.Seminar;
import com.group12.course.mapper.CourseMapper;
import com.group12.course.mapper.KlassSeminarMapper;
import com.group12.course.mapper.SeminarMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeminarDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    SeminarMapper seminarMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    KlassSeminarMapper klassSeminarMapper;
    @Autowired
    SeminarDao seminarDao;

    @Test
    public void testInsertSeminar(){
        Seminar seminar =new Seminar();

        seminar.setCourse(new Course());
        seminar.getCourse().setId(16L);

        seminar.setMaxTeam(5);
        seminar.setSeminarName("test");
        seminar.setVisible(false);
        seminar.setSeminarSerial(5);

        Course course = new Course();
        Course subCourse = new Course();
        course.setId(16L);
        subCourse.setId(17L);
        subCourse.setSeminarMainCourse(course);
        courseMapper.updateCourse(subCourse);

        seminarDao.insertSeminar(seminar);

        Assert.assertNotNull(klassSeminarMapper.selectKlassSeminarBySeminarIdAndKlassId(seminar.getId(),24L));

    }

    @Test
    public void testListSeminarByRoundId(){
        Assert.assertNotNull(seminarMapper.listSeminarByRoundId(new Long(1)));
    }

    @Test
    public void testSelectSeminarById(){
        Seminar seminar = seminarMapper.selectSeminarById(1L);
        System.out.println(seminar);
        Assert.assertEquals("用户故事x11",
                seminarMapper.selectSeminarById(1L).getIntroduction());
        Assert.assertEquals(new Long(3),
                seminarMapper.selectSeminarById(1L).getRound().getId()
        );

    }

}
