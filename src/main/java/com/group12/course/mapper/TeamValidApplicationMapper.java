package com.group12.course.mapper;

import com.group12.course.entity.application.TeamValidApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TeamValidApplication Mapper 层接口
 * @author Xu Gang
 * @date 2018年12月17日
 */
@Mapper
@Component
public interface TeamValidApplicationMapper {

    TeamValidApplication selectTeamValidApplicationById(Long id);

    List<TeamValidApplication> selectTeamValidApplicationByTeacherId(Long id);

    int addTeamValidApplication(TeamValidApplication record);

    int updateTeamValidApplicationStatus(TeamValidApplication record);

}
