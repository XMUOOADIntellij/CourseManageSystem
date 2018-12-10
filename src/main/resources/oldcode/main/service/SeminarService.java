package com.group12.course.service;

import com.group12.course.entity.Seminar;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param file MultipartFile file
     * @return String
     */
    String uploadPresentation(MultipartFile file);


    /**
     * 下载讨论课报告
     * @param request ServletRequest
     * @param response ServletResponse;
     * @return String
     */

    String downloadPresentation(HttpServletResponse response,String fileName,String filePath);


    /**
     * 批量下载报告
     * @param request
     * @param response
     * @return String
     */
    void downloadAllPresentation(HttpServletRequest request, HttpServletResponse response);
}