package com.group12.course.service;

import com.group12.course.dao.TeamValidApplicationDao;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.application.TeamValidApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamValidApplicationService {

    @Autowired
    TeamValidApplicationDao teamValidApplicationDao;

    public TeamValidApplication getTeamValidApplicationMapperById(TeamValidApplication teamValidApplication) {
        return teamValidApplicationDao.getTeamValidApplicationById(teamValidApplication);
    }

    public List<TeamValidApplication> getTeamValidApplicationListByTeacher(Teacher teacher){
        return teamValidApplicationDao.getTeamValidApplicationListByTeacher(teacher);
    }

    public int changeApplicationStatus(TeamValidApplication teamValidApplication){
        return teamValidApplicationDao.changeApplicationStatus(teamValidApplication);
    }
}
