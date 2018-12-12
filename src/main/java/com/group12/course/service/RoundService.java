package com.group12.course.service;

import com.group12.course.dao.RoundDao;
import com.group12.course.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Round Service 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Service
public class RoundService {

    @Autowired
    RoundDao roundDao;

    public Round getRound(Long id){
        return roundDao.getRound(id);
    }

    public int deleteRound(Long id){
        return roundDao.deleteRound(id);
    }

    public int addRound(Round round){
        return roundDao.addRound(round);
    }

    public int updateRound(Round round){
        return roundDao.updateRound(round);
    }
}
