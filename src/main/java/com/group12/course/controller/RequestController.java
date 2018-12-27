package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.application.TeamValidApplication;
import com.group12.course.service.TeamValidApplicationService;
import com.group12.course.tools.Jwt;
import com.group12.course.controller.vo.TeamValidApplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Request Controller
 * @author Xu Gang
 * @date 2018年12月18日
 * */

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    TeamValidApplicationService teamValidApplicationService;

    @GetMapping(value = "/teamvalid",produces = "application/json; charset=utf-8")
    public void getTeamValid(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        if (jwtTeacher!=null){
            List<TeamValidApplication> list = teamValidApplicationService.getTeamValidApplicationListByTeacher(jwtTeacher);
            String json = JSON.toJSONString(list);
            response.getWriter().write(json);
        }
        else {
            response.setStatus(403);
        }
    }

    /**
     * 获取特定请求的 id
     *
     * @param teamvalidId 审核请求的 id
     */
    @GetMapping(value = "/teamvalid/{teamvalidId}",produces = "application/json; charset=utf-8")
    public void getTeamValidById(@PathVariable Long teamvalidId,HttpServletRequest request, HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        if (jwtTeacher!=null){
            TeamValidApplication teamValidApplication = new TeamValidApplication();
            teamValidApplication.setId(teamvalidId);
            teamValidApplication.setTeacher(jwtTeacher);
            TeamValidApplication result = teamValidApplicationService.getTeamValidApplicationMapperById(teamValidApplication);
            String json = JSON.toJSONString(result);
            response.getWriter().write(json);
            response.setStatus(200);
        }
        else {
            response.setStatus(403);
        }
    }

    /**
     * 更改队伍审核请求
     *
     * @param teamvalidId 队伍审核的 id
     * @param teamValidApplicationVO 审核对象
     */
    @PutMapping(value = "/teamvalid/{teamvalidId}", produces = "application/json; charset=utf-8")
    public void changeTeamValidStatus(@PathVariable Long teamvalidId,@RequestBody TeamValidApplicationVO teamValidApplicationVO, HttpServletRequest request, HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        int status = 0;
        TeamValidApplication teamValidApplication = new TeamValidApplication(teamValidApplicationVO,jwtTeacher);
        teamValidApplication.setId(teamvalidId);
        String result = teamValidApplicationVO.getHandletype();
        if (jwtTeacher!=null&&result!=null){
            switch (result){
                case "accept":teamValidApplication.setStatus(1);break;
                case "reject":teamValidApplication.setStatus(0);break;
                default:response.setStatus(400);break;
            }
            status = teamValidApplicationService.changeApplicationStatus(teamValidApplication);
        }
        else {
            response.setStatus(403);
        }
        if (status==1){
            response.setStatus(204);
        }
        else {
            response.setStatus(404);
        }
    }
}
