package com.group12.course.dao;

import com.group12.course.entity.application.ShareTeamApplication;
import com.group12.course.mapper.ShareTeamApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShareTeamApplicationDao {

    @Autowired
    ShareTeamApplicationMapper shareTeamApplicationMapper;

    public ShareTeamApplication selectShareTeamApplicationById(Long id){
        return shareTeamApplicationMapper.selectShareTeamApplicationById(id);
    }

    public List<ShareTeamApplication> selectShareTeamApplicationByMainCourseId(Long mainCourseId){
        return shareTeamApplicationMapper.selectShareTeamApplicationByMainCourseId(mainCourseId);
    }

    public int addShareTeamApplication(ShareTeamApplication shareTeamApplication){
        return shareTeamApplicationMapper.addShareTeamApplication(shareTeamApplication);
    }

    public int updateShareTeamApplication(ShareTeamApplication shareTeamApplication){
        return shareTeamApplicationMapper.updateShareTeamApplication(shareTeamApplication);
    }

    public int deleteShareTeamApplication(Long id){
        return shareTeamApplicationMapper.deleteShareTeamApplication(id);
    }

    public int deleteShareTeamApplicationByMainCourseId(Long mainCourseId){
        return shareTeamApplicationMapper.deleteShareTeamApplicationByMainCourseId(mainCourseId);
    }
}
