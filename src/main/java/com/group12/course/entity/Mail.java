package com.group12.course.entity;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * 用于发送邮件
 * @author Xu Gang
 * @date 2018年12月2日
 * */
@Component
public class Mail {

    private JavaMailSenderImpl senderImpl;

    private SimpleMailMessage mailMessage;

    private Properties prop;

    public Mail() {
        this.senderImpl=new JavaMailSenderImpl();
        this.mailMessage=new SimpleMailMessage();
        this.prop=new Properties();
    }

    /**
     * 通过邮箱发送验证码的方法
     * @param to 目标邮箱地址
     * @param text 验证码
     * @return 是否发送成功
     **/

    public boolean sendMail (String to,String text) {
        try{
            // 设定mail server
            senderImpl.setHost("smtp.qq.com");

            mailMessage.setTo(to);
            mailMessage.setFrom( "277030573@qq.com" );
            mailMessage.setSubject( "验证码：" );
            mailMessage.setText("验证码为：" + text);

            senderImpl.setUsername("277030573@qq.com");
            senderImpl.setPassword("xnmyetoesmpzbjjf");

            prop.put("mail.smtp.auth","true");
            prop.put("mail.smtp.timeout","25000");
            senderImpl.setJavaMailProperties(prop);

            // 发送邮件
            senderImpl.send(mailMessage);

            return true;
        }catch (Exception e) {
            /**
             * TODO
             * 将错误记录在日志中*/
            return false;
        }
    }

}
