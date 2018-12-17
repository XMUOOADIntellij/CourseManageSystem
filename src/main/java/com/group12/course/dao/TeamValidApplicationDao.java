package com.group12.course.dao;

import com.group12.course.entity.application.TeamValidApplication;
import com.group12.course.mapper.TeamValidApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamValidApplicationDao {

    @Autowired
    TeamValidApplicationMapper teamValidApplicationMapper;

    public TeamValidApplication getTeamValidApplicationMapperById(Long id){
        return teamValidApplicationMapper.selectTeamValidApplicationById(id);
    }
}
