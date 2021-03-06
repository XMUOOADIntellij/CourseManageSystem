package com.group12.course.tools;
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
 * @date 2018年12月15日
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
            final String sslFactory = "javax.net.ssl.SSLSocketFactory";
            // Get a Properties object
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.163.com");
            props.setProperty("mail.smtp.socketFactory.class", sslFactory);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");

            final String username = "xmucms1_2@163.com";
            final String password = "ooad18805925581";
            Session session = Session.getDefaultInstance(props, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }});
            Message msg = new MimeMessage(session);

            // 设置发件人和收件人
            msg.setFrom(new InternetAddress(username));
            Address tos = new InternetAddress(to);

            // 收件人地址
            msg.setRecipient(Message.RecipientType.TO, tos);
            msg.setSubject("忘记密码" );
            // 标题
            msg.setText("当前密码为：" + text);
            // 内容
            msg.setSentDate(new Date());
            Transport.send(msg);
            return true;
        }
        catch (Exception e){

            return false;
        }
    }
}
