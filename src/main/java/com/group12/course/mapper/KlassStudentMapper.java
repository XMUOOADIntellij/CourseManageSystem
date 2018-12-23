package com.group12.course.mapper;

import com.group12.course.entity.KlassStudent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.List;

@Mapper
@Component
public interface KlassStudentMapper {

    List<KlassStudent> selectKlassStudentByCourseId(Long courseId);

    List<KlassStudent> selectKlassStudentByStudentId(Long studentId);

    int deleteKlassStudentByKlassId(Long klassId);

    int addKlassStudent(KlassStudent klassStudent);

}
