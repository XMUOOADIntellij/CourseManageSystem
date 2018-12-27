package com.group12.course.controller;

import com.group12.course.service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/class")
public class KlassController {

    @Autowired
    KlassService klassService;

    /**
     * 在指定班级导入学生名单
     * @param
     * @return
     */
    @PostMapping(value="/{classId}",produces = "application/json; charset=utf-8")
    public void uploadStudentList(@PathVariable Long classId, @RequestParam("file") MultipartFile file, HttpServletResponse response){
        int status = klassService.uploadStudentList(classId,file);
        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(201);
        }
    }

    /**
     * 删除某一课程下的某一班级
     * @param classId
     */
    @DeleteMapping(value="/{classId}",produces = "application/json; charset=utf-8")
    public void deleteKlassByKlassId(@PathVariable Long classId,HttpServletResponse response){
        int status = klassService.deleteKlass(classId);
        if(status == 0){
            response.setStatus(404);
        }
        else{
            response.setStatus(200);
        }
    }
}
