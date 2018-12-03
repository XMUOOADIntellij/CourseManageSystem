package com.group12.course.entity;

import java.util.Random;

/**
 * 生成验证码
 * @author Xu Gang
 * @date 2018年12月2日
 * */
public class VerificationCode {

    private String verificationCode;
    private final int codeLength=6;

    public String generateCode(){
        verificationCode = new String("");
        Random random=new Random();
        for (int i=0;i<codeLength;i++){
            int randomNum=random.nextInt(10);
            verificationCode += randomNum;
        }
        return verificationCode;
    }
}
