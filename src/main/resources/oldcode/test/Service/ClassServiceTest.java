package com.group12.course.Service;

import com.group12.course.entity.Class;
import com.group12.course.service.ClassService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试 Class 的 service 层方法
 * @author Tan Xue
 * @date 2018/12/2
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ClassService classService;
    private Class class1;

    @Before
    public void initTestClass(){
        class1 = new Class();
        class1.setId("1");
        class1.setName("OOAD");
        class1.setTime("周一三四节");
        class1.setLocation("公寓305");
        class1.setCourseId(new Long(1));
    }

    @Test
    public void testGetAllClasses(){
        classService.deleteClass(class1.getId());
        classService.addClass(class1);
        Assert.assertFalse(classService.getAllClasses(class1.getCourseId()).isEmpty());
    }

    @Test
    public void testAddClass() {
        classService.deleteClass(class1.getId());
        Assert.assertEquals(1,classService.addClass(class1));
    }

    @Test
    public void testGetClassById() {
        classService.deleteClass(class1.getId());
        classService.addClass(class1);
        Assert.assertEquals("公寓305",classService.getClassById(class1.getId()).getLocation());
    }

    @Test
    public void testUpdateClass() {
        classService.deleteClass(class1.getId());
        classService.addClass(class1);
        class1.setLocation("海韵202");
        Assert.assertEquals(1,classService.updateClass(class1));
        Assert.assertEquals("海韵202",classService.getClassById(class1.getId()).getLocation());
    }

    @Test
    public void testDeleteClass() {
        classService.deleteClass(class1.getId());
        classService.addClass(class1);
        Assert.assertEquals(1,classService.deleteClass(class1.getId()));
        //再次删除找不到，返回0
        Assert.assertEquals(0,classService.deleteClass(class1.getId()));
    }
}