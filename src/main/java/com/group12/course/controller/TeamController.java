package com.group12.course.controller;

import com.alibaba.fastjson.JSONObject;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.Team;
import com.group12.course.service.TeamService;
import com.group12.course.tools.Jwt;
import com.group12.course.controller.vo.TeamVO;
import com.group12.course.controller.vo.TeamValidApplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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

    /**
     * 添加某个队伍
     *
     * @param teamVO 前端传过来的新加的队伍对象
     * 成功创建返回200以及返回的队伍对象中包含新添加的对象的id
     * 失败返回400
     * */
    @PostMapping(value = "",produces = "application/json; charset=utf-8")
    public void createTeam(@RequestBody TeamVO teamVO, HttpServletResponse response)throws IOException {
        Team team=new Team(teamVO);
        team =teamService.createTeam(team);
        if (team.getId()==null){
            response.setStatus(400);
        }
        else {
            response.setStatus(200);
            String json =JSONObject.toJSONString(team);
            response.getWriter().write(json);
        }
    }

    /**
     * 根据学生 id 获取队伍
     *
     * @param request 包含学生对象的 jwt token 的请求
     * 返回一个包含该学生的队伍
     *
     * 此 api 暂时不实现了
     * */
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

    /**
     * 根据队伍 id 获取队伍
     *
     * @param teamId 队伍id
     * 返回一个包含该学生的队伍
     * */
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

    /**
     * 根据队伍 id 删除队伍
     *
     * @param teamId 队伍id
     * 成功返回200
     * 失败返回404
     * */
    @DeleteMapping(value = "/{teamId}",produces = "application/json; charset=utf-8")
    public void deleteTeam(@PathVariable Long teamId, HttpServletRequest request,HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        teamService.authCheck(jwtStudent,teamId);
        int count = teamService.deleteTeamByTeamId(teamId);
        if (count==-1){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
        }
    }

    /**
     * 根据队伍 id 添加组员
     *
     * @param teamId 队伍id
     * @param student 新加的学生
     * 成功返回200
     * 失败返回404
     * */
    @PutMapping(value = "/{teamId}/add",produces = "application/json; charset=utf-8")
    public void addTeam(@RequestBody Student student, HttpServletRequest request,@PathVariable Long teamId, HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        teamService.authCheck(jwtStudent,teamId);
        Team team=new Team();
        team.setId(teamId);
        Team count = teamService.addMember(team,student);
        if (count.getId()!=null){
            response.setStatus(200);
        }
        else {
            response.setStatus(400);
        }
    }

    /**
     * 根据队伍 id 删除组员
     *
     * @param teamId 队伍id
     * @param student 删除的学生
     * 成功返回200
     * 失败返回404
     * */
    @PutMapping(value = "/{teamId}/remove",produces = "application/json; charset=utf-8")
    public void removeTeammate(@RequestBody Student student, @PathVariable Long teamId, HttpServletRequest request,HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        teamService.authCheck(jwtStudent,teamId);
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
