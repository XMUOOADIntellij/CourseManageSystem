package com.group12.course.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group12.course.entity.Course;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.entity.Team;
import com.group12.course.service.TeamService;
import com.group12.course.tools.Jwt;
import com.group12.course.vo.TeamVO;
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
        }
    }

  /**
   *
   * */
    @GetMapping(value = "",produces = "application/json; charset=utf-8")
    public void getTeam(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        // 区分传入的是学生还是教师，调用不同的 Service
        if (jwtStudent!=null){
            Team team = teamService.getTeamByStudentId(jwtStudent.getId());
            if (team==null){

            }
            else {
                response.setStatus(200);
                TeamVO teamVO = new TeamVO(team);
                //System.out.println(teamVO);
                String jsson= JSONObject.toJSONString(teamVO);
                //System.out.println(jsson);
                response.getWriter().write(jsson);
                /*ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
                String json = mapper.writeValueAsString(teamVO);
                System.out.println("Java2Json: "+json);*/
                //return teamVO;
            }
        }
        else {
            //return new TeamVO();
        }
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
