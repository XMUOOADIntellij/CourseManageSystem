package com.group12.course.service;

import com.group12.course.dao.CourseDao;
import com.group12.course.dao.TeamValidApplicationDao;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.application.TeamValidApplication;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组队审核申请 TeamValidApplication service 层
 *
 * @author Xu Gang
 * @date 2018年12月26日
 */
@Service
public class TeamValidApplicationService {

    @Autowired
    TeamValidApplicationDao teamValidApplicationDao;

    @Autowired
    CourseDao courseDao;

    public TeamValidApplication getTeamValidApplicationMapperById(TeamValidApplication teamValidApplication) {
        return teamValidApplicationDao.getTeamValidApplicationById(teamValidApplication);
    }

    public List<TeamValidApplication> getTeamValidApplicationListByTeacher(Teacher teacher){
        return teamValidApplicationDao.getTeamValidApplicationListByTeacher(teacher);
    }

    public int changeApplicationStatus(TeamValidApplication teamValidApplication){
        return teamValidApplicationDao.changeApplicationStatus(teamValidApplication);
    }

    public int addApplication(TeamValidApplication teamValidApplication,Long courseId){
        Teacher teacher = courseDao.getCourse(courseId).getTeacher();
        teamValidApplication.setTeacher(teacher);
        return teamValidApplicationDao.addApplication(teamValidApplication);
    }
}
