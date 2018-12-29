package com.group12.course.service;

import com.group12.course.controller.vo.CourseMemberLimitVO;
import com.group12.course.dao.*;
import com.group12.course.entity.*;
import com.group12.course.entity.application.ShareSeminarApplication;
import com.group12.course.entity.application.ShareTeamApplication;
import com.group12.course.entity.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Klass Service 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Service
public class CourseService {

    @Autowired
    CourseDao courseDao;
    @Autowired
    ShareTeamApplicationDao shareTeamApplicationDao;
    @Autowired
    ShareSeminarApplicationDao shareSeminarApplicationDao;
    @Autowired
    KlassStudentDao klassStudentDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    KlassDao klassDao;
    @Autowired
    SeminarDao seminarDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    TeamStrategyDao teamStrategyDao;
    @Autowired
    TeamAndStrategyDao teamAndStrategyDao;
    @Autowired
    TeamOrStrategyDao teamOrStrategyDao;
    @Autowired
    ConflictCourseStrategyDao conflictCourseStrategyDao;
    @Autowired
    MemberLimitStrategyDao memberLimitStrategyDao;
    @Autowired
    CourseMemberLimitStrategyDao courseMemberLimitStrategyDao;


    /**
     * 获得当前用户所有课程
     *
     * @param teacherId 老师ID
     * @return List<Course>
     */
    public List<Course> getCourseByTeacherId(Long teacherId) {
        return courseDao.getCourseByTeacherId(teacherId);
    }

    /**
     * 获得所有课程
     *
     * @return
     */
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    /**
     * 根据课程id获得课程
     *
     * @param courseId int
     * @return Course
     */
    public Course getCourseById(Long courseId) {
        return courseDao.getCourse(courseId);
    }

