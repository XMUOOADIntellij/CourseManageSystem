package com.group12.course.service;

import com.group12.course.entity.Seminar;

import java.util.List;

/**
 * @author Y Jiang
 * @date  2018/12/3
 */
public interface SeminarService {

    /**
     * 获得当前轮次所有讨论课
     * @param roundId Long
     * @return List
     */
    List<Seminar> listRoundSeminars(Long roundId);

    /**
     *
     * @param courseId
     * @return
     */
    List<Seminar> listCourseSeminars(Long courseId);


}
