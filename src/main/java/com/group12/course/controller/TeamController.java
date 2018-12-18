package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group12.course.entity.Course;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.Team;
import com.group12.course.entity.application.TeamValidApplication;
import com.group12.course.service.TeamService;
import com.group12.course.tools.Jwt;
import com.group12.course.vo.StudentVO;
import com.group12.course.vo.TeamVO;
import com.group12.course.vo.TeamValidApplicationVO;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Team Controller 层
 * @author Xu Gang
 * @date 2018年12月11日
 * */
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping(value = "",produces = "application/json; charset=utf-8")
    public void createTeam(@RequestBody TeamVO teamVO, HttpServletResponse response)throws IOException {
        Team team=new Team(teamVO);
        int status=teamService.createTeam(team);
        if (status==0){
            response.setStatus(400);
        }
        else {
            response.setStatus(200);
            response.getWriter().write(""+team.getId());
            /**
             * TODO
             * 返回 id
             * */
        }
    }

    @GetMapping(value = "",produces = "application/json; charset=utf-8")
    public void getTeam(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        if (jwtStudent!=null){
            Team team = teamService.getTeamByStudentId(jwtStudent.getId());
            if (team==null){
                response.setStatus(404);
            }
            else {
                response.setStatus(200);
                String json= JSONObject.toJSONString(team);
                response.getWriter().write(json);
            }
        }
        else {
            response.setStatus(403);
        }
    }

    @GetMapping(value = "/{teamId}",produces = "application/json; charset=utf-8")
    public void getTeamByTeamId(@PathVariable Long teamId, HttpServletResponse response)throws IOException {
        Team returnTeam = teamService.getTeamByTeamId(teamId);
        if (returnTeam==null){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(returnTeam);
            response.getWriter().write(json);
        }
    }

    @PutMapping(value = "/{teamId}",produces = "application/json; charset=utf-8")
    public Team updateTeam(@RequestBody TeamVO teamVO,@PathVariable String teamId, HttpServletResponse response)throws IOException {
        /*暂时不实现了。不存在这个 api */
        return new Team();
    }

    @DeleteMapping(value = "/{teamId}",produces = "application/json; charset=utf-8")
    public void deleteTeam(@PathVariable Long teamId, HttpServletResponse response)throws IOException {
        int count = teamService.deleteTeamByTeamId(teamId);
        if (count==-1){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
        }
    }

    @PutMapping(value = "/{teamId}/add",produces = "application/json; charset=utf-8")
    public void addTeam(@RequestBody Student student, @PathVariable Long teamId, HttpServletResponse response)throws IOException {
        Team team=new Team();
        team.setId(teamId);
        int count = teamService.addMember(team,student);
        if (count==1){
            response.setStatus(200);
        }
        else {
            response.setStatus(400);
        }
    }

    @PutMapping(value = "/{teamId}/remove",produces = "application/json; charset=utf-8")
    public void removeTeammate(@RequestBody Student student, @PathVariable Long teamId, HttpServletResponse response)throws IOException {
        Team team=new Team();
        team.setId(teamId);
        int count = teamService.deleteTeamMember(team,student);
        if (count==1){
            response.setStatus(200);
        }
        else {
            response.setStatus(400);
        }
    }

    @PostMapping(value = "/{teamId}/teamvalidrequest",produces = "application/json; charset=utf-8")
    public void sendRequest(@RequestBody TeamValidApplicationVO teamValidApplicationVO, @PathVariable Long teamId, HttpServletResponse response)throws IOException {
        //TeamValidApplication teamValidApplication = new TeamValidApplication(teamValidApplicationVO);

        // TODO 前端不能回传老师的id，所以需要根据课程去找老师的id
    }

    @PutMapping(value = "/{teamId}/approve",produces = "application/json; charset=utf-8")
    public Team approveRequest(@RequestBody Team team,@PathVariable String teamId, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new Team();
    }
}
