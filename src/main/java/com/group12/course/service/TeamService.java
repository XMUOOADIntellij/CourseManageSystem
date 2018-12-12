package com.group12.course.service;

import com.group12.course.dao.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Team service 层对应接口的实现
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Service
public class TeamService {

    @Autowired
    TeamDao teamDao;

}
