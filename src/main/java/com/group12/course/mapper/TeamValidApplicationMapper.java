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

    /**
     * 通过 id 获取组队审核申请
     *
     * @param id 申请 id
     * @return 组队申请
     */
    TeamValidApplication selectTeamValidApplicationById(Long id);

    /**
     * 通过教师 id 获取该教师名下的所有组队审核申请
     *
     * @param id 教师 id
     * @return 申请的列表
     */
    List<TeamValidApplication> selectTeamValidApplicationByTeacherId(Long id);

    /**
     * 添加一条新的组队申请
     *
     * @param record 新的组队申请
     * @return 添加数量
     */
    int addTeamValidApplication(TeamValidApplication record);

    /**
     * 更改申请状态
     *
     * @param record 更改后的申请对象
     * @return 修改数量
     */
    int updateTeamValidApplicationStatus(TeamValidApplication record);

}
