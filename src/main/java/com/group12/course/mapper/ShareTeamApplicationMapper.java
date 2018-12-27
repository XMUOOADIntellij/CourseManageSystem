package com.group12.course.mapper;

import com.group12.course.entity.application.ShareTeamApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShareTeamApplicationMapper {

    ShareTeamApplication selectShareTeamApplicationById(Long id);

    List<ShareTeamApplication> selectShareTeamApplicationByCourseId(Long courseId);

    int addShareTeamApplication(ShareTeamApplication shareTeamApplication);

    int updateShareTeamApplication(ShareTeamApplication shareTeamApplication);

    int deleteShareTeamApplication(Long id);

    int deleteShareTeamApplicationByMainCourseId(Long mainCourseId);
}
