package course.test;

import com.group12.course.dao.CourseDao;
import com.group12.course.entity.Course;
import com.group12.course.mapper.CourseMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CourseTest {

    private CourseDao mapper;
    public static void main(String[] args) {
       // SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build();
        //SqlSession sqlSession= SqlSessionFactory;
        //CourseMapper courseMapper=sqlSession.getMapper(CourseMapper.class);

        //Course course=new Course("3","00BD","MingQiu","Haiyun303");
       // Course result=courseMapper.add(course);
       // sqlSession.commit();

       // System.out.println(result.getName());

    }


 //根据id查询课
    @Test
 public void testGetCourseById() {
        Course course = this.mapper.getCourseNameById(2);
        System.out.println(course.getName());
 }
}
