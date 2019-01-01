package com.group12.course.controller;

import com.alibaba.fastjson.JSONObject;
import com.group12.course.controller.vo.*;
import com.group12.course.entity.*;
import com.group12.course.entity.application.ShareSeminarApplication;
import com.group12.course.entity.application.ShareTeamApplication;
import com.group12.course.entity.strategy.*;
import com.group12.course.service.*;
import com.group12.course.tools.Jwt;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Course Controller 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    KlassService klassService;
    @Autowired
    StudentService studentService;
    @Autowired
    RoundService roundService;
    @Autowired
    TeamService teamService;
    @Autowired
    SeminarService  seminarService;
    @Autowired
    StrategyService strategyService;


    /**
     * 新建课程
     * @param courseVO
     * @return Course
     */
    @PostMapping(value="",produces = "application/json; charset=utf-8")
    public void createCourse(@RequestBody CourseVO courseVO, HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        if(jwtTeacher!=null){

            System.out.print(courseVO);
            //添加课程
            Course course = new Course(courseVO);
            course.setTeacher(jwtTeacher);
            //记录组队人数限制策略
            MemberLimitStrategy memberLimitStrategy = new MemberLimitStrategy();
            memberLimitStrategy.setMinMember(courseVO.getTeamMinMember());
            memberLimitStrategy.setMaxMember(courseVO.getTeamMaxMember());
            //记录选修课人数限制策略
            List<CourseMemberLimitVO> courseMemberLimitVOList = courseVO.getCourseMemberLimitVOList();
            List<CourseMemberLimitStrategy> courseMemberLimitStrategyList = new ArrayList<>();
            for (CourseMemberLimitVO courseMemberLimitVO:courseMemberLimitVOList) {
                System.out.print(courseMemberLimitVO);
                CourseMemberLimitStrategy courseMemberLimitStrategy = new CourseMemberLimitStrategy(courseMemberLimitVO);
                courseMemberLimitStrategy.setCourse(courseService.getCourseById(courseMemberLimitVO.getCourseId()));
                courseMemberLimitStrategyList.add(courseMemberLimitStrategy);
            }
            //记录选修课人数限制策略之间的关系
            Integer relation = courseVO.getRelation();
            //记录冲突课程
            List<List<Long>> conflictCourseLists = courseVO.getConflictCourseLists();

            int status = courseService.createCourse(course,memberLimitStrategy, courseMemberLimitStrategyList,relation,conflictCourseLists);

            if(status == 0){
                response.setStatus(403);
            }
            else{
                response.setStatus(201);
            }
        }
        else{
            response.setStatus(404);
        }
    }


    /**
     * 根据课程id 获得课程
     * @param courseId int
     * @return Course
     */
    @GetMapping(value="/{courseId}",produces = "application/json; charset=utf-8")
    public void getCourseById(@PathVariable Long courseId,HttpServletResponse response) throws IOException {
        CourseInfoVO courseInfoVO = new CourseInfoVO();

        Course course = courseService.getCourseById(courseId);
        courseInfoVO.setCourse(course);

        List<TeamStrategy> teamStrategyList = strategyService.getTeamStrategyByCourseId(courseId);
        courseInfoVO.setTeamStrategyList(teamStrategyList);

        if (courseInfoVO==null){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(courseInfoVO);
            response.getWriter().write(json);
        }

    }


    /**
     * 获得所有课程
     * @param response
     */
    @GetMapping(value="/allcourse",produces = "application/json; charset=utf-8")
    public void getAllCourse( HttpServletResponse response) throws IOException {
        List<Course> courseList = courseService.getAllCourse();

        if (courseList.isEmpty()){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(courseList);
            response.getWriter().write(json);
        }
    }


    /**
     * 获得当前用户所有课程
     * @return List<Course>
     */
    @GetMapping(value="",produces = "application/json; charset=utf-8")
    public void getCourseByUserId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        Student jwtStudent = Jwt.unSign(token,Student.class);
        if (jwtTeacher!=null){
            List<Course> courseList = courseService.getCourseByTeacherId(jwtTeacher.getId());
            List<CourseBasicVO> courseBasicVOList = new ArrayList<>();
            for (Course course:courseList) {
                CourseBasicVO courseBasicVO = new CourseBasicVO(course);
                courseBasicVOList.add(courseBasicVO);
            }
            if (courseBasicVOList.isEmpty()){
                response.setStatus(404);
            }
            else {
                response.setStatus(200);
                String json = JSONObject.toJSONString(courseBasicVOList);
                response.getWriter().write(json);
            }
        }
        else if(jwtStudent!= null){
            List<Course> courseList = courseService.getCourseByStudentId(jwtStudent.getId());
            List<CourseBasicVO> courseBasicVOList = new ArrayList<>();
            for (Course course:courseList) {
                CourseBasicVO courseBasicVO = new CourseBasicVO(course);
                courseBasicVOList.add(courseBasicVO);
            }
            if (courseBasicVOList.isEmpty()){
                response.setStatus(404);
            }
            else {
                response.setStatus(200);
                String json = JSONObject.toJSONString(courseBasicVOList);
                response.getWriter().write(json);
            }
        }
        else{
            response.setStatus(403);
        }
    }

    /**
     * 根据课程id 获得课程下的所有轮次
     * @param courseId
     * @param response
     * @return
     */
    @GetMapping(value="/{courseId}/round",produces = "application/json; charset=utf-8")
    public void getRoundByCourseId(@PathVariable Long courseId,HttpServletResponse response) throws IOException {
        List<Round> roundList = roundService.getRoundByCourseId(courseId);
        List<RoundInfoVO> roundInfoVOList = new ArrayList<>();
        for (Round round:roundList) {
            RoundInfoVO roundInfoVO = new RoundInfoVO(round);
            roundInfoVOList.add(roundInfoVO);
        }

        if(roundInfoVOList.isEmpty()){
            response.setStatus(404);
        }
        else{
            response.setStatus(200);
            String json = JSONObject.toJSONString(roundInfoVOList);
            response.getWriter().write(json);
        }
    }





    /**
     * 根据课程id 删除课程
     * @param courseId int
     * @return Course
     */
    @DeleteMapping(value="/{courseId}",produces = "application/json; charset=utf-8")
    public void deleteCourse(@PathVariable Long courseId,HttpServletResponse response){

        int status1 = courseService.deleteCourse(courseId);
        int status2 = strategyService.deleteTeamStrategyByCourseId(courseId);

        if(status1 == 0 || status2 == 0){
            response.setStatus(400);
        }
        else{
            response.setStatus(204);
        }
    }

    /**
     * 根据课程id获取所有小组信息
     * @param courseId
     * @param response
     * @return
     */
    @GetMapping(value="/{courseId}/team",produces = "application/json; charset=utf-8")
    public void getTeamByCourseId(@PathVariable Long courseId,HttpServletRequest request,HttpServletResponse response) throws IOException {

        List<Team> teamList = courseService.getTeamByCourseId(courseId);
        List<TeamBasicVO> teamBasicVOList = new ArrayList<>();
        for (Team team:teamList) {
            TeamBasicVO teamBasicVO = new TeamBasicVO(team);
            teamBasicVOList.add(teamBasicVO);
        }
        if(!teamBasicVOList.isEmpty()){
            response.setStatus(200);
            String json = JSONObject.toJSONString(teamBasicVOList);
            response.getWriter().write(json);
        }
        else{
            response.setStatus(404);
        }
    }


    /**
     * 根据课程id 以及学生id 获取小组信息
     * @param courseId
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value="/{courseId}/myTeam",produces = "application/json; charset=utf-8")
    public void getMyTeam(@PathVariable Long courseId,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        Student jwtStudent = Jwt.unSign(token,Student.class);
        if (jwtStudent!=null){
            Team team = teamService.getTeamByStudentIdAndCourseId(jwtStudent.getId(),courseId);
            TeamBasicVO teamBasicVO = new TeamBasicVO(team);
            if(teamBasicVO != null){
                response.setStatus(200);
                String json = JSONObject.toJSONString(teamBasicVO);
                response.getWriter().write(json);
            }
            else{
                response.setStatus(404);
            }
        }
        else{
            response.setStatus(404);
        }
    }

    /**
     * 根据课程id获取未组队学生
     */
    @GetMapping(value="/{courseId}/noTeam",produces = "application/json; charset=utf-8")
    public void getStudentNoTeam(@PathVariable Long courseId, HttpServletResponse response) throws IOException {

        List<StudentVO> studentVOList = new ArrayList<>();
        List<Student> studentList = courseService.getStudentNoTeam(courseId);
        if(studentList.isEmpty()){
            response.setStatus(404);
        }
        else{
            for (Student student:studentList) {
                StudentVO studentVO = new StudentVO(student);
                studentVOList.add(studentVO);
            }
            response.setStatus(200);
            String json = JSONObject.toJSONString(studentVOList);
            response.getWriter().write(json);
        }
    }

    /**
     * 根据id 获取课程的班级列表
     * @param courseId 课程id
     * @return 班级列表
     */
    @GetMapping(value="/{courseId}/class",produces = "application/json; charset=utf-8")
    public void getKlassByCourseId(@PathVariable Long courseId,HttpServletResponse response) throws IOException {
        List<Klass> klassList = klassService.getAllKlassByCourseId(courseId);
        List<KlassInfoVO> klassInfoVOList = new ArrayList<>();
        for (Klass klass:klassList) {
            KlassInfoVO klassInfoVO = new KlassInfoVO(klass);
            klassInfoVOList.add(klassInfoVO);
        }
        if (klassInfoVOList.isEmpty()){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(klassInfoVOList);
            response.getWriter().write(json);
        }
    }



    /**
     * 在课程下创建班级
     * @param courseId
     * @param response
     */
    @PostMapping(value="/{courseId}/class",produces = "application/json; charset=utf-8")
    public void createKlass(@PathVariable Long courseId, @RequestBody KlassVO klassVO,  HttpServletResponse response){
        //创建班级
        Klass klass = new Klass(klassVO);
        klass.setCourse(courseService.getCourseById(courseId));
        int status = klassService.addKlass(klass);

        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(201);
        }


    }

    /**
     * 获取分组共享信息
     * @param response
     * @return
     */
    @GetMapping(value="/teamshare",produces = "application/json; charset=utf-8")
    public void getTeamShareMessage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        if(jwtTeacher!=null){
            List<ShareTeamApplication> shareTeamApplicationList = courseService.getShareTeamApplicationByTeacherId(jwtTeacher.getId());
            if(shareTeamApplicationList.isEmpty()){
                response.setStatus(404);
            }
            else{
                response.setStatus(200);
                String json = JSONObject.toJSONString(shareTeamApplicationList);
                response.getWriter().write(json);
            }
        }
        else{
            response.setStatus(403);
        }

    }

    /**
     * 获取讨论课共享信息
     * @param response
     * @return
     */
    @GetMapping(value="/seminarshare",produces = "application/json; charset=utf-8")
    public void getSeminarShareMessage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        if(jwtTeacher!=null){
            List<ShareSeminarApplication> shareSeminarApplicationList = courseService.getShareSeminarApplicationByTeacherId(jwtTeacher.getId());
            if(shareSeminarApplicationList.isEmpty()){
                response.setStatus(404);
            }
            else{
                response.setStatus(200);
                String json = JSONObject.toJSONString(shareSeminarApplicationList);
                response.getWriter().write(json);
            }
        }
        else{
            response.setStatus(403);
        }

    }

    /**
     * 处理分组共享
     * @param teamshareId
     * @param response
     */
    @PostMapping(value="/teamshare/{teamshareId}",produces = "application/json; charset=utf-8")
    public void handleTeamShare(@PathVariable Long teamshareId, @Param("handler") Integer handler, HttpServletResponse response){
        int status = courseService.handleTeamShare(teamshareId,handler);
        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(201);
        }
    }


    /**
     * 处理讨论课共享
     * @param seminarshareId
     * @param response
     */
    @PostMapping(value="/seminarshare/{seminarshareId}",produces = "application/json; charset=utf-8")
    public void handleSeminarShare(@PathVariable Long seminarshareId,@Param("handler") Integer handler,HttpServletResponse response){
        int status = courseService.handleSeminarShare(seminarshareId,handler);
        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(201);
        }
    }


    /**
     * 取消分组共享
     * @param teamshareId
     * @param response
     */
    @DeleteMapping(value="/teamshare/{teamshareId}",produces = "application/json; charset=utf-8")
    public void deleteTeamShareById(@PathVariable Long teamshareId,HttpServletResponse response){
        int status = courseService.deleteShareTeamApplication(teamshareId);
        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(200);
        }
    }

    /**
     * 取消讨论课共享
     * @param seminarshareId
     * @param response
     */
    @DeleteMapping(value="/seminarshare/{seminarshareId}",produces = "application/json; charset=utf-8")
    public void deleteSeminarShareById(@PathVariable Long seminarshareId,HttpServletResponse response){
        int status = courseService.deleteShareSeminarApplication(seminarshareId);
        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(200);
        }
    }

    /**
     * 发起一个分组共享请求
     * @param courseId
     * @param response
     */
    @PostMapping(value="/{courseId}/teamsharerequest",produces = "application/json; charset=utf-8")
    public void requestTeamShare(@PathVariable Long courseId,@RequestBody List<Long> subCourseIdList, HttpServletResponse response){
        int status = 1;
        for (Long subCourseId:subCourseIdList) {
            ShareTeamApplication shareTeamApplication = new ShareTeamApplication();

            shareTeamApplication.setMainCourse(courseService.getCourseById(courseId));
            Course subCourse = courseService.getCourseById(subCourseId);
            shareTeamApplication.setSubCourse(subCourse);
            shareTeamApplication.setSubCourseTeacher(subCourse.getTeacher());

            status = courseService.addShareTeamApplication(shareTeamApplication)==0?0:status;
        }

        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(201);
        }
    }

    /**
     * 发起一个讨论课共享请求
     * @param courseId
     * @param response
     */
    @PostMapping(value="/{courseId}/seminarsharerequest",produces = "application/json; charset=utf-8")
    public void requestSeminarShare(@PathVariable Long courseId,@RequestBody List<Long> subCourseIdList,HttpServletResponse response){
        int status= 1;
        for (Long subCourseId:subCourseIdList) {
            ShareSeminarApplication shareSeminarApplication = new ShareSeminarApplication();

            shareSeminarApplication.setMainCourse(courseService.getCourseById(courseId));
            Course subCourse = courseService.getCourseById(subCourseId);
            shareSeminarApplication.setSubCourse(subCourse);
            shareSeminarApplication.setSubCourseTeacher(subCourse.getTeacher());

            status = courseService.addShareSeminarApplication(shareSeminarApplication)==0?0:status;
        }
        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(201);
        }
    }


    /**
     * 新建队伍
     * @param teamInfoVO
     * @param courseId
     * @param classId
     * @param response
     */
    @PostMapping(value="/{courseId}/class/{classId}/team",produces = "application/json; charset=utf-8")
    public void createTeam(@RequestBody TeamInfoVO teamInfoVO, @PathVariable Long courseId, @PathVariable Long classId, HttpServletResponse response){
        /* 这里界面返回的内容与api标准的有所差别*/
        Team team = new Team();
        team.setTeamName(teamInfoVO.getName());
        team.setLeader(teamInfoVO.getLeader());
        team.setMembers(teamInfoVO.getMembers());

        Course course = courseService.getCourseById(courseId);
        team.setCourse(course);
        Klass klass = klassService.getKlass(classId);
        team.setKlass(klass);

        Team returnTeam = teamService.createTeam(team);
        if(returnTeam==null){
            response.setStatus(403);
        }
        else{
            response.setStatus(201);
        }
    }

}



