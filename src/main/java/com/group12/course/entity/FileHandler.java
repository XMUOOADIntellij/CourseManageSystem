package com.group12.course.entity;

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

    public ArrayList<User> handlerStudentList(MultipartFile file){
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount=sheet.getLastRowNum();
            ArrayList<User> userlist = new ArrayList<>(rowCount-2);
            for (int i = 0; i <= rowCount ; i++) {
                if (i<2){
                    continue;
                }
                /**
                 * TODO
                 * 修复空格 bug*/
                User tempUser= new User();
                DataFormatter dataFormatter = new DataFormatter();
                System.out.println(sheet.getRow(i).getCell(0));
                System.out.println(dataFormatter.formatCellValue(sheet.getRow(i).getCell(0)));
                tempUser.setAccount(dataFormatter.formatCellValue(sheet.getRow(i).getCell(0)).trim());
                tempUser.setPassword("123456");
                tempUser.setName(sheet.getRow(i).getCell(1).getStringCellValue());
                userlist.add(tempUser);
            }
            return userlist;
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
        ArrayList<User> users= new ArrayList<User>();
        return users;
    }


}
