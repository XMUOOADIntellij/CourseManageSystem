package com.group12.course.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件处理工具类
 * ① 上传文件
 * ② 下载文件
 * ③ 批量下载，打包成压缩文件
 * @author Y Jiang
 * @date 2018/12/19
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    static public String uploadFile(MultipartFile file, String filePath, String fileName) throws Exception {
        if (file.isEmpty()) {
            return "Empty file";
        }
        File dest = new File(filePath + fileName);

        if (!dest.getParentFile().exists()) {
            logger.debug("makeDirections"+dest.getParentFile());
            dest.getParentFile().mkdirs();
        }
        try {
            logger.debug("fileTransfer");
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    static public void downloadFile(HttpServletResponse response, String fileUrl, String fileName) throws Exception {
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        //2.设置文件头：最后一个参数是设置下载文件名
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,"utf-8"));

        if (fileUrl != null) {
            File file = new File(fileUrl);
            if (file.exists()) {
                byte[] buffer = new byte[1024];
                FileInputStream fileInputStream = null;
                BufferedInputStream bufferedInputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    OutputStream outputStream = response.getOutputStream();
                    int i = bufferedInputStream.read(buffer);
                    while (i != -1) {
                        outputStream.write(buffer, 0, i);
                        i = bufferedInputStream.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return;
    }

    static public void downloadAllFiles(HttpServletResponse response, List<String> url, List<String> fileName) throws Exception {

        //取出路径
        String path = url.get(0).substring(0, url.get(0).lastIndexOf(System.getProperty("file.separator")) + 1);

        File directoryFile = new File(path);
        if (!directoryFile.isDirectory() && !directoryFile.exists()) {
            directoryFile.mkdirs();
        }

        String zipFileName = "all" + ".zip";
        String stringZipPath = path;

        //压缩文件输出流
        ZipOutputStream zipOutputStream = null;
        //文件输入流
        FileInputStream fileInputStream = null;
        //缓冲区
        BufferedInputStream bufferedInputStream = null;
        File zipFile = new File(stringZipPath, zipFileName);

        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < url.size(); i++) {
                //解码获取真实路径与文件名
                String realFileName = fileName.get(i);
                File file = new File(url.get(i));
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
                    int len = 1024 * 10;
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
        if (zipFile.exists()) {
            downloadFile(response, stringZipPath + zipFileName, zipFileName);
            zipFile.delete();
        }
    }

}
