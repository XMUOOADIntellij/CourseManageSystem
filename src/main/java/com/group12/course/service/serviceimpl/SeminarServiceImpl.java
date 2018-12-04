package com.group12.course.service.serviceimpl;

import com.group12.course.entity.Seminar;
import com.group12.course.service.SeminarService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 *
 * Seminar Controller 实现
 * @author Y Jiang
 * @date  2018/12/3
 */
@Service
public class SeminarServiceImpl implements SeminarService {

    @Override
    public List<Seminar> listRoundSeminars(Long roundId){
        //TODO 讨论课列表
         return null;
    }

    @Override
    public String uploadPresentation(MultipartFile file){
        if(file.isEmpty()){
            return "Empty file";
        }

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        //测试用path
        String filePath = "E://test//";
        File dest =new File(filePath + fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);
            return dest.toString();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "Upload failed!";
    }

    @Override
    public String downloadPresentation(HttpServletRequest request, HttpServletResponse response){
        String fileName = "test.txt";

        if(fileName!=null){
            String realPath ="E:/test/";
            File file = new File(realPath,fileName);
            if(file.exists()){

                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition","attachment;fileName="+fileName);

                byte[] buffer = new byte[1024];
                FileInputStream fileInputStream = null;
                BufferedInputStream bufferedInputStream=null;

                try{
                    fileInputStream = new FileInputStream(file);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    OutputStream outputStream = response.getOutputStream();
                    int i=bufferedInputStream.read(buffer);
                    while(i!=-1){
                        outputStream.write(buffer,0,i);
                        i = bufferedInputStream.read(buffer);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(bufferedInputStream!=null){
                        try{
                            bufferedInputStream.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(fileInputStream!=null){
                        try{
                            fileInputStream.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
