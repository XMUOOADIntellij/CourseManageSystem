package com.group12.course.controller;

import com.group12.course.entity.Course;
import com.group12.course.entity.Round;
import com.group12.course.entity.Seminar;
import com.group12.course.service.CourseService;
import com.group12.course.service.RoundService;
import com.group12.course.tools.Jwt;
import com.group12.course.vo.RoundVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    CourseService courseService;

    /**
     * 获取某轮次下的所有讨论课
     * @param roundId
     * @param response
     * @return
     */
    @GetMapping(value="/{roundId}/seminar",produces = "application/json; charset=utf-8")
    public List<Seminar> getSeminarByRoundId(@PathVariable Long roundId,HttpServletResponse response){
        /*TODO get方法还没有*/
        List<Seminar> seminarList = roundService.getSeminarByRoundId(roundId);
        if(seminarList.isEmpty()){
            response.setStatus(404);
            return new ArrayList<>();
        }
        else{
            response.setStatus(200);
            return seminarList;
        }
    }

    /**
     * 根据轮次 id 获取轮次
     * @param roundId
     * @param response
     * @return
     */
    @GetMapping(value="/{roundId}",produces = "application/json;charset=utf-8")
    public Round getRound(@PathVariable Long roundId, HttpServletResponse response){
        Round round = roundService.getRound(roundId);
        if(round == null){
            response.setStatus(404);
            return null;
        }
        else{
            response.setStatus(200);
            return round;
        }
    }

    /**
     * 根据轮次 id 修改轮次,设置本轮次成绩计算规则
     * @param roundId
     * @param roundVO
     * @param response
     */
    @PutMapping(value="/{roundId}",produces = "application/json;charset=utf-8")
    public void updateRound(@PathVariable Long roundId, @RequestBody RoundVO roundVO, HttpServletResponse response){
        Round round = new Round(roundVO);
        round.setId(roundId);
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
     * @param response
     */
    @PostMapping(value="",produces = "application/json;charset=utf-8")
    public void createRound(HttpServletRequest request,HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        Course jwtCourse = Jwt.unSign(token, Course.class);
        if (jwtCourse != null) {
            Long courseId = jwtCourse.getId();

            Round round = new Round();
//            round.setCourse(courseService.getCourseById(courseId));
            Integer serial = roundService.countRoundByCourseId(courseId)+1;
            round.setRoundSerial(serial);

            int status = roundService.addRound(round);
            if (status == 0) {
                response.setStatus(403);
            } else {
                response.setStatus(201);
            }
        } else {
            response.setStatus(403);
        }

//        Round round = new Round();
//        round.setCourse(courseService.getCourseById(new Long(1)));
//        /*TODO roundSerial*/
//        int status = roundService.addRound(round);
//        if(status == 0){
//            response.setStatus(403);
//        }
//        else{
//            response.setStatus(201);
//        }
    }

}
