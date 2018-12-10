package com.group12.course.view;

import com.group12.course.entity.Course;
import com.group12.course.entity.Group;
import com.group12.course.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Group controller
 * @author Tan Xue
 * @date 2018/12/4
 */
@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * 获取当前课程的所有小组
     * @param course 课程
     * @return 班级列表
     */
    @GetMapping(value="/getAll",produces = "application/json")
    public List<Group> getAllGroups(@RequestBody Course course){
        return groupService.getAllGroups(course.getId());
    }

    /**
     * 根据小组id 获取小组信息
     * @param groupId 小组编号
     * @return 小组
     */
    @GetMapping(value = "/{groupId}",produces = "application/json")
    public Group getGroup(@PathVariable String groupId){
        return groupService.getGroup(groupId);
    }

    /**
     * 添加小组成员
     * @param studentId 学生编号
     * 添加成功返回200,
     * 失败则返回410
     */
    @PostMapping(value="/addMember")
    public void addMember(String studentId,HttpServletResponse reponse){

    }

    /**
     * 删除小组成员
     * @param studentId 学生编号
     * 删除成功返回200,
     * 失败则返回410
     */
    @DeleteMapping(value = "/deleteMember")
    public void deleteMember(String studentId,HttpServletResponse reponse){

    }

    /**
     * 解散小组
     * @param groupId 小组编号
     * 解散成功返回200
     * 失败则返回410
     */
    @DeleteMapping(value = "/deleteGroup")
    public void disbandGroup(String groupId,HttpServletResponse reponse){
        groupService.disbandGroup(groupId);
    }

    /**
     * 申请组队
     * @param groupId
     */
    @PutMapping(value = "/apply")
    public void apply(String groupId){

    }
}
