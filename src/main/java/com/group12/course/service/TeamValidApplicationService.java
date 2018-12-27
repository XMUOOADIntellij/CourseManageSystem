package com.group12.course.service;

import com.group12.course.dao.CourseDao;
import com.group12.course.dao.TeamDao;
import com.group12.course.dao.TeamValidApplicationDao;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.Team;
import com.group12.course.entity.application.TeamValidApplication;
import com.group12.course.exception.InformationException;
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

    @Autowired
    TeamDao teamDao;

    private final int teamIsValid = 1;
    private final int teamIsInvalid = 2;
    private final int teamIsInAuditing = 0;

    public TeamValidApplication getTeamValidApplicationMapperById(TeamValidApplication teamValidApplication) {
        return teamValidApplicationDao.getTeamValidApplicationById(teamValidApplication);
    }

    public List<TeamValidApplication> getTeamValidApplicationListByTeacher(Teacher teacher){
        return teamValidApplicationDao.getTeamValidApplicationListByTeacher(teacher);
    }

    public int changeApplicationStatus(TeamValidApplication teamValidApplication){
        Team team = teamValidApplication.getTeam();
        switch (teamValidApplication.getStatus()){
            case 1:team.setStatus(teamIsValid);break;
            case 2:team.setStatus(teamIsValid);break;
            default:throw new InformationException("小组申请更改状态信息有误");
        }
        teamDao.changeTeam(team);
        return teamValidApplicationDao.changeApplicationStatus(teamValidApplication);
    }

    public int addApplication(TeamValidApplication teamValidApplication,Long courseId){
        Teacher teacher = courseDao.getCourse(courseId).getTeacher();
        teamValidApplication.setTeacher(teacher);
        return teamValidApplicationDao.addApplication(teamValidApplication);
    }
}
