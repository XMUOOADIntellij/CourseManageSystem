package com.group12.course.mapper;

import com.group12.course.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 报名的展示的Mapper
 * @author Y Jiang
 * @date 2018/12/12
 */
@Mapper
@Component
public interface AttendanceMapper {

    /**
     * 通过id删除展示报名
     * @param id id
     * @return 1成功 0返回
     */
    Integer deleteAttendanceById(Long id);

    /**
     * 删除班级讨论课下所有展示报名
     * @param klassSeminarId 课程讨论课id
     * @return 删除的条数
     */
    Integer deleteAttendanceByKlassSeminarId(Long klassSeminarId);

    /**
     * 插入一条展示报名记录
     * @param record 记录
     * @return 展示报名的id
     */
    Integer insertAttendance(Attendance record);

    /**
     * 更新展示报名记录
     * @param record 记录
     * @return 1成功 0失败
     */
    Integer updateAttendance(Attendance record);

    /**
     * 通过id查找展示报名
     * @param id 展示报名id
     * @return 展示报名记录
     */
    Attendance selectAttendanceById(Long id);

    /**
     * 查找班级讨论课中某个队伍的展示报名
     * @param klassSeminarId 班级讨论课id
     * @param teamId 队伍id
     * @return 展示报名记录
     */
    Attendance selectAttendanceByKlassSeminarIdAndTeamId(Long klassSeminarId,Long teamId);

    /**
     * 查找班级讨论课中 某顺序的展示报名
     * @param klassSeminarId 班级讨论课id
     * @param teamOrder 队伍所在次序
     * @return 展示报名记录
     */
    Attendance selectAttendanceByKlassSeminarIdAndTeamOrder(Long klassSeminarId,Integer teamOrder);

    /**
     * 查找当前班级讨论课正在展示的小组
     * @param klassSeminarId 班级讨论课
     * @return 展示报名记录
     */
    Attendance selectPresentedAttendanceByKlassSeminarId(Long klassSeminarId);

    /**
     * 查询当前班级讨论课下所有展示报名
     * @param klassSeminarId 班级讨论课id
     * @return 展示报名列表
     */
    List<Attendance> listAttendanceByKlassSeminarId(Long klassSeminarId);

}