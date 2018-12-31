package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.application.ShareTeamApplication;
import com.group12.course.mapper.ShareTeamApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tan Xue
 */
@Component
public class ShareTeamApplicationDao {

    @Autowired
    ShareTeamApplicationMapper shareTeamApplicationMapper;
    @Autowired
    CourseDao courseDao;

    public ShareTeamApplication selectShareTeamApplicationById(Long id){
        return shareTeamApplicationMapper.selectShareTeamApplicationById(id);
    }

    public List<ShareTeamApplication> selectShareTeamApplicationByTeacherId(Long teacherId){
        List<ShareTeamApplication> shareTeamApplicationList = new ArrayList<>();
        //为主课程时
        List<Course> courseList = courseDao.getCourseByTeacherId(teacherId);
        for (Course course:courseList) {
            List<ShareTeamApplication> shareTeamApplicationList1 = shareTeamApplicationMapper.selectShareTeamApplicationByMainCourseId(course.getId());
            if(!shareTeamApplicationList1.isEmpty()&&shareTeamApplicationList1!=null){
                shareTeamApplicationList.addAll(shareTeamApplicationList1);
            }
        }
        //为从课程时
        List<ShareTeamApplication> shareTeamApplicationList2 = shareTeamApplicationMapper.selectShareTeamApplicationBySubCourseTeacherId(teacherId);
        if(shareTeamApplicationList2!=null){
            shareTeamApplicationList.addAll(shareTeamApplicationList2);
        }
        return shareTeamApplicationList;
    }

    public int addShareTeamApplication(ShareTeamApplication shareTeamApplication){
        return shareTeamApplicationMapper.addShareTeamApplication(shareTeamApplication);
    }

    public int updateShareTeamApplication(ShareTeamApplication shareTeamApplication){
        return shareTeamApplicationMapper.updateShareTeamApplication(shareTeamApplication);
    }

    /**
     * 取消分组共享，并修改从课程中的记录
     * @param id
     * @return
     */
    public int deleteShareTeamApplication(Long id){
        Course subCourse = selectShareTeamApplicationById(id).getSubCourse();
        subCourse.setTeamMainCourse(null);
        courseDao.updateCourse(subCourse);
        return shareTeamApplicationMapper.deleteShareTeamApplication(id);
    }

    public int deleteShareTeamApplicationByMainCourseId(Long mainCourseId){
        return shareTeamApplicationMapper.deleteShareTeamApplicationByMainCourseId(mainCourseId);
    }
}
