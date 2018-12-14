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

    int delete(Long id);

    int insert(Attendance record);

    int update(Attendance record);

    Attendance selectAttendenceById(Long id);

    Attendance selectAttendance(Long klassSeminarId,Long teamId);

    List<Attendance> selectAllAttendance(Long klassSeminarId);

    Integer updateOrder(Long id,Integer teamOrder);
}