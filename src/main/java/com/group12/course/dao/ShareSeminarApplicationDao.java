package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.application.ShareSeminarApplication;
import com.group12.course.mapper.ShareSeminarApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShareSeminarApplicationDao {

    @Autowired
    ShareSeminarApplicationMapper shareSeminarApplicationMapper;
    @Autowired
    CourseDao courseDao;

    public ShareSeminarApplication selectShareSeminarApplicationById(Long id){
        return shareSeminarApplicationMapper.selectShareSeminarApplicationById(id);
    }

    public List<ShareSeminarApplication> selectShareSeminarApplicationByMainCourseId(Long mainCourseId){
        return shareSeminarApplicationMapper.selectShareSeminarApplicationByMainCourseId(mainCourseId);
    }

    public int addShareSeminarApplication(ShareSeminarApplication shareSeminarApplication){
        return shareSeminarApplicationMapper.addShareSeminarApplication(shareSeminarApplication);
    }

    public int updateSeminarApplication(ShareSeminarApplication shareSeminarApplication){
        return shareSeminarApplicationMapper.updateSeminarApplication(shareSeminarApplication);
    }

    /**
     * 取消讨论课共享，并修改从课程的记录
     * @param id
     * @return
     */
    public int deleteShareSeminarApplication(Long id){
        Course subCourse = selectShareSeminarApplicationById(id).getSubCourse();
        subCourse.setSeminarMainCourse(null);
        courseDao.updateCourse(subCourse);
        return shareSeminarApplicationMapper.deleteShareSeminarApplication(id);
    }

    public int deleteShareSeminarApplicationByMainCourseId(Long mainCourseId){
        return shareSeminarApplicationMapper.deleteShareSeminarApplicationByMainCourseId(mainCourseId);
    }
}
