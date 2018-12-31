package com.group12.course.mapper;

import com.group12.course.entity.application.ShareSeminarApplication;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tan Xue
 */
@Mapper
@Component
public interface ShareSeminarApplicationMapper {
    /**
     *  选择
     * @param id
     * @return
     */
    ShareSeminarApplication selectShareSeminarApplicationById(Long id);

    /**
     * 根据主课程id查找共享信息
     * @param mainCourseId
     * @return
     */
    List<ShareSeminarApplication> selectShareSeminarApplicationByMainCourseId(Long mainCourseId);

    /**
     * 根据从课程教师id查找共享信息
     * @param subCourseTeacherId
     * @return
     */
    List<ShareSeminarApplication> selectShareSeminarApplicationBySubCourseTeacherId(Long subCourseTeacherId);


    /**
     * 添加
     * @param shareSeminarApplication
     * @return
     */
    int addShareSeminarApplication(ShareSeminarApplication shareSeminarApplication);

    /**
     * 修改
     * @param shareSeminarApplication
     * @return
     */
    int updateSeminarApplication(ShareSeminarApplication shareSeminarApplication);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteShareSeminarApplication(Long id);

    /**
     * 删除
     * @param mainCourseId
     * @return
     */
    int deleteShareSeminarApplicationByMainCourseId(Long mainCourseId);
}
