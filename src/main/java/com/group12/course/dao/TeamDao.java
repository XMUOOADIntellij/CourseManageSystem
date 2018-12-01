package com.group12.course.dao;

import com.group12.course.entity.Team;

import java.util.List;

/**
 * @author Tan Xue
 * 2018年12月1日
 */
public interface TeamDao {

    /**
     * 获得所有小组
     * @return Team
     */
    List<Team> listTeams();

    /**
     * 根据班级id获得小组
     */
   List<Team> getTeamsByClassId(int id);

    /**
     * 根据组号id获得小组
     */
    Team getTeamByTeamId(int id);

   /**
     * 增加小组
     */
    boolean add(Team team);

    /**
     * 根据组号id删除小组
     */
    boolean remove(int id);

    /**
     * 更新小组
     */
    boolean update(Team team);

}
