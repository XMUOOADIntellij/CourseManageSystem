package com.group12.course.service;

import com.group12.course.dao.RoundDao;
import com.group12.course.entity.Round;
import com.group12.course.entity.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Round Service 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Service
public class RoundService {

    @Autowired
    RoundDao roundDao;


    /**
     * 获取某轮次下的讨论课
     * @param roundId
     * @return
     */
    public List<Seminar> getSeminarByRoundId(Long roundId){
        /*TODO*/
        return new ArrayList<>();
    }

    /**
     * 根据轮次 id 获取轮次
     * @param roundId
     * @return
     */
    public Round getRound(Long roundId){
        Round round = roundDao.getRound(roundId);
        return round;
    }

    /**
     * 根据轮次 id 修改轮次
     * @param roundId
     * @param round
     */
    public void updateRound(Long roundId,Round round){
        int status = roundDao.updateRound(round);

    }

    /**
     * 创建轮次
     * @param round
     */
    public void createRound(Round round){
        int status = roundDao.addRound(round);

    }
}
