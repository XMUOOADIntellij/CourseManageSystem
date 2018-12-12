package com.group12.course.dao;

import com.group12.course.entity.Klass;
import com.group12.course.mapper.KlassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Klass Dao 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Component
public class KlassDao {
    @Autowired
    KlassMapper klassMapper;

    public Klass getKlass(Long id){
        return klassMapper.getKlass(id);
    }

    public int deleteKlass(Long id){
        return klassMapper.deleteKlass(id);
    }

    public int addKlass(Klass klass){
        return klassMapper.addKlass(klass);
    }

    public int updateKlass(Klass klass){
        return klassMapper.updateKlass(klass);
    }
}
