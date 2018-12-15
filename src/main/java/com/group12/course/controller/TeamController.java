package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group12.course.entity.Student;
import com.group12.course.entity.Team;
import com.group12.course.service.TeamService;
import com.group12.course.vo.TeamVO;
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
    public void createTeam(@RequestBody TeamVO team, HttpServletResponse response)throws IOException {
        /*TODO*/
    }

    @GetMapping(value = "/{teamId}",produces = "application/json; charset=utf-8")
    public Team getTeam(@RequestBody Team team,@PathVariable String teamId, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new Team();
    }

    @PutMapping(value = "/{teamId}",produces = "application/json; charset=utf-8")
    public Team updateTeam(@RequestBody Team team,@PathVariable String teamId, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new Team();
    }

    @DeleteMapping(value = "/{teamId}",produces = "application/json; charset=utf-8")
    public Team deleteTeam(@RequestBody Team team,@PathVariable String teamId, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new Team();
    }

    @PutMapping(value = "/{teamId}/add",produces = "application/json; charset=utf-8")
    public Team addTeam(@RequestBody Team team,@PathVariable String teamId, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new Team();
    }

    @PutMapping(value = "/{teamId}/remove",produces = "application/json; charset=utf-8")
    public Team removeTeammate(@RequestBody Team team,@PathVariable String teamId, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new Team();
    }

    @PostMapping(value = "/{teamId}/teamvalidrequest",produces = "application/json; charset=utf-8")
    public void sendRequest(@RequestBody Team team, @PathVariable String teamId, HttpServletResponse response)throws IOException {
        /*TODO*/
    }

    @PutMapping(value = "/{teamId}/approve",produces = "application/json; charset=utf-8")
    public Team approveRequest(@RequestBody Team team,@PathVariable String teamId, HttpServletResponse response)throws IOException {
        /*TODO*/
        return new Team();
    }
}
