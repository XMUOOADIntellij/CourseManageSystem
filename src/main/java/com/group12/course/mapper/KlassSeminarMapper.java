package com.group12.course.mapper;

import com.group12.course.entity.KlassSeminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 班级讨论课相关 Mapper层
 * @author Y Jiang
 * @date 2018/12/12
 */
@Mapper
@Component
public interface KlassSeminarMapper {

    /**
     * 按照id删除班级讨论课
     * @param id 班级讨论课id
     * @return 成功1，失败0
     */
    Integer deleteById(Long id);

    /**
     * 通过讨论课id删除班级讨论课
     * @param seminarId 讨论课id
     * @return 成功数目
     */
    Integer deleteBySeminarId(Long seminarId);

    /**
     * 通过id查找班级讨论课
     * @param id 班级讨论课id
     * @return 班级讨论课记录
     */
    KlassSeminar selectKlassSeminarById(Long id);

    /**
     * 通过讨论课id和班级id找到班级讨论课
     * @param seminarId 讨论课id
     * @param classId 班级id
     * @return 班级讨论课记录
     */
    KlassSeminar selectKlassSeminarBySeminarIdAndKlassId(Long seminarId, Long classId);

    /**
     * 列出讨论课下的所有班级讨论课
     * @param seminarId 讨论课id
     * @return 班级讨论课列表
     */
    List<KlassSeminar> listKlassSeminarBySeminarId(Long seminarId);

    /**
     * 更新班级讨论课信息
     * @param record 班级讨论课记录
     * @return 1成功 0失败
     */
    Integer updateKlassSeminar(KlassSeminar record);

    /**
     * 批量插入班级讨论课
     * @param list 班级讨论课列表
     * @return 成功数目
     */
    Integer insertKlassSeminarList(List<KlassSeminar> list);

    /**
     * 列出多个讨论课下的班级讨论课
     * @param seminarId 讨论课id列表
     * @return 班级讨论课列表
     */
    List<KlassSeminar> listKlassSeminarBySeminarIdList(@Param("seminarId") List<Long> seminarId);

    /**
     * 列出多个班级下的班级讨论课
     * @param klassIdList 班级id列表
     * @return 班级讨论课列表
     */
    List<KlassSeminar> listKlassSeminarByKlassIdList(@Param("klassIdList")List<Long>klassIdList);
}