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
import org.springframework.web.multipart.MultipartFile;

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
            Course course = new Course(courseVO);
            course.setTeacher(jwtTeacher);
            int status1 = courseService.addCourse(course);

            //记录组队人数限制策略
            String strategyName1 = "MemberLimitStrategy";
            MemberLimitStrategy memberLimitStrategy = new MemberLimitStrategy();
            memberLimitStrategy.setMinMember(courseVO.getTeamMingMember());
            memberLimitStrategy.setMaxMember(courseVO.getTeamMaxMember());
            int status2 = strategyService.addMemberLimitStrategy(memberLimitStrategy);

            //记录选修课人数限制策略
            List<CourseMemberLimitVO> courseMemberLimitVOList = courseVO.getCourseMemberLimitVOList();
            if(courseMemberLimitVOList!=null && !courseMemberLimitVOList.isEmpty()) {
                String strategyName2;
                String strategyName3 = "CourseMemberLimitStrategy";
                if (courseVO.getRelation() == 0) {
                    //0表示满足其一，或关系
                    strategyName2 = "TeamOrStrategy";
                    List<TeamOrStrategy> teamOrStrategyList = new ArrayList<>();
                    for (CourseMemberLimitVO item : courseMemberLimitVOList) {
                        TeamOrStrategy teamOrStrategy = new TeamOrStrategy();
                        CourseMemberLimitStrategy courseMemberLimitStrategy = new CourseMemberLimitStrategy(item);
                        strategyService.addCourseMembetLimitStrategy(courseMemberLimitStrategy);
                        teamOrStrategy.setStrategyName(strategyName3);
                        teamOrStrategy.getStrategyList().add(courseMemberLimitStrategy);
                        teamOrStrategyList.add(teamOrStrategy);
                    }
                    List<TeamOrStrategy> returnTeamOrStrategyList = strategyService.addTeamOrStrategyList(teamOrStrategyList);
                    //组队人数限制策略和选修课人数限制策略是与关系
                    List<TeamAndStrategy> teamAndStrategyList1 = new ArrayList<>();
                    TeamAndStrategy teamAndStrategy1 = new TeamAndStrategy();
                    teamAndStrategy1.setStrategyName(strategyName1);
                    teamAndStrategy1.getStrategyList().add(memberLimitStrategy);
                    teamAndStrategyList1.add(teamAndStrategy1);
                    TeamAndStrategy teamAndStrategy2 = new TeamAndStrategy();
                    teamAndStrategy2.setStrategyName(strategyName2);
                    for (TeamOrStrategy teamOrStrategy : returnTeamOrStrategyList) {
                        teamAndStrategy2.getStrategyList().add(teamOrStrategy);
                    }
                    List<TeamAndStrategy> returnTeamAndStrategyList1 = strategyService.addTeamAndStrategyList(teamAndStrategyList1);
                    //将策略加到team_strategy表中
                    TeamStrategy teamStrategy = new TeamStrategy();
                    teamStrategy.setCourse(course);
                    teamStrategy.setStrategyName(new String("TeamAndStrategy"));
                    for (TeamAndStrategy teamAndStrategy : returnTeamAndStrategyList1) {
                        teamStrategy.getStrategyList().add(teamAndStrategy);
                    }
                    strategyService.addTeamStrategy(teamStrategy);
                } else {
                    //1表示均满足，与关系
                    strategyName2 = "TeamAndStrategy";
                    List<TeamAndStrategy> teamAndStrategyList = new ArrayList<>();
                    for (CourseMemberLimitVO item : courseMemberLimitVOList) {
                        TeamAndStrategy teamAndStrategy = new TeamAndStrategy();
                        CourseMemberLimitStrategy courseMemberLimitStrategy = new CourseMemberLimitStrategy(item);
                        strategyService.addCourseMembetLimitStrategy(courseMemberLimitStrategy);
                        teamAndStrategy.setStrategyName(strategyName3);
                        teamAndStrategy.getStrategyList().add(courseMemberLimitStrategy);
                        teamAndStrategyList.add(teamAndStrategy);
                    }
                    List<TeamAndStrategy> returnTeamAndStrategyList = strategyService.addTeamAndStrategyList(teamAndStrategyList);
                    //组队人数限制策略和选修课人数限制策略是与关系
                    List<TeamAndStrategy> teamAndStrategyList1 = new ArrayList<>();
                    TeamAndStrategy teamAndStrategy1 = new TeamAndStrategy();
                    teamAndStrategy1.setStrategyName(strategyName1);
                    teamAndStrategy1.getStrategyList().add(memberLimitStrategy);
                    teamAndStrategyList1.add(teamAndStrategy1);
                    TeamAndStrategy teamAndStrategy2 = new TeamAndStrategy();
                    teamAndStrategy2.setStrategyName(strategyName2);
                    for (TeamAndStrategy teamAndStrategy : returnTeamAndStrategyList) {
                        teamAndStrategy2.getStrategyList().add(teamAndStrategy);
                    }
                    List<TeamAndStrategy> returnTeamAndStrategyList2 = strategyService.addTeamAndStrategyList(teamAndStrategyList1);
                    //将策略加到team_strategy表中
                    TeamStrategy teamStrategy = new TeamStrategy();
                    teamStrategy.setCourse(course);
                    teamStrategy.setStrategyName(new String("TeamAndStrategy"));
                    for (TeamAndStrategy teamAndStrategy : returnTeamAndStrategyList2) {
                        teamStrategy.getStrategyList().add(teamAndStrategy);
                    }
                    strategyService.addTeamStrategy(teamStrategy);
                }
            }
            else {
                TeamStrategy teamStrategy = new TeamStrategy();
                teamStrategy.setCourse(course);
                teamStrategy.setStrategyName(strategyName1);
                teamStrategy.getStrategyList().add(memberLimitStrategy);
                strategyService.addTeamStrategy(teamStrategy);
            }
            //记录冲突课程
            List<List<Course>> conflictCourseLists = courseVO.getConflictCourseLists();
            if(conflictCourseLists!=null && !conflictCourseLists.isEmpty()){
                for (List<Course> conflictCourseList:conflictCourseLists) {
                    List<ConflictCourseStrategy> conflictCourseStrategyList = new ArrayList<>();
                    for (Course course1:conflictCourseList) {
                        ConflictCourseStrategy conflictCourseStrategy = new ConflictCourseStrategy();
                        conflictCourseStrategy.setCourse(course1);
                        conflictCourseStrategyList.add(conflictCourseStrategy);
                    }
                    List<ConflictCourseStrategy> returnConflictCourseStrategyList = strategyService.addConflictCourseStrategyList(conflictCourseStrategyList);
                    TeamStrategy teamStrategy = new TeamStrategy();
                    teamStrategy.setCourse(course);
                    teamStrategy.setStrategyName("ConflictCourseStrategy");
                    for (ConflictCourseStrategy item:returnConflictCourseStrategyList) {
                        teamStrategy.getStrategyList().add(item);
                    }
                    strategyService.addTeamStrategy(teamStrategy);
                }
            }

            if(status1 == 0 || status2 == 0){
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
    public void getCourseByTeacherId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        Teacher jwtTeacher = Jwt.unSign(token,Teacher.class);
        if (jwtTeacher!=null){
            List<Course> courseList = courseService.getCourseByTeacherId(jwtTeacher.getId());
            if (courseList.isEmpty()){
                response.setStatus(404);
            }
            else {
                response.setStatus(200);
                String json = JSONObject.toJSONString(courseList);
                response.getWriter().write(json);
            }
        }
        else {
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
        if(roundList.isEmpty()){
            response.setStatus(404);
        }
        else{
            response.setStatus(200);
            String json = JSONObject.toJSONString(roundList);
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

        List<Team> teamVOList = courseService.getTeamByCourseId(courseId);
        String json = JSONObject.toJSONString(teamVOList);
        response.getWriter().write(json);
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
            if(team != null){
                response.setStatus(200);
                String json = JSONObject.toJSONString(team);
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
        if (klassList.isEmpty()){
            response.setStatus(404);
        }
        else {
            response.setStatus(200);
            String json = JSONObject.toJSONString(klassList);
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
     * @param courseId
     * @param response
     * @return
     */
    @GetMapping(value="/{courseId}/teamshare",produces = "application/json; charset=utf-8")
    public void getTeamShareMessage(@PathVariable Long courseId, HttpServletResponse response) throws IOException {
        List<ShareTeamApplication> shareTeamApplicationList = courseService.getShareTeamApplicationByCourseId(courseId);
        if(shareTeamApplicationList.isEmpty()){
            response.setStatus(404);
        }
        else{
            response.setStatus(200);
            String json = JSONObject.toJSONString(shareTeamApplicationList);
            response.getWriter().write(json);
        }
    }

    /**
     * 获取讨论课共享信息
     * @param courseId
     * @param response
     * @return
     */
    @GetMapping(value="/{courseId}/seminarshare",produces = "application/json; charset=utf-8")
    public void getSeminarShareMessage(@PathVariable Long courseId, HttpServletResponse response) throws IOException {
        List<ShareSeminarApplication> shareSeminarApplicationList = courseService.getShareSeminarApplicationByMainCourseId(courseId);
        if(shareSeminarApplicationList.isEmpty()){
            response.setStatus(404);
        }
        else{
            response.setStatus(200);
            String json = JSONObject.toJSONString(shareSeminarApplicationList);
            response.getWriter().write(json);
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