    /**
     * 创建课程
     *
     * @param course
     * @return Course
     */
    public int createCourse(Course course, MemberLimitStrategy memberLimitStrategy,
                            List<CourseMemberLimitStrategy> courseMemberLimitStrategyList,
                            Integer relation, List<List<Long>> conflictCourseLists) {

        //添加课程
        int status1 = courseDao.addCourse(course);

        //记录组队人数限制策略
        String strategyName1 = "MemberLimitStrategy";
        int status2 = memberLimitStrategyDao.addMemberLimitStrategy(memberLimitStrategy);

        //记录选修课人数限制策略
        if (courseMemberLimitStrategyList != null && !courseMemberLimitStrategyList.isEmpty()) {
            String strategyName2;
            String strategyName3 = "CourseMemberLimitStrategy";
            if (relation == 0) {
                //0表示满足其一，或关系
                strategyName2 = "TeamOrStrategy";
                List<TeamOrStrategy> teamOrStrategyList = new ArrayList<>();

                for (CourseMemberLimitStrategy courseMemberLimitStrategy : courseMemberLimitStrategyList) {
                    courseMemberLimitStrategyDao.addCourseMemberLimitStrategy(courseMemberLimitStrategy);

                    TeamOrStrategy teamOrStrategy = new TeamOrStrategy();
                    teamOrStrategy.setStrategyName(strategyName3);
                    List<Strategy> strategyList = new ArrayList<>();
                    strategyList.add(courseMemberLimitStrategy);
                    teamOrStrategy.setStrategyList(strategyList);

                    teamOrStrategyList.add(teamOrStrategy);
                }
                List<TeamOrStrategy> returnTeamOrStrategyList = teamOrStrategyDao.addTeamOrStrategyList(teamOrStrategyList);


                //组队人数限制策略和选修课人数限制策略是与关系
                List<TeamAndStrategy> teamAndStrategyList1 = new ArrayList<>();

                TeamAndStrategy teamAndStrategy1 = new TeamAndStrategy();
                teamAndStrategy1.setStrategyName(strategyName1);
                List<Strategy> strategyList = new ArrayList<>();
                strategyList.add(memberLimitStrategy);
                teamAndStrategy1.setStrategyList(strategyList);
                teamAndStrategyList1.add(teamAndStrategy1);

                TeamAndStrategy teamAndStrategy2 = new TeamAndStrategy();
                teamAndStrategy2.setStrategyName(strategyName2);
                List<Strategy> strategyList1 = new ArrayList<>();
                for (TeamOrStrategy teamOrStrategy : returnTeamOrStrategyList) {
                    strategyList1.add(teamOrStrategy);
                }
                teamAndStrategy2.setStrategyList(strategyList1);
                teamAndStrategyList1.add(teamAndStrategy2);

                List<TeamAndStrategy> returnTeamAndStrategyList1 = teamAndStrategyDao.addTeamAndStrategyList(teamAndStrategyList1);


                //将策略加到team_strategy表中
                TeamStrategy teamStrategy = new TeamStrategy();
                teamStrategy.setCourse(course);
                teamStrategy.setStrategyName(new String("TeamAndStrategy"));
                List<Strategy> strategyList2 = new ArrayList<>();
                for (TeamAndStrategy teamAndStrategy : returnTeamAndStrategyList1) {
                    strategyList2.add(teamAndStrategy);
                }
                teamStrategy.setStrategyList(strategyList2);

                teamStrategyDao.addTeamStrategy(teamStrategy);

            } else {
                //1表示均满足，与关系
                strategyName2 = "TeamAndStrategy";
                List<TeamAndStrategy> teamAndStrategyList = new ArrayList<>();
                for (CourseMemberLimitStrategy courseMemberLimitStrategy : courseMemberLimitStrategyList) {

                    courseMemberLimitStrategyDao.addCourseMemberLimitStrategy(courseMemberLimitStrategy);

                    TeamAndStrategy teamAndStrategy = new TeamAndStrategy();
                    teamAndStrategy.setStrategyName(strategyName3);
                    List<Strategy> strategyList = new ArrayList<>(1);
                    strategyList.add(courseMemberLimitStrategy);
                    teamAndStrategy.setStrategyList(strategyList);
                    teamAndStrategyList.add(teamAndStrategy);
                }
                List<TeamAndStrategy> returnTeamAndStrategyList = teamAndStrategyDao.addTeamAndStrategyList(teamAndStrategyList);


                //组队人数限制策略和选修课人数限制策略是与关系
                List<TeamAndStrategy> teamAndStrategyList1 = new ArrayList<>();

                TeamAndStrategy teamAndStrategy1 = new TeamAndStrategy();
                teamAndStrategy1.setStrategyName(strategyName1);
                List<Strategy> strategyList = new ArrayList<>();
                strategyList.add(memberLimitStrategy);
                teamAndStrategy1.setStrategyList(strategyList);
                teamAndStrategyList1.add(teamAndStrategy1);

                TeamAndStrategy teamAndStrategy2 = new TeamAndStrategy();
                teamAndStrategy2.setStrategyName(strategyName2);
                List<Strategy> strategyList1 = new ArrayList<>();
                for (TeamAndStrategy teamAndStrategy : returnTeamAndStrategyList) {
                    strategyList1.add(teamAndStrategy);
                }
                teamAndStrategy2.setStrategyList(strategyList1);
                teamAndStrategyList1.add(teamAndStrategy2);

                List<TeamAndStrategy> returnTeamAndStrategyList2 = teamAndStrategyDao.addTeamAndStrategyList(teamAndStrategyList1);


                //将策略加到team_strategy表中
                TeamStrategy teamStrategy = new TeamStrategy();
                teamStrategy.setCourse(course);
                teamStrategy.setStrategyName(new String("TeamAndStrategy"));
                List<Strategy> strategyList2 = new ArrayList<>();
                for (TeamAndStrategy teamAndStrategy : returnTeamAndStrategyList2) {
                    strategyList2.add(teamAndStrategy);
                }
                teamStrategy.setStrategyList(strategyList2);
                teamStrategyDao.addTeamStrategy(teamStrategy);
            }
        } else {
            TeamStrategy teamStrategy = new TeamStrategy();
            teamStrategy.setCourse(course);
            teamStrategy.setStrategyName(strategyName1);
            List<Strategy> strategyList = new ArrayList<>();
            strategyList.add(memberLimitStrategy);
            teamStrategy.setStrategyList(strategyList);
            teamStrategyDao.addTeamStrategy(teamStrategy);
        }


        //记录冲突课程
        if (conflictCourseLists != null && !conflictCourseLists.isEmpty()) {
            for (List<Long> conflictCourseList : conflictCourseLists) {
                List<ConflictCourseStrategy> conflictCourseStrategyList = new ArrayList<>();
                for (Long courseId : conflictCourseList) {
                    ConflictCourseStrategy conflictCourseStrategy = new ConflictCourseStrategy();
                    conflictCourseStrategy.setCourse(courseDao.getCourse(courseId));
                    conflictCourseStrategyList.add(conflictCourseStrategy);
                }
                List<ConflictCourseStrategy> returnConflictCourseStrategyList = conflictCourseStrategyDao.addConflictCourseStrategyList(conflictCourseStrategyList);
                TeamStrategy teamStrategy = new TeamStrategy();
                teamStrategy.setCourse(course);
                teamStrategy.setStrategyName("ConflictCourseStrategy");
                List<Strategy> strategyList = new ArrayList<>();
                for (ConflictCourseStrategy item : returnConflictCourseStrategyList) {
                    strategyList.add(item);
                }
                teamStrategy.setStrategyList(strategyList);
                teamStrategyDao.addTeamStrategy(teamStrategy);
            }
        }

        if(status1 == 0 || status2 == 0){
            return 0;
        }
        else{
            return 1;
        }

    }

