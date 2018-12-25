package com.group12.course.dao;


import com.group12.course.entity.*;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamDaoTest {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private KlassMapper klassMapper;

    @Test
    @Rollback
    public void testAddTeam(){
        Team testTeam = new Team();
        Student leader = new Student();
        leader.setId(14868L);
        testTeam.setLeader(leader);
        Course course = new Course();
        course.setId(1L);
        testTeam.setCourse(course);
        Klass klass = new Klass();
        klass.setId(1L);
        testTeam.setKlass(klass);
        testTeam.setTeamName("hahaha");
        testTeam.setStatus(0);
        System.out.println(teamDao.checkTeamValid(testTeam));
        System.out.println(teamDao.checkLeaderInTeam(testTeam,course));
        System.out.println(teamDao.checkMembersInTeam(testTeam));
        testTeam=teamDao.addTeam(testTeam);
        int i = teamDao.getKlassLastTeamSerial(klass.getId());
        System.out.println(i);
        System.out.println(testTeam);
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
