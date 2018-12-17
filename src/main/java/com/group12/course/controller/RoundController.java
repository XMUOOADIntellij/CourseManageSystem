package com.group12.course.controller;

import com.group12.course.entity.Round;
import com.group12.course.entity.Seminar;
import com.group12.course.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    /**
     * 获取某轮次下的讨论课
     * @param roundId
     * @param response
     * @return
     */
    @GetMapping(value="/{roundId}/seminar",produces = "application/json; charset=utf-8")
    public List<Seminar> getSeminarByRoundId(@PathVariable Long roundId,HttpServletResponse response){
        /*TODO*/
        return new ArrayList<>();
    }

    /**
     * 根据轮次 id 获取轮次
     * @param roundId
     * @param response
     * @return
     */
    @GetMapping(value="/{roundId}",produces = "application/json;charset=utf-8")
    public Round getRound(@PathVariable Long roundId,HttpServletResponse response){
        Round round = roundService.getRound(roundId);
        if(round == null){
            response.setStatus(404);
        }
        else{
            response.setStatus(200);
        }
        return round;
    }

    /**
     * 根据轮次 id 修改轮次
     * @param roundId
     * @param round
     * @param response
     */
    @PutMapping(value="/{roundId}",produces = "application/json;charset=utf-8")
    public void updateRound(@PathVariable Long roundId,@RequestBody Round round, HttpServletResponse response){
        int status = roundService.updateRound(round);
        if(status == 0){
            response.setStatus(400);
        }
        else{
            response.setStatus(204);
        }
    }

    /**
     * 创建轮次
     * @param round
     * @param response
     */
    @PostMapping(value="",produces = "application/json;charset=utf-8")
    public void createRound(@RequestBody Round round,HttpServletResponse response){
        int status = roundService.addRound(round);
        if(status == 0){
            response.setStatus(400);
        }
        else{
            response.setStatus(204);
        }
    }

}
