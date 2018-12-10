package com.group12.course.view;

import com.group12.course.service.SeminarService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Seminar Relevant Api
 * @author Y Jiang
 * @date 2018/12/3
 */
@RestController
@RequestMapping("/api/seminar")
public class SeminarController {

    @Autowired
    SeminarService seminarService;

    @PostMapping("/presentation")
    public String uploadPresentation(@RequestParam("file") MultipartFile file) {
        return seminarService.uploadPresentation(file);
    }

    @GetMapping("/presentation")
    public String downloadPresentation(HttpServletResponse response, String fileName, @RequestBody String filePath) {
        System.out.println(filePath+fileName);
        return seminarService.downloadPresentation(response,fileName,filePath);
    }

    @GetMapping("/presentation/getAll")
    public void downloadAllPresentation(HttpServletRequest request, HttpServletResponse response) {
        seminarService.downloadAllPresentation(request,response);
    }

}
