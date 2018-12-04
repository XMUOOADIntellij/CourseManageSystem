package com.group12.course.dao.daoimpl;

import com.group12.course.dao.GroupDao;
import com.group12.course.entity.Group;
import com.group12.course.mapper.GroupMapper;

import java.util.List;

/**
 * Group Dao 层接口实现
 * @author Tan Xue
 * @date 2018/12/1
 */
public class GroupDaoImpl implements GroupDao {

    GroupMapper groupMapper;

    @Override
    public List<Group> listGroups(Long courseId){
        return groupMapper.listGroups(courseId);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return groupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Group record) {
        return groupMapper.insert(record);
    }

    @Override
    public int insertSelective(Group record) {
        return groupMapper.insertSelective(record);
    }

    @Override
    public Group selectByPrimaryKey(String id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Group record) {
        return groupMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Group record) {
        return groupMapper.updateByPrimaryKey(record);
    }
}
