package com.group12.course.mapper;

import com.group12.course.entity.Klass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Klass Mapper 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Mapper
@Component
public interface KlassMapper {

    /**
     * 删除班级
     * @param id 班级编号
     * @return 删除数量
     */
    int deleteKlass(Long id);

    /**
     * 添加班级
     * @param record 班级
     * @return 添加数量
     */
    int addKlass(Klass record);

    /**
     * 查询班级
     * @param id 班级编号
     * @return 班级
     */
    Klass getKlass(Long id);

    /**
     * 修改班级信息
     * @param record 班级
     * @return 修改数量
     */
    int updateKlass(Klass record);

}