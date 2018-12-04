package com.group12.course.entity;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            // Get a Properties object
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.qq.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");

            final String username = "277030573@qq.com";
            final String password = "xnmyetoesmpzbjjf";
            Session session = Session.getDefaultInstance(props, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }});
            Message msg = new MimeMessage(session);

            // 设置发件人和收件人
            msg.setFrom(new InternetAddress(username));
            Address tos = new InternetAddress(to);

            // 多个收件人地址
            msg.setRecipient(Message.RecipientType.TO, tos);
            msg.setSubject("验证码：" );
            // 标题
            msg.setText("验证码为：" + text);
            // 内容
            msg.setSentDate(new Date());
            Transport.send(msg);
            return true;
        }
        catch (Exception e){
            /**
             * TODO
             * 日志记录*/
            return false;
        }
    }
}
