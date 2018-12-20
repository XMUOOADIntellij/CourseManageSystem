package com.group12.course.controller;

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
    public List<TeamValidApplication> getTeamValid(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        if (jwtTeacher!=null){
            List<TeamValidApplication> list = teamValidApplicationService.getTeamValidApplicationListByTeacher(jwtTeacher);
            return list;
        }
        else {
            response.setStatus(403);
            return new ArrayList<>();
        }
    }

    @GetMapping(value = "/teamvalid/{teamvalidId}",produces = "application/json; charset=utf-8")
    public TeamValidApplication getTeamValidById(@PathVariable Long teamvalidId,HttpServletRequest request, HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        if (jwtTeacher!=null){
            TeamValidApplication teamValidApplication = new TeamValidApplication();
            teamValidApplication.setId(teamvalidId);
            teamValidApplication.setTeacher(jwtTeacher);
            TeamValidApplication result = teamValidApplicationService.getTeamValidApplicationMapperById(teamValidApplication);
            return result;
        }
        else {
            response.setStatus(403);
            return new TeamValidApplication();
        }
    }

    @PutMapping(value = "/teamvalid/{teamvalidId}", produces = "application/json; charset=utf-8")
    public TeamValidApplication changeTeamValidStatus(@PathVariable Long teamvalidId, TeamValidApplicationVO teamValidApplicationVO, HttpServletRequest request, HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        if (jwtTeacher!=null){
            TeamValidApplication teamValidApplication = new TeamValidApplication(teamValidApplicationVO,jwtTeacher);
            teamValidApplication.setId(teamvalidId);
            TeamValidApplication result = teamValidApplicationService.getTeamValidApplicationMapperById(teamValidApplication);
            return result;
        }
        else {
            response.setStatus(403);
            return new TeamValidApplication();
        }
    }
}
