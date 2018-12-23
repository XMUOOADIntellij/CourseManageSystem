package com.group12.course.mapper;

import com.group12.course.entity.application.ShareSeminarApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Component
public interface ShareSeminarApplicationMapper {
    ShareSeminarApplication selectShareSeminarApplicationById(Long id);

    List<ShareSeminarApplication> selectShareSeminarApplicationByMainCourseId(Long mainCourseId);

    int addShareSeminarApplication(ShareSeminarApplication shareSeminarApplication);

    int updateSeminarApplication(ShareSeminarApplication shareSeminarApplication);

    int deleteShareSeminarApplication(Long id);

    int deleteShareSeminarApplicationByMainCourseId(Long mainCourseId);
}
