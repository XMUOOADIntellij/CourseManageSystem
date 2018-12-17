package com.group12.course.tools;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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


    static public void downloadFile(HttpServletResponse response, String fileUrl) throws Exception{
        if(fileUrl!=null){
            File file = new File(fileUrl);
            if(file.exists()){
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition","attachment;fileUrl="+fileUrl);

                byte[] buffer = new byte[1024];
                FileInputStream fileInputStream = null;
                BufferedInputStream bufferedInputStream=null;


                fileInputStream = new FileInputStream(file);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                OutputStream outputStream = response.getOutputStream();
                int i=bufferedInputStream.read(buffer);
                while(i!=-1){
                    outputStream.write(buffer,0,i);
                    i = bufferedInputStream.read(buffer);
                }

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
            else{
                //TODO FileNotFound
            }
        }
        return;
    }

}
