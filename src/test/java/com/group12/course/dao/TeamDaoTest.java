package com.group12.course.dao;


import com.group12.course.entity.Admin;
import com.group12.course.entity.Klass;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.Team;
import com.group12.course.mapper.AdminMapper;
import com.group12.course.mapper.KlassMapper;
import com.group12.course.mapper.TeacherMapper;
import com.group12.course.mapper.TeamMapper;
import com.group12.course.tools.DBUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamDaoTest {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Test
    @Rollback
    public void testAddTeam(){
        /*Team testTeam = new Team();
        //testTeam.setCourseId(new Long(111));
        Jedis jedis = new Jedis("47.107.81.51");
        jedis.auth("123456");
        System.out.println("success");
        System.out.println("at "+jedis.ping());
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }*/
        Klass klass = klassMapper.selectKlassById(1L);
        System.out.println(klass);
        /*Team testTeam = new Team();
        *//*testTeam.setCourseId(new Long(111));
        testTeam.setGmtCreate(new Date());
        testTeam.setGmtModified(new Date());
        //testTeam.setLeaderId(new Long(122));
        testTeam.setLeaderId(new Long(122));*//*
        testTeam.setValid(true);
        testTeam.setLabel("aaa");
        testTeam.setName("asa");
        Assert.assertEquals("add Error",1,teamMapper.addTeam(testTeam));*/
    }

    @Test
    @Rollback
    public void testRemoveTeammate(){

    }

    @Test
    public void test1() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
//            AdminMapper userMapper = sqlSession.getMapper(AdminMapper.class);
//            Admin user = userMapper.getAdminByAccount("111");
            TeacherMapper teacherMapper =sqlSession.getMapper(TeacherMapper.class);

            List<Teacher> list = teacherMapper.getAllTeacher();
            System.out.println(list);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
}
