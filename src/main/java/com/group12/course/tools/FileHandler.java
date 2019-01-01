package com.group12.course.tools;

import com.group12.course.entity.Student;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;


/**
 * 用于处理文件的类
 * @author Xu Gang
 * @date 2018年12月5日
 * */
public class FileHandler {

    public ArrayList<Student> handlerStudentList(MultipartFile file){
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount=sheet.getLastRowNum();
            ArrayList<Student> studentList = new ArrayList<>(rowCount-2);
            for (int i = 0; i <= rowCount ; i++) {
                if (i<2){
                    continue;
                }
                Student tempStudent= new Student();
                DataFormatter dataFormatter = new DataFormatter();
                tempStudent.setAccount(dataFormatter.formatCellValue(
                        sheet.getRow(i).getCell(0))
                        .trim().replaceAll("\\u00a0",""));
                /**
                 * 这个替换掉的是一个 excel 自带的不可见的空白符
                 * 无法用 trim() 去除
                 * 只能人工去掉
                 * I love excel :)
                 * */
                tempStudent.setPassword("123456");
                tempStudent.setStudentName(sheet.getRow(i).getCell(1).getStringCellValue());
                studentList.add(tempStudent);
            }
            return studentList;
        }
        catch (Exception e){
            /**
             * 异常记录
             * */
            ArrayList<Student> users= new ArrayList<Student>();
            return users;
        }

    }

}
