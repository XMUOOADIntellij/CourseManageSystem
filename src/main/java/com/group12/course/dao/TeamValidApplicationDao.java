package com.group12.course.dao;

import com.group12.course.entity.Teacher;
import com.group12.course.entity.application.TeamValidApplication;
import com.group12.course.mapper.TeamValidApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 组队审核申请 TeamValidApplication dao 层
 *
 * @author Xu Gang
 * @date 2018年12月26日
 */
@Component
public class TeamValidApplicationDao {

    @Autowired
    TeamValidApplicationMapper teamValidApplicationMapper;

    public TeamValidApplication getTeamValidApplicationById(TeamValidApplication teamValidApplication){
        return teamValidApplicationMapper.selectTeamValidApplicationById(teamValidApplication.getId());
    }

    public List<TeamValidApplication> getTeamValidApplicationListByTeacher(Teacher teacher){
        return teamValidApplicationMapper.selectTeamValidApplicationByTeacherId(teacher.getId());
    }

    public int changeApplicationStatus(TeamValidApplication teamValidApplication){
        return teamValidApplicationMapper.updateTeamValidApplicationStatus(teamValidApplication);
    }

    public int addApplication(TeamValidApplication teamValidApplication){
        return teamValidApplicationMapper.addTeamValidApplication(teamValidApplication);
    }
}
