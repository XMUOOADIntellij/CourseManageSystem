package com.group12.course.controller;

import com.alibaba.fastjson.JSONObject;
import com.group12.course.controller.vo.RoundScoreInfoVO;
import com.group12.course.dao.SeminarDao;
import com.group12.course.entity.Round;
import com.group12.course.entity.RoundScore;
import com.group12.course.entity.Seminar;
import com.group12.course.service.CourseService;
import com.group12.course.service.RoundService;
import com.group12.course.service.TeamService;
import com.group12.course.controller.vo.RoundScoreVO;
import com.group12.course.controller.vo.RoundVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Round Controller 层
 * @authon Tan Xue
 * @date 2018/12/16
 */

@RestController
@RequestMapping(value="/round")
public class RoundController {

    @Autowired
    RoundService roundService;
    @Autowired
    CourseService courseService;
    @Autowired
    TeamService teamService;

    /**
     * 获取某轮次下的所有讨论课
     *
     * @param roundId
     * @param response
     * @return
     */
    @GetMapping(value = "/{roundId}/seminar", produces = "application/json; charset=utf-8")
    public void getSeminarByRoundId(@PathVariable Long roundId, HttpServletResponse response) throws IOException {
        List<Seminar> seminarList = roundService.getSeminarByRoundId(roundId);
        if (seminarList.isEmpty()) {
            response.setStatus(404);
        } else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(seminarList);
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
        if (round == null) {
            response.setStatus(404);
        } else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(round);
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
    public void createRound(@RequestParam Long courseId, HttpServletResponse response) {
        /* 需要前端传回courseId*/

        Long status = roundService.addRound(courseId);
        if (status == 0) {
            response.setStatus(403);
        } else {
            response.setStatus(201);
        }
    }

    /**
     * 根据轮次id 查找所有的成绩
     *
     * @param roundId
     * @param response
     * @return
     */
    @GetMapping(value = "/{roundId}/roundscore", produces = "application/json;charset=utf-8")
    public void getRoundScoreByRoundId(@PathVariable Long roundId, HttpServletResponse response) throws IOException {
        List<RoundScore> roundScoreList = roundService.getRoundScoreByRoundId(roundId);
        if (roundScoreList.isEmpty()) {
            response.setStatus(404);
        } else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(roundScoreList);
            response.getWriter().write(json);
        }
    }

    /**
     * 获取某轮次下某组的成绩信息
     *
     * @param response
     * @return
     */
    @GetMapping(value = "/{roundId}/team/{teamId}/roundscore", produces = "application/json;charset=utf-8")
    public void getTeamScoreByRoundIdAndTeamId(@PathVariable Long roundId, @PathVariable Long teamId, HttpServletResponse response) throws IOException {
        RoundScore roundScore = roundService.getRoundScoreByRoundIdAndTeamId(roundId, teamId);
        if (roundScore == null) {
            response.setStatus(404);
        } else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(roundScore);
            response.getWriter().write(json);
        }
    }

//    /**
//     * 按id 修改轮次成绩信息
//     * @param roundScoreInfoVO
//     * @param roundId
//     * @param teamId
//     * @param response
//     */
//    @PutMapping(value = "/{roundId/team/{teamId}/roundscore", produces = "application/json;charset=utf-8")
//    public void updateRoundScore(@RequestBody RoundScoreInfoVO roundScoreInfoVO, @PathVariable Long roundId, @PathVariable Long teamId, HttpServletResponse response) {
//        RoundScore roundScore = new RoundScore();
//        roundScore.setRound(roundService.getRound(roundId));
//        roundScore.setTeam(teamService.getTeamByTeamId(teamId));
//        roundScore.setPresentationScore(roundScoreInfoVO.getPresentationScore());
//        roundScoreInfoVO.setQuestionScore(roundScoreInfoVO.getQuestionScore());
//        roundScore.setReportScore(roundScoreInfoVO.getReportScore());
//        int status = roundService.updateRoundScore(roundScore);
//        if (status == 0) {
//            response.setStatus(403);
//        } else {
//            response.setStatus(201);
//        }
//
//   }
}