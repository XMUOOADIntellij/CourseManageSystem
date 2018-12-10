package com.group12.course.service.serviceimpl;

import com.group12.course.dao.GroupDao;
import com.group12.course.entity.Group;
import com.group12.course.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Group Service 层接口实现
 * @author Tan Xue
 * @date 2018/12/4
 */
@Service
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    @Override
    public List<Group> getAllGroups(Long courseId){
        return groupDao.listGroups(courseId);
    }

    @Override
    public Group getGroup(String groupId) {
        return groupDao.selectByPrimaryKey(groupId);
    }

    @Override
    public int addMember(String studentId) {
        return 0;
    }

    @Override
    public int deleteMember(String studentId) {
        return 0;
    }

    @Override
    public int disbandGroup(String groupId) {
        return groupDao.deleteByPrimaryKey(groupId);
    }

    @Override
    public int apply(String groupId) { return 0;}
}