    /**
     * 删除课程
     * @param id int
     * @return Course
     */
    public int deleteCourse(Long id){
        return courseDao.deleteCourse(id);
    }

    /**
     * 更新课程
     * @param course Course
     * @return Course
     */
    public int updateCourse(Course course){
        return courseDao.updateCourse(course);
    }

    /**
     * 根据课程id 查询共享分组信息
     * @param courseId
     * @return
     */
    public List<ShareTeamApplication> getShareTeamApplicationByCourseId(Long courseId){
        return shareTeamApplicationDao.selectShareTeamApplicationByCourseId(courseId);
    }

    /**
     * 根据课程id 查询共享讨论课信息
     * @param mainCourseId
     * @return
     */
    public List<ShareSeminarApplication> getShareSeminarApplicationByMainCourseId(Long mainCourseId){
        return shareSeminarApplicationDao.selectShareSeminarApplicationByMainCourseId(mainCourseId);
    }

    /**
     * 取消分组共享
     * @param id
     * @return
     */
    public int deleteShareTeamApplication(Long id){
        ShareTeamApplication shareTeamApplication = shareTeamApplicationDao.selectShareTeamApplicationById(id);
        //更新从课程记录
        Course subCourse = shareTeamApplication.getSubCourse();
        subCourse.setTeamMainCourse(null);
        courseDao.updateCourse(subCourse);
        //删除从课程中的klass_team记录
        List<Klass> klassList = klassDao.getAllKlassByCourseId(subCourse.getId());
        int status = 1;
        for (Klass klass:klassList) {
            status = teamDao.deleteTeamFromKlassByKlassId(klass.getId());
        }
        status = shareSeminarApplicationDao.deleteShareSeminarApplication(id)==0?0:status;
        return status;

    }

    /**
     * 取消讨论课共享
     * @param id
     * @return
     */
    public int deleteShareSeminarApplication(Long id){
        ShareSeminarApplication shareSeminarApplication = shareSeminarApplicationDao.selectShareSeminarApplicationById(id);
        //更新从课程记录
        Course subCourse = shareSeminarApplication.getSubCourse();
        subCourse.setSeminarMainCourse(null);
        courseDao.updateCourse(subCourse);
        //删除从课程的klass_seminar记录
        List<Klass> klassList = klassDao.getAllKlassByCourseId(subCourse.getId());
        int status = 1;
        for (Klass klass:klassList) {
            status = klassSeminarDao.deleteKlassSeminarByKlassId(klass.getId())==0?0:status;
        }
        status = shareSeminarApplicationDao.deleteShareSeminarApplication(id)==0?0:status;
        return status;
    }

    /**
     * 获得没有组队的学生
     * @param courseId
     * @return
     */
    public List<Student> getStudentNoTeam(Long courseId){

        List<Student> studentNoTeamList = new ArrayList<>();
        List<KlassStudent> klassStudentList = klassStudentDao.selectKlassStudentByCourseId(courseId);
        for (KlassStudent klassStudent:klassStudentList) {
            if(klassStudent.getTeam()!=null){
                studentNoTeamList.add(klassStudent.getStudent());
            }
        }
        return studentNoTeamList;
    }

    /**
     * 新增共享分组请求
     * @param shareTeamApplication
     * @return
     */
    public int addShareTeamApplication(ShareTeamApplication shareTeamApplication){
        return shareTeamApplicationDao.addShareTeamApplication(shareTeamApplication);
    }

    /**
     * 新增共享讨论课请求
     * @param shareSeminarApplication
     * @return
     */
    public int addShareSeminarApplication(ShareSeminarApplication shareSeminarApplication){
        return shareSeminarApplicationDao.addShareSeminarApplication(shareSeminarApplication);
    }


    /**
     * 选择小组班级
     * @param course
     * @param team
     * @return
     */
    public Klass chooseKlassByCourseAndTeam(Course course,Team team){
        //当前课程的所有班级,初始化所有班级计数为0
        List<Klass> klassList = klassDao.getAllKlassByCourseId(course.getId());
        List<Map> klassCountList = new ArrayList<>();
        for (Klass klass:klassList) {
            Map map = new HashMap(2);
            map.put("klassId",klass.getId());
            map.put("number",0);
            klassCountList.add(map);
        }
        //获得当前小组的所有学生
        List<Student> studentList = team.getMembers();
        //根据课程id和学生id查找学生在该门课程的班级,并记录班级中的小组学生个数
        List<KlassStudent> klassStudentList = new ArrayList<>();
        for (Student student:studentList) {
            Klass klass = klassStudentDao.selectKlassByCourseIdAndStudentId(course.getId(),student.getId());
            for (Map klassCount:klassCountList) {
                if(Long.valueOf(klassCount.get("klassId").toString())==klass.getId()){
                    Integer number = Integer.parseInt(klassCount.get("number").toString())+1;
                    klassCount.put("number",number);
                }
            }
        }
        //选择班级
        Long maxKlassId = Long.valueOf(klassCountList.get(0).get("klassId").toString());
        Integer maxNumber = Integer.parseInt(klassCountList.get(0).get("number").toString());
        for (Map klassCount:klassCountList) {
            Integer number = Integer.parseInt(klassCount.get("number").toString());
            if(number>maxNumber){
                maxNumber = number;
                maxKlassId = Long.valueOf(klassCount.get("klassId").toString());
            }
            /*TODO 如果人数相等怎么办？*/
        }
        if(maxKlassId == 0){
            return null;
        }
        else{
            return klassDao.getKlass(maxKlassId);
        }

    }


