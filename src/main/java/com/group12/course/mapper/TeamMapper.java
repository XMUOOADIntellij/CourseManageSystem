package com.group12.course.mapper;

import com.group12.course.entity.Student;
import com.group12.course.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Team Mapper 层接口
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Mapper
@Component
public interface TeamMapper {

    /**
     * 删除数据库中队伍的记录
     *
     * @param id 主键代表用户账号
     * @return 代表处理数量
     * */
    int deleteTeam(Long id);

    /**
     * 删除数据库中队伍的记录
     *
     * @param id 主键代表用户账号
     * @return 代表处理数量
     * */
    int deleteTeamMembers(Long id);

    /**
     * 在数据库中添加队伍的记录
     *
     * @param record 待添加的队伍
     * @param courseId 所在课程的 id
     * @param klassId 所在的班级的 id
     * @param leaderId 队长 id
     * @return 代表处理数量
     * */
    int addTeam(Team record,Long courseId,Long klassId,Long leaderId);

    /**
     * 在数据库中添加队员的记录
     *
     * @param teamId 待添加的队员
     * @param courseId 所在课程的 id
     * @param klassId 所在的班级的 id
     * @param studentId  队员 id
     * @return 代表处理数量
     * */
    int addTeamMembers(Long teamId,Long courseId,Long klassId,Long studentId);

    /**
     * 获取数据库中队伍的记录
     *
     * @param id 主键代表队伍id
     * @return 代表获取到的队伍对象
     * */
    Team selectTeamById(Long id);

    /**
     * 根据队长 id 获取数据库中队伍的记录
     *
     * @param id 主键代表队长id
     * @return 代表获取到的队伍对象
     * */
    List<Team> selectTeamByLeaderId(Long id);

    /**
     * 根据队长 id 获取数据库中队伍的记录
     *
     * @param id 主键代表队长id
     * @return 代表获取到的队伍对象
     * */
    List<Team> selectTeamByKlassId(Long id);

    /**
     * 根据队伍 id 获取数据库中队伍的记录
     *
     * @param id 主键代表队伍id
     * @return 代表获取到的队伍对象
     * */
    List<Student> selectTeamMembersByTeamId(Long id);

    /**
     * 根据队员 id 获取数据库中队伍的 id
     *
     * @param id 主键代表队员id
     * @return 代表获取到的队伍 id
     * */
    List<Long> selectTeamIdByMembersId(Long id);

    /**
     * 更新数据库中学生的记录
     *
     * @param record 主键代表新的队伍对象
     * @return 代表处理数量
     * */
    int updateTeam(Team record);

}