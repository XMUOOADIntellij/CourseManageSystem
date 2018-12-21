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

    Integer deleteAttendanceById(Long id);

    Integer deleteAttendanceByKlassSeminarId(Long klassSeminarId);

    Integer insertAttendance(Attendance record);

    Integer updateAttendance(Attendance record);

    Attendance selectAttendanceById(Long id);

    Attendance selectAttendanceByKlassSeminarIdAndTeamId(Long klassSeminarId,Long teamId);

    Attendance selectPresentedAttendanceByKlassSeminarId(Long klassSeminarId);

    List<Attendance> listAttendanceByKlassSeminarId(Long klassSeminarId);



}