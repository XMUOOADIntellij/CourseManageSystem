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
        return roundDao.getRound(roundId);
    }

    /**
     * 根据轮次 id 修改轮次
     * @param round
     * @param round
     */
    public int updateRound(Round round){
        return  roundDao.updateRound(round);
    }

    /**
     * 创建轮次
     * @param round
     */
    public int addRound(Round round){
        return roundDao.addRound(round);
    }

    /**
     * 计算某课程下的轮次个数
     * @param courseId
     * @return
     */
    public int countRoundByCourseId(Long courseId){
        List<Round> roundList = roundDao.getRoundByCourseId(courseId);
        Integer count = roundList.size();
        return count;
    }
}
