package com.amao.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringbootAysncApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void test1() {
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:30开会");
        message.setTo("wuweimou@aliyun.com");
        message.setFrom("1039416911@qq.com");
        mailSender.send(message);
    }

    @Test
    void test2() throws MessagingException {
        //1、创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        //邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);

        helper.setTo("1522880911@qq.com");
        helper.setFrom("1039416911@qq.com");

        //上传文件
        helper.addAttachment("1.gif",new File("C:\\Users\\ASUS\\Desktop\\微信图片_20200223192607.gif"));
        helper.addAttachment("2.jpg",new File("C:\\Users\\ASUS\\Desktop\\微信图片_20200223192642.jpg"));

        mailSender.send(mimeMessage);


    }

}
