package com.group12.course.mapper;

import com.group12.course.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
     * 在数据库中添加队伍的记录
     *
     * @param record 待添加的队伍
     * @return 代表处理数量
     * */
    int addTeam(Team record);

    /**
     * 获取数据库中队伍的记录
     *
     * @param id 主键代表队伍id
     * @return 代表获取到的队伍对象
     * */
    Team getTeam(Long id);

    /**
     * 更新数据库中学生的记录
     *
     * @param record 主键代表新的队伍对象
     * @return 代表处理数量
     * */
    int updateTeam(Team record);

}