package com.group12.course.controller;

import com.alibaba.fastjson.JSONObject;
import com.group12.course.controller.vo.RoundInfoVO;
import com.group12.course.controller.vo.RoundScoreVO;
import com.group12.course.controller.vo.SeminarVO;
import com.group12.course.entity.KlassRound;
import com.group12.course.entity.Round;
import com.group12.course.entity.RoundScore;
import com.group12.course.entity.Seminar;
import com.group12.course.service.CourseService;
import com.group12.course.service.RoundService;
import com.group12.course.service.TeamService;
import com.group12.course.controller.vo.RoundVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Round Controller 层
 * @author Tan Xue
 * @date 2018/12/16
 */

@RestController
@RequestMapping(value="/round")
public class RoundController {

    @Autowired
    RoundService roundService;

    /**
     * 获取某轮次下的所有讨论课
     *
     * @param roundId
     * @param response
     * @return
     */
    @GetMapping(value = "/{roundId}/seminar", produces = "application/json; charset=utf-8")
    public void getSeminarByRoundId(@PathVariable Long roundId, HttpServletResponse response) throws IOException {
        List<SeminarVO> seminarVOList = new ArrayList<>();
        List<Seminar> seminarList = roundService.getSeminarByRoundId(roundId);
        for (Seminar seminar:seminarList) {
            SeminarVO seminarVO = new SeminarVO(seminar);
            seminarVOList.add(seminarVO);
        }
        if (seminarVOList.isEmpty()) {
            response.setStatus(404);
        } else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(seminarVOList);
            response.getWriter().write(json);
        }
    }

    /**
     * 根据轮次 id 获取轮次
     *
     * @param roundId
     * @param response
     * @return
     */
    @GetMapping(value = "/{roundId}", produces = "application/json;charset=utf-8")
    public void getRound(@PathVariable Long roundId, HttpServletResponse response) throws IOException {
        Round round = roundService.getRound(roundId);
        RoundInfoVO roundInfoVO = new RoundInfoVO(round);
        if (roundInfoVO == null) {
            response.setStatus(404);
        } else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(roundInfoVO);
            response.getWriter().write(json);
        }
    }

    /**
     * 根据轮次 id 修改轮次,设置本轮次成绩计算规则
     *
     * @param roundId
     * @param roundVO
     * @param response
     */
    @PutMapping(value = "/{roundId}", produces = "application/json;charset=utf-8")
    public void updateRound(@PathVariable Long roundId, @RequestBody RoundVO roundVO, HttpServletResponse response) {
        Round round = new Round(roundVO);
        round.setId(roundId);
        int status = roundService.updateRound(round);
        if (status == 0) {
            response.setStatus(400);
        } else {
            response.setStatus(204);
        }
    }

    /**
     * 创建轮次
     * @param response
     */
    @PostMapping(value = "", produces = "application/json;charset=utf-8")
    public void createRound(@RequestParam Long courseId, HttpServletResponse response) throws IOException {
        /* 需要前端传回courseId*/
        Long roundId = roundService.addRound(courseId);
        if (roundId != null) {
            response.setStatus(201);
            String json = JSONObject.toJSONString(roundService.getRound(roundId));
            response.getWriter().write(json);

        } else {
            response.setStatus(403);

        }
    }


    /**
     * 设置班级轮次报名次数
     * @param klassRoundList
     * @param response
     */
    @PostMapping(value="/klassround",produces = "application/json;charset=utf-8")
    public void createKlassRound(@RequestBody List<KlassRound> klassRoundList,HttpServletResponse response){
        int status = roundService.addKlassRoundList(klassRoundList);
        if (status == 0) {
            response.setStatus(403);
        } else {
            response.setStatus(201);
        }
    }

}