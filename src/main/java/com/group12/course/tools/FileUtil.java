package com.group12.course.tools;

import com.group12.course.entity.Attendance;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    static public void downloadAllFiles(HttpServletResponse response, List<String> names, String path) throws Exception{

        for(String item:names){
            System.out.println(path+names);
        }
        File directoryFile = new File(path);
        if (!directoryFile.isDirectory() && !directoryFile.exists()) {
            directoryFile.mkdirs();
        }

        String zipFileName ="all" + ".zip";
        String stringZipPath = path;

        //压缩文件输出流
        ZipOutputStream zipOutputStream = null;
        //文件输入流
        FileInputStream fileInputStream = null;
        //缓冲区
        BufferedInputStream bufferedInputStream = null;
        File zipFile = new File(stringZipPath,zipFileName);

        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < names.size(); i++) {
                //解码获取真实路径与文件名
                String realFileName = java.net.URLDecoder.decode(names.get(i), "UTF-8");
                String realFilePath = java.net.URLDecoder.decode(path, "UTF-8");
                File file = new File(realFilePath,realFileName);
                //未对文件不存在时进行操作，后期优化。
                if (file.exists()) {
                    //将需要压缩的文件格式化为输入流
                    fileInputStream = new FileInputStream(file);
                    //在压缩目录中文件的名字
                    ZipEntry zipEntry = new ZipEntry(realFileName);
                    //定位该压缩条目位置，开始写入文件到压缩包中
                    zipOutputStream.putNextEntry(zipEntry);
                    bufferedInputStream = new BufferedInputStream(fileInputStream, 1024 * 10);
                    int read = 0;
                    byte[] buf = new byte[1024 * 10];
                    int len = 1024*10;
                    while ((read = bufferedInputStream.read(buf, 0, len)) != -1) {
                        zipOutputStream.write(buf, 0, read);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (null != bufferedInputStream) {
                bufferedInputStream.close();
            }
            if (null != zipOutputStream) {
                zipOutputStream.flush();
                zipOutputStream.close();
            }
            if (null != fileInputStream) {
                fileInputStream.close();
            }

        }
        if(zipFile.exists()){
            downloadFile(response,stringZipPath+zipFileName);
            zipFile.delete();
        }
    }

}
