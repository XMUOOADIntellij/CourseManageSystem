package com.group12.course.dao;

import com.group12.course.entity.Presentation;
import com.group12.course.mapper.PresentationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PresentationDao {
    @Autowired
    PresentationMapper presentationMapper;


    public Long enrollPresentation(Presentation record){
        // TODO 和讨论课的关系
        // TODO 错误处理，判断小组id是否存在
        // 新建时间为当前
        record.setGmtCreate(LocalDateTime.now());
        if(presentationMapper.insertSelective(record)==1){
           return record.getId();}
        else {
            //TODO 错误处理，插入记录失败
            return null;
        }
    }
}
