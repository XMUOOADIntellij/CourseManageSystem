package com.group12.course.mapper;

import com.group12.course.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 * 2018年12月1日
 */
@Mapper
@Component
public interface TeamMapper {

    /**
     * 获得所有小组
     */
    List<Team> getAllTeams();

    /**
     * 根据班级id获得小组
     */
    List<Team> getTeamsByClassId(@Param("id")int id);

    /**
     * 根据组号id获得小组
     */
    Team getTeamByTeamId(@Param("id")int id);

    /**
     * 增加小组
     */
    boolean add(Team team);

    /**
     * 根据组号id删除小组
     */
    boolean remove(@Param("id")int id);

    /**
     * 更新小组
     */
    boolean update(Team team);

}
