package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.application.ShareSeminarApplication;
import com.group12.course.mapper.ShareSeminarApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tan Xue
 */
@Component
public class ShareSeminarApplicationDao {

    @Autowired
    ShareSeminarApplicationMapper shareSeminarApplicationMapper;
    @Autowired
    CourseDao courseDao;

    public ShareSeminarApplication selectShareSeminarApplicationById(Long id){
        return shareSeminarApplicationMapper.selectShareSeminarApplicationById(id);
    }

    public List<ShareSeminarApplication> selectShareSeminarApplicationByTeacherId(Long teacherId){
        List<ShareSeminarApplication> shareSeminarApplicationList = new ArrayList<>();
        List<Course> courseList = courseDao.getCourseByTeacherId(teacherId);
        //为主课程时
        for (Course course:courseList) {
            List<ShareSeminarApplication> shareSeminarApplicationList1 = shareSeminarApplicationMapper.selectShareSeminarApplicationByMainCourseId(course.getId());
            if(shareSeminarApplicationList1!=null&&!shareSeminarApplicationList1.isEmpty()){
                shareSeminarApplicationList.addAll(shareSeminarApplicationList1);
            }
        }
        //为从课程时
        List<ShareSeminarApplication> shareSeminarApplicationList2 = shareSeminarApplicationMapper.selectShareSeminarApplicationBySubCourseTeacherId(teacherId);
        if(shareSeminarApplicationList2!=null){
            shareSeminarApplicationList.addAll(shareSeminarApplicationList2);
        }
        return shareSeminarApplicationList;
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
