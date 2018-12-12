package com.group12.course.service.serviceimpl;

import com.group12.course.entity.Seminar;
import com.group12.course.service.SeminarService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        //讨论课列表
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
        return fileName+suffixName;
    }

    @Override
    public String downloadPresentation(HttpServletResponse response,String fileName,String filePath){

        if(fileName!=null){
            File file = new File(filePath,fileName);
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

    @Override
    public void downloadAllPresentation(HttpServletRequest request, HttpServletResponse response){
        String[] names = {"test.txt", "test1.txt", "test2.txt", "test3.txt"};
        String[] paths = {"E:/test/", "E:/test/", "E:/test/", "E:/test/"};

        String directory = "E:/test1/";
        File directoryFile = new File(directory);
        if (!directoryFile.isDirectory() && !directoryFile.exists()) {
            directoryFile.mkdirs();
        }

        String zipFileName ="test" + ".zip";
        String stringZipPath = directory + zipFileName;

        //压缩文件输出流
        ZipOutputStream zipOutputStream = null;
        //文件输入流
        FileInputStream fileInputStream = null;
        //缓冲区
        BufferedInputStream bufferedInputStream = null;
        File zipFile = new File(stringZipPath);

        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            // OutputStream outputStream = response.getOutputStream();
            for (int i = 0; i < paths.length; i++) {
                //解码获取真实路径与文件名
                String realFileName = java.net.URLDecoder.decode(names[i], "UTF-8");
                String realFilePath = java.net.URLDecoder.decode(paths[i], "UTF-8");
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
            try {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(zipFile.exists()){
            downloadPresentation(response,stringZipPath,zipFileName);
            zipFile.delete();
        }
    }
}
