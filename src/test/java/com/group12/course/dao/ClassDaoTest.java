package com.group12.course.dao;

import com.group12.course.entity.Class;
import com.group12.course.mapper.ClassMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 测试 Class 的 Dao 层方法
 * @author Tan Xue
 * @date 2018/12/2
 */

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

public class ClassDaoTest {

    @Autowired
    private ClassMapper classMapper;
    private Class class1;

    @Before
    public void initTestClass(){
        class1 = new Class();
        class1.setId(new String("1"));
        class1.setName(new String("OOAD"));
        class1.setTime(new String("周一三四节"));
        class1.setLocation(new String("公寓305"));
        class1.setCourseId(new Long(1));
    }

    @Test
    @Rollback
    public void testListClasses(){
        classMapper.deleteByPrimaryKey(class1.getId());
        classMapper.insert(class1);
        Assert.assertFalse(classMapper.listClasses(class1.getCourseId()).isEmpty());
    }

    @Test
    @Rollback
    public void testInsert() {
        classMapper.deleteByPrimaryKey(class1.getId());
        Assert.assertEquals("Insert Error",1,classMapper.insert(class1));
    }

    @Test
    @Rollback
    public void testInsertSelective() {
        class1.setLocation(null);
        classMapper.deleteByPrimaryKey(class1.getId());
        Assert.assertEquals("Class InsertSelective Error",1,classMapper.insertSelective(class1));
    }

    @Test
    @Rollback
    public void testDeleteByPrimaryKey() {
        classMapper.deleteByPrimaryKey(class1.getId());
        classMapper.insert(class1);
        Assert.assertEquals("Class Delete Error",1,classMapper.deleteByPrimaryKey(class1.getId()));
        // 再次删除找不到，返回0
        Assert.assertEquals("Class Delete Error",0,classMapper.deleteByPrimaryKey(class1.getId()));
    }

    @Test
    @Rollback
    public void testSelectByPrimaryKey() {
        classMapper.deleteByPrimaryKey(class1.getId());
        classMapper.insert(class1);
        Assert.assertEquals("公寓305",classMapper.selectByPrimaryKey(class1.getId()).getLocation());
    }

    @Test
    @Rollback
    public void testUpdateByPrimaryKey(){
        classMapper.deleteByPrimaryKey(class1.getId());
        classMapper.insert(class1);
        class1.setLocation(new String("海韵202"));
        class1.setTime(null);
        Assert.assertEquals(1,classMapper.updateByPrimaryKey(class1));
        Assert.assertEquals("海韵202",classMapper.selectByPrimaryKey(class1.getId()).getLocation());
        Assert.assertEquals(null,classMapper.selectByPrimaryKey(class1.getId()).getTime());
    }

    @Test
    @Rollback
    public void testUpdateByPrimaryKeySelective() {
        classMapper.deleteByPrimaryKey(class1.getId());
        classMapper.insert(class1);
        class1.setLocation(new String("海韵202"));
        class1.setTime(null);
        Assert.assertEquals(1,classMapper.updateByPrimaryKeySelective(class1));
        Assert.assertEquals("海韵202",classMapper.selectByPrimaryKey(class1.getId()).getLocation());
        //不更新空值
        Assert.assertEquals("周一三四节",classMapper.selectByPrimaryKey(class1.getId()).getTime());

    }
}