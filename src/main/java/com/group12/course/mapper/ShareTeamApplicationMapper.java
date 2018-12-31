package com.group12.course.mapper;

import com.group12.course.entity.application.ShareTeamApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 */
@Mapper
@Component
public interface ShareTeamApplicationMapper {

    /**
     *  查询
     * @param id
     * @return
     */
    ShareTeamApplication selectShareTeamApplicationById(Long id);

    /**
     *  根据主课程id查询
     * @param mainCourseId
     * @return
     */
    List<ShareTeamApplication> selectShareTeamApplicationByMainCourseId(Long mainCourseId);


    /**
     * 根据从课程教师id查询
     * @param subCourseTeacherId
     * @return
     */
    List<ShareTeamApplication> selectShareTeamApplicationBySubCourseTeacherId(Long subCourseTeacherId);


    /**
     * 添加
     * @param shareTeamApplication
     * @return
     */
    int addShareTeamApplication(ShareTeamApplication shareTeamApplication);

    /**
     * 修改
     * @param shareTeamApplication
     * @return
     */
    int updateShareTeamApplication(ShareTeamApplication shareTeamApplication);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteShareTeamApplication(Long id);

    /**
     * 删除
     * @param mainCourseId
     * @return
     */
    int deleteShareTeamApplicationByMainCourseId(Long mainCourseId);
}
