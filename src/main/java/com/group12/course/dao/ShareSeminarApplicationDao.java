package com.group12.course.dao;

import com.group12.course.entity.application.ShareSeminarApplication;
import com.group12.course.mapper.ShareSeminarApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShareSeminarApplicationDao {

    @Autowired
    ShareSeminarApplicationMapper shareSeminarApplicationMapper;

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

    public int deleteShareSeminarApplication(Long id){
        return shareSeminarApplicationMapper.deleteShareSeminarApplication(id);
    }

    public int deleteShareSeminarApplicationByMainCourseId(Long mainCourseId){
        return shareSeminarApplicationMapper.deleteShareSeminarApplicationByMainCourseId(mainCourseId);
    }
}