    /**
     * 处理共享分组请求
     * @param teamShareId
     * @param handler
     * @return
     */
    public int handleTeamShare(Long teamShareId,Integer handler){
        ShareTeamApplication shareTeamApplication = shareTeamApplicationDao.selectShareTeamApplicationById(teamShareId);
        Course subCourse = shareTeamApplication.getSubCourse();
        //如果从课程已经接受了其他的分组共享，则拒绝
        if(subCourse.getTeamMainCourse()!=null){
            shareTeamApplication.setStatus(0);
            shareTeamApplicationDao.updateShareTeamApplication(shareTeamApplication);
            return 0;
        }
        //被共享的课程拒绝共享
        if(handler==0){
            shareTeamApplication.setStatus(0);
            shareTeamApplicationDao.updateShareTeamApplication(shareTeamApplication);
            return 1;
        }
        else{
            //删除从课程中原来的分组
            List<Klass> klassList = klassDao.getAllKlassByCourseId(subCourse.getId());
            for (Klass klass:klassList) {
                teamDao.deleteTeamFromKlassByKlassId(klass.getId());
            }

            //修改从课程的teamMainCourse
            Course mainCourse = shareTeamApplication.getMainCourse();
            subCourse.setTeamMainCourse(mainCourse);
            courseDao.updateCourse(subCourse);

            //将班级小组信息插入到klass_team表
            List<Team> teamList = teamDao.getTeamByCourseId(mainCourse.getId());
            for (Team team:teamList) {

                //对主课程的每个小组划分在从课程的班级
                Klass klass = chooseKlassByCourseAndTeam(subCourse,team);
                if(klass!=null){
                    teamDao.addTeamIntoKlass(team,klass);
                }
            }
            return 1;
        }
    }


    /**
     * 处理共享讨论课请求
     * @param seminarShareId
     * @param handler
     * @return
     */
    public int handleSeminarShare(Long seminarShareId,Integer handler){
        ShareSeminarApplication shareSeminarApplication = shareSeminarApplicationDao.selectShareSeminarApplicationById(seminarShareId);
        Course subCourse = shareSeminarApplication.getSubCourse();
        if(subCourse.getSeminarMainCourse()!=null){
            shareSeminarApplication.setStatus(0);
            shareSeminarApplicationDao.updateSeminarApplication(shareSeminarApplication);
            return 0;
        }
        if(handler == 0){
            shareSeminarApplication.setStatus(0);
            shareSeminarApplicationDao.updateSeminarApplication(shareSeminarApplication);
            return 1;
        }
        else {
            //删除从课程中原来的的讨论课
            List<Klass> klassList = klassDao.getAllKlassByCourseId(subCourse.getId());
            for (Klass klass:klassList) {
                klassSeminarDao.deleteKlassSeminarByKlassId(klass.getId());
            }
            //修改从课程的seminarMainCourse
            Course mainCourse = shareSeminarApplication.getMainCourse();
            subCourse.setSeminarMainCourse(mainCourse);
            courseDao.updateCourse(subCourse);

            //将共享的seminar插入到klass_seminar表
            List<Seminar> seminarList = seminarDao.listSeminarByCourseId(mainCourse.getId());
            List<KlassSeminar> klassSeminarList = new ArrayList<>();
            for (Klass klass : klassList) {
                for (Seminar seminar : seminarList) {
                    KlassSeminar klassSeminar = new KlassSeminar();
                    klassSeminar.setKlass(klass);
                    klassSeminar.setSeminar(seminar);
                    klassSeminar.setSeminarStatus(0);
                    klassSeminarList.add(klassSeminar);
                }
            }
            return klassSeminarDao.insertKlassSeminarList(klassSeminarList);
            /* TODO klass_round中的enrollNumber在哪设置？*/
        }
    }

    public List<Team> getTeamByCourseId(Long courseId){
        return teamDao.getTeamByCourseId(courseId);
    }

}
