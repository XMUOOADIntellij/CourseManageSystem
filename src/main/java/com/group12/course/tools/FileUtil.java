package com.group12.course.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    static public String uploadFile(MultipartFile file,String filePath) throws Exception{
        if(file.isEmpty()){
            return "Empty file";
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        File dest =new File(filePath + fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }

        file.transferTo(dest);
        return dest.toString();
    }

}
