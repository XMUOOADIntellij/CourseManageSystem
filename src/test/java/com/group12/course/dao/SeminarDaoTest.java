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
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SeminarDaoTest {
    @Autowired
    SeminarMapper seminarMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    @Test
    public void testInsertSeminar(){
        Seminar seminar =new Seminar();

        seminar.setCourse(new Course());
        seminar.getCourse().setId(16L);

        seminar.setMaxTeam(5);
        seminar.setSeminarName("test");
        seminar.setVisible(false);


        Course course = new Course();
        Course subCourse = new Course();
        course.setId(16L);
        subCourse.setId(17L);
        subCourse.setSeminarMainCourse(course);
        courseMapper.updateCourse(subCourse);

        seminarMapper.insertSeminar(seminar);

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
